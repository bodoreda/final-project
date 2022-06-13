package kr.co.board.model.vo;

import java.util.ArrayList;

public class boardPageData {
	private ArrayList<Board> list;
	private String pagenavi;
	public boardPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boardPageData(ArrayList<Board> list, String pagenavi) {
		super();
		this.list = list;
		this.pagenavi = pagenavi;
	}
	public ArrayList<Board> getList() {
		return list;
	}
	public void setList(ArrayList<Board> list) {
		this.list = list;
	}
	public String getPagenavi() {
		return pagenavi;
	}
	public void setPagenavi(String pagenavi) {
		this.pagenavi = pagenavi;
	}

}
