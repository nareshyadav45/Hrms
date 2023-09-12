package com.hrms.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "main_empjobhistory")
public class EmployeeJobHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "user_id")
	private int userId;

	@Column(name = "position_id")
	private int positionId;

	@Column(name = "position_name")
	private String positionName;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "jobtitle_id")
	private int jobTitleId;

	@Column(name = "jobtitle_name")
	private String jobTitleName;

	@Column(name = "client_name")
	private String clientName;

	@Column(name = "vendor")
	private String vendorName;
	
	@Column(name = "received_amount")
	private double amountRecieved;

	@Column(name = "paid_amount")
	private double amountPaid;

	@Column(name = "start_date")
	private Date fromDate;

	@Column(name = "end_date")
	private Date toDate;
	
	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "modifieddate")
	private Date modifiedDate;
	
	@Column(name = "createdby")
	private int createdBy;
	
	@Column(name = "modifiedby")
	private int modifiedby;

	@Column(name = "isactive")
	private int isactive;
	
	
}
