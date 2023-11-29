package com.kh.springdb.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/*
MNO	NUMBER(38,0)
MNAME	VARCHAR2(50 BYTE)
MEMAIL	VARCHAR2(100 BYTE)
MBIRTH	DATE
 * */
public class User {
	private int mno;
	private String mname;
	private String memail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mbirth = new Date();
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
}






