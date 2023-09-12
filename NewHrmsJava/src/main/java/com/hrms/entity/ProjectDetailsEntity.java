package com.hrms.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "tm_projects")
public class ProjectDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int projectId;

	@Column(name = "project_Name")
	private String projectName;

	@Column(name = "project_status")
	private String projectstatus;

	@Column(name = "description")
	private String description;

	@Column(name = "base_project")
	private int baseProject;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private ClientDetailsEntity client;
	
	//@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="Manager_id",referencedColumnName = "emp_id")
	private EmployeeDetails manager;

	@ManyToOne
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private SalaryCurrencyEntity currency;

	private String project_type;

	@Column(name = "lead_approve_ts")
	private Boolean leadapprove;

	@Column(name = "estimated_hrs")
	private int estimatedhours;

	@Column(name = "start_date")
	private LocalDate startdate;

	@Column(name = "end_date")
	private LocalDate enddate;

	@Column(name = "is_active")
	private Boolean isactive;

	@Column(name = "created_by")
	private int created_by;

	@Column(name = "modified_by")
	private int modified_by;

	@Column(name = "created")
	private LocalDateTime created_date;

	@Column(name = "modified")
	private LocalDateTime modifiedDate;

	@Column(name = "initiated_date")
	private LocalDate initiated_date;


	@Column(name = "hold_date")
	private LocalDate hold_date;

	@Column(name = "completed_date")
	private LocalDate completed_date;

}
