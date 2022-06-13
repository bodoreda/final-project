package kr.co.board.model.vo;

import lombok.Data;

@Data
public class File {
	private int fileNo;
	private String filename;
	private String filepath;
	private int boardNo;
	public File() {
		super();
		// TODO Auto-generated constructor stub
	}
	public File(int fileNo, String filename, String filepath, int boardNo) {
		super();
		this.fileNo = fileNo;
		this.filename = filename;
		this.filepath = filepath;
		this.boardNo = boardNo;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}	
	
}
