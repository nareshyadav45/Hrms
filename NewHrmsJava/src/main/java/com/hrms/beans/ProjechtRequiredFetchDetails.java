package com.hrms.beans;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProjechtRequiredFetchDetails{
	
	private int projectId;
	
	private String projectName;

	private String projectstatus;
	
	private String description;
	
	private int clientid;
	
	private int  currencyid;
	
	private String ManagerId;
	
	private String project_type;
	
    //private Boolean leadAppove;
    
    //private int estimatedhours;
    
    private LocalDate startdate;
    
    private LocalDate enddate;
    
    private Boolean isactive;	
    
   // private int baseProject;
    
    private int created_by;
    
    private int modified_by;
    
    private LocalDateTime created_date;
    
    private LocalDateTime modifiedDate;
    
    //private LocalDate initiated_date;
    
    //private LocalDate hold_date;
    
    //private LocalDate completed_date;
    
    
    
    

}
