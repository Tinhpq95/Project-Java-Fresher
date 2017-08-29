package com.finalproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finalproject.model.ProjectInfo;
import com.finalproject.repository.ProjectCompany;

@Component
public class ProjectData {
	private int numberProjectTestSuite = 0; // Number of project test suit from
											// begin count to now

	private List<String> getProjectName() {

		List<String> projectName = new ArrayList<>();
		List<String> urls = new ArrayList<>();

		String url = "http://localhost/FrontPage";

		try {
			Document doc = Jsoup.connect(url).get();
			Elements tableElements = doc.select("table");
			for (int i = 0; i < tableElements.size(); i++) {
				projectName.add(tableElements.get(i).select("td").get(1).text().substring(1));
				// System.out.println(tableElements.get(i).select("td").get(1).text().substring(1));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return projectName;
	}

	public List<ProjectInfo> getProjectInfo() {

		List<ProjectInfo> listProjectInfo = new ArrayList<>();
		List<String> projectName = getProjectName();
		List<String> projectTestSuitName = new ArrayList<>();
		String url = "http://localhost/FrontPage.";

		for (int i = 0; i < projectName.size(); i++) {
			int countDesigned = 0, countReady = 0, countManual = 0, countAutomated = 0;
			try {
				Document doc = Jsoup.connect(url + projectName.get(i)).get();
				Elements tableElements = doc.select("table");
				Elements tableRowElements = tableElements.select(":not(thead) tr");

				for (int j = 1; j < tableRowElements.size(); j++) {
					projectTestSuitName.add(url + projectName.get(i) + "."
							+ tableRowElements.get(j).select("td").get(0).text().substring(1));
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			for (int k = numberProjectTestSuite; k < projectTestSuitName.size(); k++) {

				try {
					// System.out.println(projectTestSuitName.get(k));
					Document doc = Jsoup.connect(projectTestSuitName.get(k)).get();
					Elements tableElements = doc.select("table");
					Elements tableRowElements = tableElements.select(":not(thead) tr");

					for (int n = 2; n < tableRowElements.size(); n++) {

						Element row = tableRowElements.get(n);
						Elements rowItems = row.select("td");
						for (int l = 0; l < rowItems.size(); l++) {
							if ("<td>Automated?</td>".equals(tableRowElements.get(0).select("td").get(l).toString())) {
								if ("Yes".equals(rowItems.get(l).text())) {
									countAutomated++;
								} else {
									countManual++;
								}
							}
							if ("<td>Test Case Readiness</td>"
									.equals(tableRowElements.get(0).select("td").get(l).toString())) {
								if ("Designed".equals(rowItems.get(l).text())) {
									countDesigned++;
								} else {

									countReady++;
								}
							}
						}

					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			listProjectInfo
					.add(new ProjectInfo(projectName.get(i), countDesigned, countReady, countManual, countAutomated));
			numberProjectTestSuite = numberProjectTestSuite + projectTestSuitName.size();
		}

		return listProjectInfo;
	}
	
	@Autowired
	ProjectCompany projectCompany;
	
	@Scheduled(fixedRate = 600000)
	public void reportCurrentTime() {
		ProjectData listData = new ProjectData();
		List<ProjectInfo> listProjectInfo = listData.getProjectInfo();
		for (int i = 0; i < listProjectInfo.size(); i++) {
			projectCompany.save(listProjectInfo.get(i));
			listProjectInfo.get(i).toStringKQ();
			System.out.println("AUTOOOOO");
		}
	}
}
