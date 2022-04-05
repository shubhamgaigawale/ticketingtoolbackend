package com.monkdevs.ticketingtool.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Department {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long deptId ;
	private String deptName;

	
	public Department(long deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	

	public long getDeptId() {
		return deptId;
	}



	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
