package com.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.model.ProjectInfo;
import com.finalproject.responsitory.ProjectCompany;

@Service("ProjectService")
public class ProjectServiceImp implements ProjectService {

	@Autowired
	ProjectCompany projectCompany;

	@Override
	public ProjectInfo findbyProjectName(String projectName) {
		// TODO Auto-generated method stub

		List<ProjectInfo> projectInfos = getAll();

		for (ProjectInfo projectInfo : projectInfos) {
			if (projectInfo.getProjectName().equals(projectName)) {
				return projectInfo;
			}
		}

		return null;
	}

	@Override
	public List<ProjectInfo> getAll() {

		List<ProjectInfo> projectInfos = (List<ProjectInfo>) projectCompany.findAll();
		return projectInfos;
	}

	@Override
	public List<String> getProjectName() {
		// TODO Auto-generated method stub
		List<String> projectNames = new ArrayList<>();
		List<ProjectInfo> projectInfos = getAll();

		for (ProjectInfo projectInfo : projectInfos) {
			projectNames.add(projectInfo.getProjectName());
		}
		return projectNames;
	}

}