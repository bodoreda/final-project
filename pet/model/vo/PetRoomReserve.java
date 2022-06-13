package kr.co.pet.model.vo;

import lombok.Data;

@Data
public class PetRoomReserve {
	private int rnum;
	private int roomNo;
	private String memberId;
	private int occupied;
	private String petChkin;
	private String petChkout;
	private String petDays;
}
