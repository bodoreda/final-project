package kr.co.pet.model.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PetBoard {
	private int rnum;
	private int pbNo;
	private String pbTitle;
	private String pbWriter;
	private String pbContent;
	private String pbDate;
	private ArrayList<PetBoardFile> fileList;
	private int starScore;
}
