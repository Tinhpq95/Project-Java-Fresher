package com.finalproject.service;

import java.util.List;

import com.finalproject.model.ProjectInfo;
import com.finalproject.model.ProjectName;

public interface ProjectService {
	public List<ProjectInfo> getAll();
	public ProjectInfo findbyProjectName(String projectName);
	public List<ProjectName> getProjectName();
}
