package kr.co.pet.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pet.model.dao.PetDao;
import kr.co.pet.model.vo.PetBoard;
import kr.co.pet.model.vo.PetBoardFile;
import kr.co.pet.model.vo.PetBoardPageData;
import kr.co.pet.model.vo.PetRoomReserve;

@Service
public class PetService {
	@Autowired
	private PetDao dao;

	public ArrayList<PetRoomReserve> selectRoomList(String checkinDate) {
		// TODO Auto-generated method stub
		return dao.selectRoomList(checkinDate);
	}

	public ArrayList<PetRoomReserve> selectRoomList(String checkinDate, String checkoutDate) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("checkinDate",checkinDate);
		map.put("checkoutDate",checkoutDate);
		return dao.selectRoomList(map);
	}

	public String selectRoomCharge(int roomNo, int roomNo2, int stay_period) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("roomNo",roomNo);
		map.put("roomNo2",roomNo2);
		map.put("stay_period",stay_period);
		return dao.selectRoomCharge(map);
	}

	public int insertReserve(PetRoomReserve prr, int[] rooms) {
		// TODO Auto-generated method stub
		int result = 0;
		for(int roomNo : rooms) {
			if(roomNo != 0) {	// roomNo가 0인 경우는 객실이 선택되지 않은 경우이므로 제외
				prr.setRoomNo(roomNo);
				result += dao.insertReserve(prr);
			}
		}
		return result;
	}

	public PetBoardPageData pbList(int reqPage) {
		int numPerPage = 10;	//한 페이지에 보여질 게시글의 수 : 10개
		int end = reqPage*numPerPage;	//1p -> start:1,end:10, 2p -> start:11,end:20
		int start = end-numPerPage+1;
		
		//요청한 페이지의 게시글 목록 조회
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("end",end);
		map.put("start",start);
		ArrayList<PetBoard> list = dao.pbList(map);
		
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
		/*1~10p 요청 -> pageNavi 시작번호 : 1
		11~20p 요청 -> pageNavi 시작번호 : 6
		21~30p 요청 -> pageNavi 시작번호 : 11*/
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//페이지 네비
		String pageNavi = "<ul class='pagination'>";
		//페이지 네비 시작번호가 1이 아닌경우 '이전(prev)' 버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/petRoomReserve.do?reqPage="+(pageNo-1)+"#pbAnchor'>&lt</a></li>";
		}
		//페이지 숫자 생성
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/petRoomReserve.do?reqPage="+pageNo+"#pbAnchor'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/petRoomReserve.do?reqPage="+pageNo+"#pbAnchor'>"+pageNo+"</a></li>";
			}		
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//'다음(next)' 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/petRoomReserve.do?reqPage="+(pageNo)+"#pbAnchor'>&gt</a></li>";
		}
		pageNavi += "</ul>";
		
		PetBoardPageData pbpd = new PetBoardPageData(list, pageNavi);
		return pbpd;
	}
	
	@Transactional
	public int insertPetBoard(PetBoard pb, ArrayList<PetBoardFile> fileList) {
		//파일은 pbNo가 필요하기때문에 PetBoard테이블에 insert하는것이 먼저
		int result1 = dao.insertPetBoard(pb);
		int result = 0;
		if(result1>0) {
			int pbNo = dao.selectPbNo();
			for(PetBoardFile pbf : fileList) {
				pbf.setPbNo(pbNo);
				result += dao.insertFile(pbf);
			}
		}else {
			return -1;
		}
		return result;
	}

	public PetBoard selectOnePetBoard(int pbNo) {
		return dao.selectOnePetBoard(pbNo);
	}

	@Transactional
	public int deletePb(int pbNo) {
		int result = dao.deletePb(pbNo);
		return result;
	}

	@Transactional
	public int updatePb(PetBoard pb, ArrayList<PetBoardFile> fileList) {
		//파일은 pbNo가 필요하기때문에 PetBoard테이블에 insert하는것이 먼저
		int result1 = dao.updatePb(pb);
		int result = 0;
		if(result1>0) {
			int pbNo = pb.getPbNo();
			if(fileList.size() != 0) {
				int delResult = dao.deleteFile(pbNo);	//delResult : 0 ~ 2 개 파일 삭제
				if(delResult == 0 || delResult == 1 || delResult == 2) {
					for(PetBoardFile pbf : fileList) {
						pbf.setPbNo(pbNo);
						result += dao.insertFile(pbf);
					}				
				}else {
					return -1;
				}
			}
		}else {
			return -2;
		}
		return result;
	}

}
