package kr.co.board.model.vo;

import lombok.Data;

@Data
public class boardComment {
	private int bcNo;
	private int bcLevel;
	private String bcWriter;
	private String bcContent;
	private String bcDate;
	private int boardRef;
	private int bcRef;
	public boardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boardComment(int bcNo, int bcLevel, String bcWriter, String bcContent, String bcDate, int boardRef,
			int bcRef) {
		super();
		this.bcNo = bcNo;
		this.bcLevel = bcLevel;
		this.bcWriter = bcWriter;
		this.bcContent = bcContent;
		this.bcDate = bcDate;
		this.boardRef = boardRef;
		this.bcRef = bcRef;
	}
	public int getBcNo() {
		return bcNo;
	}
	public void setBcNo(int bcNo) {
		this.bcNo = bcNo;
	}
	public int getBcLevel() {
		return bcLevel;
	}
	public void setBcLevel(int bcLevel) {
		this.bcLevel = bcLevel;
	}
	public String getBcWriter() {
		return bcWriter;
	}
	public void setBcWriter(String bcWriter) {
		this.bcWriter = bcWriter;
	}
	public String getBcContent() {
		return bcContent;
	}
	public void setBcContent(String bcContent) {
		this.bcContent = bcContent;
	}
	public String getBcDate() {
		return bcDate;
	}
	public void setBcDate(String bcDate) {
		this.bcDate = bcDate;
	}
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public int getBcRef() {
		return bcRef;
	}
	public void setBcRef(int bcRef) {
		this.bcRef = bcRef;
	}
	public String getBcContentBr() {
		return bcContent.replaceAll("\r\n", "<br>");
	}
}
