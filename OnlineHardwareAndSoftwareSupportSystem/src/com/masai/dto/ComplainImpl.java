package com.masai.dto;

public class ComplainImpl implements Complain {

	private int cSno;
	private int cTicketNo;
	private String type;
	private String cDesc;
	private String cStatus;
	private String cAssignEngg;
	private String cByEmployee;

	@Override
	public int getcSno() {
		return cSno;
	}

	@Override
	public void setcSno(int cSno) {
		this.cSno = cSno;
	}

	@Override
	public int getcTicketNo() {
		return cTicketNo;
	}

	@Override
	public void setcTicketNo(int cTicketNo) {
		this.cTicketNo = cTicketNo;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getcDesc() {
		return cDesc;
	}

	@Override
	public void setcDesc(String cDesc) {
		this.cDesc = cDesc;
	}

	@Override
	public String getcStatus() {
		return cStatus;
	}

	@Override
	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}

	@Override
	public String getcAssignEngg() {
		return cAssignEngg;
	}

	@Override
	public void setcAssignEngg(String cAssignEngg) {
		this.cAssignEngg = cAssignEngg;
	}

	@Override
	public String getcByEmployee() {
		return cByEmployee;
	}

	@Override
	public void setcByEmployee(String cByEmployee) {
		this.cByEmployee = cByEmployee;
	}

	public ComplainImpl() {

	}

	public ComplainImpl(int cTicketNo, String type, String cDesc, String cStatus, String cAssignEngg) {
		this.cTicketNo = cTicketNo;
		this.type = type;
		this.cDesc = cDesc;
		this.cStatus = cStatus;
		this.cAssignEngg = cAssignEngg;
	}

	public ComplainImpl(int cSno, int cTicketNo, String type, String cDesc, String cStatus, String cAssignEngg,
			String cByEmployee) {

		this.cSno = cSno;
		this.cTicketNo = cTicketNo;
		this.type = type;
		this.cDesc = cDesc;
		this.cStatus = cStatus;
		this.cAssignEngg = cAssignEngg;
		this.cByEmployee = cByEmployee;
	}

	@Override
	public String toString() {
		return "ComplainImpl [cSno=" + cSno + ", cTicketNo=" + cTicketNo + ", cDesc=" + cDesc + ", cStatus=" + cStatus
				+ ", cAssignEngg=" + cAssignEngg + ", cByEmployee=" + cByEmployee + "]";
	}

}
