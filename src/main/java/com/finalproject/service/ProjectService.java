package com.finalproject.service;

import java.util.List;

import com.finalproject.model.ProjectInfo;

public interface ProjectService {
	public List<ProjectInfo> getAll();
	public ProjectInfo findbyProjectName(String projectName);
	public List<String> getProjectName();
}
