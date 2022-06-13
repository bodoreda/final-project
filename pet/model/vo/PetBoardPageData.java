package kr.co.pet.model.vo;

import java.util.ArrayList;

public class PetBoardPageData {
	private ArrayList<PetBoard> list;
	private String pageNavi;
	public PetBoardPageData(ArrayList<PetBoard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public PetBoardPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<PetBoard> getList() {
		return list;
	}
	public void setList(ArrayList<PetBoard> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
