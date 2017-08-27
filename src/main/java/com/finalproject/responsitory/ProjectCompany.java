package com.finalproject.responsitory;

import org.springframework.data.repository.CrudRepository;
import com.finalproject.model.ProjectInfo;

public interface ProjectCompany extends CrudRepository<ProjectInfo, Long> {

}
