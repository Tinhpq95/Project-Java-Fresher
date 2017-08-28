package com.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.model.ProjectInfo;
import com.finalproject.responsitory.ProjectCompany;

@RestController
public class ProjectInfoController {

	@Autowired
	ProjectCompany projectCompany;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/project/getAll")
	public ResponseEntity<?> getAll() {

		List<ProjectInfo> projectInfos = (List<ProjectInfo>) projectCompany.findAll();
		return ResponseEntity.ok(projectInfos);
	}

}
