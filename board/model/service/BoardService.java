package kr.co.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.model.dao.BoardDao;
import kr.co.board.model.vo.Board;
import kr.co.board.model.vo.File;
import kr.co.board.model.vo.boardComment;
import kr.co.board.model.vo.boardPageData;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	@Transactional
	public int insertBoard(Board b, ArrayList<File> fileList) {
		//파일은 board_no가 필요하기때문에 board테이블에 insert하는것이 먼저
		int result1 = dao.insertBoard(b);
		int result = 0;
		if(result1>0) {
			int boardNo = dao.selectBoardNo();
			for(File f : fileList) {
				f.setBoardNo(boardNo);
				result += dao.insertFile(f);
			}
		}else {
			return -1;
		}
		return result;
	}
	public ArrayList<Board> boardList() {		
		return dao.boardList();
	}
	public Board selectOneBoard(int boardNo) {
		return dao.selectOneBoard(boardNo);
	}
	
	@Transactional
	public int insertBoard(boardComment bc) {
		int result = dao.insertBoardComment(bc);
		
		return result;
	}

	public ArrayList<boardComment> boardCommentList(int boardNo) {
		// TODO Auto-generated method stub
		return dao.boardCommentList(boardNo);
	}
	@Transactional
	public int boardDelete(int boardNo) {
		// TODO Auto-generated method stub
		return dao.boardDelete(boardNo);
	}

	public int boardUpdate(Board b) {
		// TODO Auto-generated method stub
		return dao.boardUpdate(b);
	}

	public int boardCommentUpdate(boardComment bc) {
		// TODO Auto-generated method stub
		return dao.boardCommentUpdate(bc);
	}

	public int boardCommentDelete(int bcNo) {
		// TODO Auto-generated method stub
		System.out.println("service :"+bcNo);
		return dao.boardCommentDelete(bcNo);
	}
	public boardPageData bpdList(int page) {
		int numPerPage = 10;	//한 페이지에 보여질 게시글의 수 : 10개
		int end = page*numPerPage;	//1p -> start:1,end:10, 2p -> start:11,end:20
		int start = end-numPerPage+1;
		
		//요청한 페이지의 게시글 목록 조회
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("end",end);
		map.put("start",start);
		ArrayList<Board> list = dao.bpdList(map);
		
		//페이지 네비게이션
		//전체 게시글 수 조회
		int totalCount = dao.totalCount();
		//전체 페이지 수 계산
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		//페이지 네비 길이 지정
		int pageNaviSize = 5;
		
		int pageNo = ((page-1)/pageNaviSize)*pageNaviSize+1;
		
		//페이지 네비
		String pageNavi = "<ul class='pagination'>";
		//페이지 네비 시작번호가 1이 아닌경우 '이전(prev)' 버튼
		if(pageNo != 1) {
			pageNavi += "<li id='pageNavi' class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList.do?page="+(pageNo-1)+"'>&lt</a></li>";
		}
		//페이지 숫자 생성
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == page) {
				pageNavi += "<li id='pageNavi' class='page-item'>";
				pageNavi += "<a class='page-link' href='/boardList.do?page="+pageNo+"'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li id='pageNavi' class='page-item'>";
				pageNavi += "<a class='page-link' href='/boardList.do?page="+pageNo+"'>"+pageNo+"</a></li>";
			}		
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//'다음(next)' 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li id='pageNavi' class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList.do?page="+(pageNo)+"'>&gt</a></li>";
		}
		pageNavi += "</ul>";
		System.out.println("dddd : "+list.size());
		System.out.println("dddd : "+pageNavi);
		boardPageData bpd = new boardPageData(list, pageNavi);
		return bpd;
	}
	
}

