package kr.co.pet.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.pet.model.vo.PetBoard;
import kr.co.pet.model.vo.PetBoardFile;
import kr.co.pet.model.vo.PetRoomReserve;

@Repository
public class PetDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<PetRoomReserve> selectRoomList(String checkinDate) {
		List<PetRoomReserve> list = sqlSession.selectList("pet.selectRoomList",checkinDate);
		return (ArrayList<PetRoomReserve>)list;
	}

	public ArrayList<PetRoomReserve> selectRoomList(HashMap<String, String> map) {
		List<PetRoomReserve> list = sqlSession.selectList("pet.selectRoomList2",map);
		return (ArrayList<PetRoomReserve>)list;
	}

	public String selectRoomCharge(HashMap<String,Integer> map) {
		String total = sqlSession.selectOne("pet.selectRoomCharge",map);
		return total;
	}

	public int insertReserve(PetRoomReserve prr) {
		// TODO Auto-generated method stub
		return sqlSession.insert("pet.insertReserve",prr);
	}

	public ArrayList<PetBoard> pbList(HashMap<String,Integer> map) {
		List<PetBoard> list = sqlSession.selectList("pet.pbList",map);
		return (ArrayList<PetBoard>)list;
	}

	public int totalCount() {
		int result = sqlSession.selectOne("pet.totalCount");
		return result;
	}

	public int insertPetBoard(PetBoard pb) {
		int result = sqlSession.insert("pet.insertPetBoard",pb);
		return result;
	}

	public int selectPbNo() {
		int result = sqlSession.selectOne("pet.selectPbNo");
		return result;
	}

	public int insertFile(PetBoardFile pbf) {
		return sqlSession.insert("pet.insertFile",pbf);
	}

	public PetBoard selectOnePetBoard(int pbNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("pet.selectOnePetBoard",pbNo);
	}

	public int deletePb(int pbNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("pet.deleteOnePetBoard",pbNo);
	}

	public int updatePb(PetBoard pb) {
		// TODO Auto-generated method stub
		return sqlSession.update("pet.updatePb",pb);
	}

	public int updateFile(PetBoardFile pbf) {
		// TODO Auto-generated method stub
		return sqlSession.update("pet.updateFile",pbf);
	}

	public int deleteFile(int pbNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("pet.deleteFile",pbNo);
		
	}

	

	
}
