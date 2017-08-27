package com.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.model.ProjectInfo;
import com.finalproject.responsitory.ProjectCompany;

@RestController
public class ProjectInfoController {
	
	@Autowired
	ProjectCompany projectCompany;
	
	@RequestMapping(value = "/project/getAll")
	public ResponseEntity<?> getAll(){
		
		List<ProjectInfo> projectInfos = (List<ProjectInfo>) projectCompany.findAll();
		return ResponseEntity.ok(projectInfos);
	}
	
	@RequestMapping(value = "/project/create4test")
	public void create4Test(){
		ProjectData listData = new ProjectData();
		List<ProjectInfo> listProjectInfo = listData.getProjectInfo();
		for (int i =0; i< listProjectInfo.size(); i++){
			projectCompany.save(listProjectInfo.get(i));
			listProjectInfo.get(i).toStringKQ();
		}
	}
	
	
}
