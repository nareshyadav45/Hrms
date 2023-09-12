package com.hrms.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.ProjechtRequiredFetchDetails;
//import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.repository.ClientDetailsRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ProjectDetailsRepository;
import com.hrms.repository.SalaryCurrencyRepo;
import com.hrms.request.bean.ManagerRoleReuestBean;
import com.hrms.response.bean.ManagerListResonseBean;
import com.hrms.response.bean.ProjecDetailsResponsebean;
import com.hrms.service.ProjectDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

	// //oldHrms
	@Autowired
	private ProjectDetailsRepository projectRepo;

	@Autowired
	private SalaryCurrencyRepo salaCurrencyRepo;

	@Autowired
	private EntityBeanResponseCommon beanResponse;

	@Autowired
	private ClientDetailsRepository clientRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ManagerListResonseBean managerBean;

	@Autowired
	private ProjecDetailsResponsebean projFetchBean;

	@Override
	public EntityBeanResponseCommon saveProjectDetails(ProjectDetailsEntity projentity) {

		log.info("Entered save project Details in service ");

		// ClientDetailsEntity client =
		// clientRepo.findByClientid(projentity.getClient().getClientid());
		// Optional<ClientDetailsEntity> client =
		// clientRepo.findById(projentity.getClient().getId());
		Optional<ClientDetailsEntity> client = clientRepo.findById(projentity.getClient().getId());

		Optional<SalaryCurrencyEntity> currency = salaCurrencyRepo.findById(projentity.getCurrency().getId());

		// managerList
		// List<EmployeeDetails> Managers = empRepo.findByEmpRole("Manager");
		// EmployeeDetails employeeDetails = Managers.get(0);
		// String empId = employeeDetails.getEmpId();
		// EmployeeDetails emp= empRepo.findByEmpId(empId);

		// List<EmployeeDetails> maangerIdByRole =
		// empRepo.maangerIdByRole("Manager");

		//EmployeeDetails employee = empRepo.findByEmpId(projentity.getEmployee().getEmpId());
		EmployeeDetails manager = empRepo.findByEmpId(projentity.getManager().getEmpId());
		//empRepo.find
		// String empRole = employee.getEmpRole();
		// String reportingManagerId = employee.getReportingManagerId();


		ProjectDetailsEntity project=new ProjectDetailsEntity();
		if (client != null && currency != null) {
			project.setCreated_date(LocalDateTime.now());
			project.setClient(client.get());
			project.setCurrency(currency.get());
			project.setManager(manager);
			project.setCreated_by(1);
			project.setIsactive(projentity.getIsactive());
			project.setCreated_date(LocalDateTime.now());
			project.setDescription(projentity.getDescription());
			project.setEnddate(projentity.getEnddate());
			project.setProject_type(projentity.getProject_type());
			project.setProjectId(projentity.getProjectId());
			project.setProjectName(projentity.getProjectName());
			project.setProjectstatus(projentity.getProjectstatus());
			project.setStartdate(projentity.getStartdate());

			ProjectDetailsEntity save = projectRepo.save(project);
			log.info("successfully  saved project Details in service ");

			if (save != null) {
				beanResponse.setMsg("Successfully Project Details Saved");
				beanResponse.setStatus(true);
			} else {
				beanResponse.setMsg("ProjectDeatails not saved");
				beanResponse.setStatus(false);
				log.info("failed to   save project Details in service ");

			}
		} else {
			beanResponse.setMsg("No Clients : and Currency:  details not found  " + projentity.getClient().getId()
					+ projentity.getCurrency().getId());
			beanResponse.setStatus(false);

		}
		return beanResponse;
	}

	// @Override
	public EntityBeanResponseCommon updateProjectDetails(int pid, ProjectDetailsEntity entity) {
		log.info("Entered update project Details in service ");

		Optional<ProjectDetailsEntity> updateEntity = projectRepo.findById(pid);
		ProjectDetailsEntity entityDB = updateEntity.get();
		if (entityDB != null) {
			entityDB.setDescription(entity.getDescription());
			entityDB.setEnddate(entity.getEnddate());
			entityDB.setStartdate(entity.getStartdate());
			entityDB.setIsactive(entity.getIsactive());
			entityDB.setProjectName(entity.getProjectName());
			entityDB.setProjectstatus(entity.getProjectstatus());
			entityDB.setProject_type(entity.getProject_type());
			entityDB.setModified_by(1);
            entityDB.setModifiedDate(LocalDateTime.now());
			projectRepo.save(entityDB);
			log.info("successfully  updated project Details in service ");

			beanResponse.setMsg("Successfully Updated Project Details of Project Id :" + pid);
			beanResponse.setStatus(true);
		} else {
			beanResponse.setMsg("ProjectDetails Not Updated");
			beanResponse.setStatus(false);
			log.info("failed to  updated project Details in service ");
		}

		return beanResponse;
	}

	// oldhrms
	// specificFiledsOfprojectsByProjectId
	@Override
	public ProjecDetailsResponsebean getAllProjectsByClientId(int id) {
		log.info("Entered fetch  project Details by client id in service ");

		// ClientDetailsEntity clientInfo = clientRepo.findByClientid(id);
		Optional<ClientDetailsEntity> clientInfo = clientRepo.findById(id);

		ProjechtRequiredFetchDetails res;
		List<ProjechtRequiredFetchDetails> list = new ArrayList<ProjechtRequiredFetchDetails>();
		
		List<ProjectDetailsEntity> projectList = projectRepo.findByClient(clientInfo.get().getId());
		for (ProjectDetailsEntity proj : projectList) {
			res = new ProjechtRequiredFetchDetails();
			res.setProjectId(proj.getProjectId());
			res.setClientid(proj.getClient().getId());
			res.setDescription(proj.getDescription());
			res.setCurrencyid(proj.getCurrency().getId());
			res.setManagerId(proj.getManager().getEmpId());
			res.setEnddate(proj.getEnddate());
			res.setIsactive(proj.getIsactive());
			res.setProjectName(proj.getProjectName());
			res.setProject_type(proj.getProject_type());
			res.setStartdate(proj.getStartdate());
			res.setProjectstatus(proj.getProjectstatus());

			boolean listProjectDeatils = list.add(res);
			if (listProjectDeatils != false) {
				projFetchBean.setMessage("successfully fetch");
				projFetchBean.setStatus(true);
				projFetchBean.setListProjectBean(list);
			} else {

				projFetchBean.setMessage("failed fetch");
				projFetchBean.setStatus(false);
			}

			log.info("successfully fteched  project Details in service ");
		}

		return projFetchBean;

	}

	@Override
	public ProjecDetailsResponsebean getAllProjects() {

		ProjechtRequiredFetchDetails bean;
		List<ProjechtRequiredFetchDetails> listProj = new ArrayList<>();

		List<ProjectDetailsEntity> projects = projectRepo.findAll();
		if (projects != null) {
			for (ProjectDetailsEntity proj : projects) {
				bean = new ProjechtRequiredFetchDetails();
				bean.setProjectId(proj.getProjectId());
				bean.setProjectName(proj.getProjectName());
				bean.setClientid(proj.getClient().getId());
				bean.setManagerId(proj.getManager().getEmpId());
				bean.setCurrencyid(proj.getCurrency().getId());
				bean.setStartdate(proj.getStartdate());
				bean.setIsactive(proj.getIsactive());
				bean.setProject_type(proj.getProject_type());
				bean.setProjectstatus(proj.getProjectstatus());
				bean.setDescription(proj.getDescription());
				bean.setCreated_by(proj.getCreated_by());
				bean.setCreated_date(proj.getCreated_date());
				bean.setEnddate(proj.getEnddate());
				bean.setModified_by(proj.getModified_by());
				bean.setModifiedDate(proj.getModifiedDate());
				
				boolean add = listProj.add(bean);
				if (add != false) {

					projFetchBean.setMessage("successfully fetch");
					projFetchBean.setStatus(true);
					projFetchBean.setListProjectBean(listProj);
				} else {

					projFetchBean.setMessage("failed fetch");
					projFetchBean.setStatus(false);
				}
			}
		}
		return projFetchBean;
	}

	@Override
	public ManagerListResonseBean getAllManager(ManagerRoleReuestBean reqBean) {
		List<EmployeeDetails> listOfManager = empRepo.maangerIdByRole(reqBean.getManagerRole());

		if (!listOfManager.isEmpty()) {
			managerBean.setMessage("successfully fetched List of Mangers ");
			managerBean.setStatus(true);
			managerBean.setListOfdetails(listOfManager);

		} else {
			managerBean.setMessage("failed to retrive the details ");
			managerBean.setStatus(false);
		}
		return managerBean;
	}

	@Override
	public ManagerListResonseBean getAllManager(String mangerRole) {
		List<EmployeeDetails> listOfManager = empRepo.maangerIdByRole(mangerRole);
		// List<EmployeeDetails> listOfManager = empRepo.findByEmpRole(mangerRole);
		if (!listOfManager.isEmpty()) {
			managerBean.setMessage("successfully fetched List of Mangers ");
			managerBean.setStatus(true);
			managerBean.setListOfdetails(listOfManager);
		} else {
			managerBean.setMessage("failed to retrive the details ");
			managerBean.setStatus(false);
		}
		return managerBean;
	}

}
