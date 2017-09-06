package com.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finalproject.model.ProjectInfo;
import com.finalproject.model.ProjectName;
import com.finalproject.repository.ProjectCompany;

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
	public List<ProjectName> getProjectName() {
		// TODO Auto-generated method stub
		List<ProjectName> projectNames = new ArrayList<>();
		List<ProjectInfo> projectInfos = getAll();

		for (ProjectInfo projectInfo : projectInfos) {
			projectNames.add(new ProjectName(projectInfo.getProjectName()));
		}
		return projectNames;
	}

}
