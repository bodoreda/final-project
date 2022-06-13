package kr.co.pet.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import kr.co.pet.model.service.PetService;
import kr.co.pet.model.vo.PetBoard;
import kr.co.pet.model.vo.PetBoardPageData;
import kr.co.pet.model.vo.PetRoomReserve;

@Controller
public class PetController {
	@Autowired
	private PetService service;
	
	@RequestMapping(value="/petRoomReserve.do")
	private String petRoomReserve(int reqPage, Model model) {
		/* ArrayList<PetBoard> list = service.pbList(); */
		PetBoardPageData pbpd = service.pbList(reqPage);
		model.addAttribute("list", pbpd.getList());
		model.addAttribute("pageNavi", pbpd.getPageNavi());
		return "pet/petRoomReserve";
	}
	
	/*
	@ResponseBody
	@RequestMapping(value="/petRoomReserve.do",produces="application/json;charset=utf-8")
	private String petRoomReserve(int pageNo) {
		PetBoardPageData pbpd = service.pbList(pageNo);
		return new Gson().toJson(pbpd);
	}
	페이지넘버 클릭시 ajax를 통해 처리하는 메소드, jsp의 ajax-success에서 data.list, data.pageNavi를 통해 값을 받고
	화면에 어떻게 출력할지까지  정해줘야해서 일단 보류
	*/
	
	@ResponseBody
	@RequestMapping(value="/selectRoomList.do",produces="application/json;charset=utf-8")
	private String selectRoomList(String checkinDate) {
		ArrayList<PetRoomReserve> list = service.selectRoomList(checkinDate);
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectRoomList2.do",produces="application/json;charset=utf-8")
	private String selectRoomList(String checkinDate,String checkoutDate) {
		ArrayList<PetRoomReserve> list = service.selectRoomList(checkinDate,checkoutDate);
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectRoomCharge.do",produces="application/json;charset=utf-8")
	private String selectRoomCharge(int roomNo, int roomNo2, int stay_period) {
		String total = service.selectRoomCharge(roomNo,roomNo2,stay_period);
		return new Gson().toJson(total);
	}
	
	@RequestMapping(value="/insertReserve.do")
	private String insertReserve(PetRoomReserve prr,int[] rooms,Model model) {
		int result = service.insertReserve(prr,rooms);
		//객실을 1개만 선택한 경우 rooms[0]에는 선택한 객실번호, rooms[1]에는 0 이 넘어옴.
		if(result == (rooms[1] == 0?1:2)) {	//insert 성공여부 판별 --> room[1]이 0인 경우 result == 1, 아닌 경우 result = 2
			model.addAttribute("msg", "반려견 객실 예약이 완료되었습니다.");
		}else {
			model.addAttribute("msg", "오류 발생");
		}
		model.addAttribute("loc", "/petRoomReserve.do?reqPage=1");
		return "common/msg";
	}
	
	@RequestMapping(value="/pbWrite.do")
	private String pbWrite(PetBoard pb, MultipartFile files[],HttpServletRequest request, Model model) {
		//파일 목록을 저장할 리스트 생성
		ArrayList<kr.co.pet.model.vo.PetBoardFile> fileList = new ArrayList<kr.co.pet.model.vo.PetBoardFile>();
		//파일이 존재하지 않더라도 배열은 무조건 길이 1 을 가짐
		if(files[0].isEmpty()) {
			//첨부파일이 없는경우
		}else {
			//첨부파일이 있는경우
			//파일처리
			//getRealPath() -> webapp 폴더
			String savePath = request.getSession()
									.getServletContext()
									.getRealPath("/resources/upload/petboard/");
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
				kr.co.pet.model.vo.PetBoardFile pbf = new kr.co.pet.model.vo.PetBoardFile();
				pbf.setFilename(filename);
				pbf.setFilepath(filepath);
				fileList.add(pbf);
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
		int result = service.insertPetBoard(pb,fileList);
		if(result != -1 && result == fileList.size()) {
			model.addAttribute("msg", "등록성공");
		}else {
			model.addAttribute("msg", "등록실패");
		}
		model.addAttribute("loc", "/petRoomReserve.do?reqPage=1");
		return "common/msg";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/viewPetBoard.do",produces="application/json;charset=utf-8")
	private String viewPetBoard(int pbNo) {
		PetBoard pb = service.selectOnePetBoard(pbNo);
		return new Gson().toJson(pb);
	}
	
	@RequestMapping(value="/deletePetBoard.do")
	private String deletePb(int pbNo,Model model) {
		int result = service.deletePb(pbNo);
		if(result>0) {
			model.addAttribute("msg","게시글 삭제 완료!");
		}else {
			model.addAttribute("msg","오류 발생!");
		}
		model.addAttribute("loc","/petRoomReserve.do?reqPage=1");
		return "common/msg";
	}
	
	
	@RequestMapping(value="/pbUpdate.do")
	private String updatePb(PetBoard pb,MultipartFile updateFiles[],HttpServletRequest request, Model model) {
		//파일 목록을 저장할 리스트 생성
		ArrayList<kr.co.pet.model.vo.PetBoardFile> fileList = new ArrayList<kr.co.pet.model.vo.PetBoardFile>();
		//파일이 존재하지 않더라도 배열은 무조건 길이 1 을 가짐
		if(updateFiles[0].isEmpty()) {
			//첨부파일이 없는경우
		}else {
			//첨부파일이 있는경우
			//파일처리
			//getRealPath() -> webapp 폴더
			String savePath = request.getSession()
									.getServletContext()
									.getRealPath("/resources/upload/petboard/");
			for(MultipartFile file : updateFiles) {
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
				kr.co.pet.model.vo.PetBoardFile pbf = new kr.co.pet.model.vo.PetBoardFile();
				pbf.setFilename(filename);
				pbf.setFilepath(filepath);
				fileList.add(pbf);
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
		int result = service.updatePb(pb,fileList);
		if(result != -1 && result == fileList.size()) {
			model.addAttribute("msg", "게시글 수정 완료!");
		}else {
			model.addAttribute("msg", "오류 발생!");
		}
		model.addAttribute("loc", "/petRoomReserve.do?reqPage=1");
		return "common/msg";
	}
}

