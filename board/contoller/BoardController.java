package kr.co.board.contoller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.board.model.service.BoardService;
import kr.co.board.model.vo.Board;
import kr.co.board.model.vo.boardComment;
import kr.co.board.model.vo.boardPageData;


@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/boardWriteFrm.do")
	public String boardWriteFrm() {
		return "board/boardWriteFrm";
	}
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite(Board b, MultipartFile files[],HttpServletRequest request, Model model) {
		//파일 목록을 저장할 리스트 생성
		ArrayList<kr.co.board.model.vo.File> fileList 
		= new ArrayList<kr.co.board.model.vo.File>();
		//파일이 존재하지 않더라도 배열은 무조건 길이 1 을 가짐
		if(files[0].isEmpty()) {
			//첨부파일이 없는경우
		}else {
			//첨부파일이 있는경우
			//파일처리
			//getRealPath() -> webapp 폴더
			String savePath = request.getSession()
									.getServletContext()
									.getRealPath("/resources/upload/board/");
			for(MultipartFile file : files) {
				//실제 유저가 올린 파일명(filename)
				String filename = file.getOriginalFilename();
				//유저가 올린 파일명을 마지막 . 기준으로 분리     test.txt -> test / .txt
				String onlyFilename = filename.substring(0,filename.indexOf("."));//test
				String extention = filename.substring(filename.indexOf("."));//.txt
				String filepath = null;
				int count = 0;			
				while(true) {
					if(count == 0) {
						filepath = onlyFilename+extention;//text.txt
					}else {
						filepath = onlyFilename+"_"+count+extention;//test_4.txt
					}
					File checkFile = new File(savePath+filepath);
					if(!checkFile.exists()) {
						break;
					}
					count++;
				}
				kr.co.board.model.vo.File f = new kr.co.board.model.vo.File();
				f.setFilename(filename);
				f.setFilepath(filepath);
				fileList.add(f);
				System.out.println("filename : "+filename);
				System.out.println("filepath : "+filepath);
				try {
					FileOutputStream fos = new FileOutputStream(new File(savePath+filepath));
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					byte[] bytes = file.getBytes();
					bos.write(bytes);
					bos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		int result = service.insertBoard(b,fileList);
		if(result != -1 && result == fileList.size()) {
			model.addAttribute("msg", "등록성공");
		}else {
			model.addAttribute("msg", "등록실패");
		}
		model.addAttribute("loc", "/boardList.do?page=1");
		return "common/msg";
	}
	@RequestMapping(value="/boardList.do")
	public String boardList(Model model, int page) {
		
		
		boardPageData bpd = service.bpdList(page);
		model.addAttribute("list", bpd.getList());
		model.addAttribute("pageNavi", bpd.getPagenavi());
		return "board/boardList";
	}
	@RequestMapping(value="/boardView.do")
	public String boardView(int boardNo,Model model) {
		Board b = service.selectOneBoard(boardNo);
		ArrayList<boardComment> list =service.boardCommentList(boardNo);
		
		model.addAttribute("b", b);
		model.addAttribute("list",list);
		return "board/boardView";
	}
	
	@RequestMapping(value="/boardCommentInsert.do")
	public String boardCommentInsert(boardComment bc,int boardNo, Model model) {
		int result = service.insertBoard(bc);
		if(result != -1) {
			model.addAttribute("msg", "등록성공");
		}else {
			model.addAttribute("msg", "등록실패");
		}
		model.addAttribute("loc", "/boardView.do?boardNo="+boardNo);
		return "common/msg";
	}
	@RequestMapping(value="/boardDelete.do")
	public String boardDelete(int boardNo,Model model) {
		
		int result= service.boardDelete(boardNo);
		if(result>0) {
			
			model.addAttribute("msg", "삭제성공");
		}else {
			model.addAttribute("msg", "삭제실패");
		}
		model.addAttribute("loc", "/boardList.do?page=1");		
		return "common/msg";
	}
	@RequestMapping(value="/boardUpdate.do")
	public String boardUpdate(Board b, Model model ) {
		
		int result = service.boardUpdate(b);
		if(result>0) {
			
			model.addAttribute("msg", "수정성공");
		}else {
			model.addAttribute("msg", "수정실패");
		}
		model.addAttribute("loc", "/boardList.do?page=1");		
		return "common/msg";
	}
	@RequestMapping(value="/boardUpdateFrm.do")
	public String boardUpdateFrm(int boardNo , Model model) {
		Board b = service.selectOneBoard(boardNo);
		model.addAttribute("b",b);
		return "board/boardUpdate";
	}
	@RequestMapping(value="/boardCommentUpdateFrm.do")
	public String boardCommentUpdateFrm(int boardNo,Model model) {
		Board b = service.selectOneBoard(boardNo);
		return "board/boardCommentUpdate";
	}
	@RequestMapping(value="/boardCommentUpdate.do")
	public String boardCommentUpdate(boardComment bc , Model model,int boardNo) {
		int result = service.boardCommentUpdate(bc);
		if(result>0) {
			
			model.addAttribute("msg", "수정성공");
		}else {
			model.addAttribute("msg", "수정실패");
		}
		model.addAttribute("loc", "/boardView.do?boardNo="+boardNo);		
		return "common/msg";
	}
	@RequestMapping(value="/boardCommentDelete.do")
	public String boardCommentDelete(int bcNo,int boardNo,Model model) {
		
		int result= service.boardCommentDelete(bcNo);
		System.out.println("CONTROLLER :"+bcNo);
		if(result>0) {
			
			model.addAttribute("msg", "삭제성공");
		}else {
			model.addAttribute("msg", "삭제실패");
		}
		model.addAttribute("loc", "/boardView.do?boardNo="+boardNo);		
		return "common/msg";
	}
	
}
