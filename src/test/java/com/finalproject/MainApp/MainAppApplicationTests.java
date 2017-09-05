package com.finalproject.MainApp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.finalproject.controller.ProjectData;
import com.finalproject.controller.ProjectInfoController;
import com.finalproject.model.ProjectInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainAppApplicationTests {

	@Autowired
	private ProjectInfoController controller;
	private MockMvc mockMvc;

	@Autowired
	private ProjectData projectData;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testDataTestSuit1() throws Exception {

		controller = mock(ProjectInfoController.class);
		mockMvc.perform(
				get("/project/SuitProject001").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.projectName", is("SuitProject001")))
				.andExpect(jsonPath("$.designed", is(5))).andExpect(jsonPath("$.ready", is(4)))
				.andExpect(jsonPath("$.manual", is(3))).andExpect(jsonPath("$.automated", is(6)));
	}

	@Test
	public void testDataTestSuit2() throws Exception {

		controller = mock(ProjectInfoController.class);
		mockMvc.perform(
				get("/project/SuitProject002").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.projectName", is("SuitProject002")))
				.andExpect(jsonPath("$.designed", is(5))).andExpect(jsonPath("$.ready", is(1)))
				.andExpect(jsonPath("$.manual", is(2))).andExpect(jsonPath("$.automated", is(4)));
	}

	@Test
	public void testDataAllNameProject() throws Exception {

		controller = mock(ProjectInfoController.class);

		mockMvc.perform(
				get("/project/getAllProjectName").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("SuitProject001")))
				.andExpect(jsonPath("$[1].name", is("SuitProject002")));
	}

	/*@Test
	public void testDataTestSuit3() throws Exception {

		controller = mock(ProjectInfoController.class);
		mockMvc.perform(
				get("/project/SuitProject003").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.projectName", is("SuitProject003")))
				.andExpect(jsonPath("$.designed", is(5))).andExpect(jsonPath("$.ready", is(1)))
				.andExpect(jsonPath("$.manual", is(2))).andExpect(jsonPath("$.automated", is(4)));
	}*/

	@Test
	public void checkProjectNameInCurrentTime() throws Exception {
		List<String> list = projectData.getProjectNameCurrent4Week();
		List<String> temp = new ArrayList<String>();
		temp.add("SuitProject001");
		assertEquals(temp.get(0), list.get(0));
	}

	@Test
	public void testGetProjectInfo() throws Exception {
		List<ProjectInfo> listPI = projectData.getProjectInfo();
		List<ProjectInfo> temp = new ArrayList<ProjectInfo>();

		temp.add(new ProjectInfo("SuitProject001", 5, 4, 3, 6));
		assertEquals(temp.get(0).getAutomated(), listPI.get(0).getAutomated());
	}
}
