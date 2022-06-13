package kr.co.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.board.model.vo.Board;
import kr.co.board.model.vo.File;
import kr.co.board.model.vo.boardComment;


@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	//private JdbcTemplate jdbcTemplate;
	public int insertBoard(Board b) {
		int result = sqlSession.insert("board.insertBoard",b);
		return result;
//		String query = "insert into board "
//				+ "values(board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
//		Object[] params = {b.getBoardTitle(),b.getBoardWriter(),b.getBoardContent()};
//		int result = jdbcTemplate.update(query,params);
//		return result;
	}

	public int selectBoardNo() {
		int result = sqlSession.selectOne("board.selectBoardNo");
		return result;
//		String query = "select max(board_no) from board";
//		int boardNo = jdbcTemplate.queryForObject(query, int.class);
//		return boardNo;
	}

	public int insertFile(File f) {
		return sqlSession.insert("board.insertFile",f);
//		String query = "insert into file_tbl values(file_seq.nextval,?,?,?)";
//		Object[] params = {f.getFilename(),f.getFilepath(),f.getBoardNo()};
//		int result = jdbcTemplate.update(query,params);
//		return result;
	}
	public ArrayList<Board> boardList() {
		List<Board> list = sqlSession.selectList("board.boardList");
		return (ArrayList<Board>)list;
//		String query = "select * from board order by 1 desc";
//		List list = jdbcTemplate.query(query, new BoardRowMapper());
//		return list;
	}	

	public Board selectOneBoard(int boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.selectOneBoard",boardNo);
	}
	public int insertBoardComment(boardComment bc) {
		System.out.println(bc.getBoardRef());
		int result = sqlSession.insert("boardComment.boardCommentInsert",bc); // 네임스페이스 . id
		return result;
	}

	public ArrayList<boardComment> boardCommentList(int boardNo) {
		List<boardComment> list = sqlSession.selectList("boardComment.boardCommentList",boardNo);
		return (ArrayList<boardComment>)list;
	}

	public int boardDelete(int boardNo) {
		System.out.println(boardNo);
		int result = sqlSession.delete("board.boardDelete",boardNo);
		return result;
	}

	public int boardUpdate(Board b) {
		return sqlSession.update("board.boardUpdate",b);
	}

	public int boardCommentUpdate(boardComment bc) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardComment.boardCommentUpdate",bc);
	}

	public int boardCommentDelete(int bcNo) {
		int result = sqlSession.delete("boardComment.boardCommentDelete",bcNo);
		System.out.println("dao :"+bcNo);
		return result;
	}

	public int totalCount() {
		int result = sqlSession.selectOne("board.totalCount");
		return result;
	}

	public ArrayList<Board> bpdList(HashMap<String, Integer> map) {
		List<Board> list = sqlSession.selectList("board.bpdList",map);
		return (ArrayList<Board>)list;
	}
}

