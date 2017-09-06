package com.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.model.ProjectInfo;
import com.finalproject.model.ProjectName;
import com.finalproject.service.ProjectServiceImp;

@RestController
@RequestMapping(value = "/project")
public class ProjectInfoController {

	@Autowired
	ProjectServiceImp projectService;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/{projectName}")
	public ResponseEntity<?> getProjectInfo(@PathVariable("projectName") String projectName){
		
		ProjectInfo proI = projectService.findbyProjectName(projectName);
		
		return ResponseEntity.ok(proI);
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/getAllProjectName")
	public ResponseEntity<?> getAllProjectName(){
		
		List<ProjectName> projectNames;
		
		projectNames = projectService.getProjectName();
		
		return ResponseEntity.ok(projectNames);
	}

}
