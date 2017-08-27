package com.finalproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.finalproject.model.ProjectInfo;

public class ProjectData {
	static int numberProjectTestSuite =0; // Number of project test suit from begin count to now
	
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
					// System.out.println(tableRowElements.get(j).select("td").get(0).text().substring(1));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
	//		 System.out.println( projectName.get(i));
			 
			for (int k=numberProjectTestSuite ; k< projectTestSuitName.size(); k++){
//			 System.out.println( projectTestSuitName.get(k));
			 try {
//					System.out.println(projectTestSuitName.get(k));
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
									}
									else{
										countReady++;
									}
								}
								if ("<td>Test Case Readiness</td>"
										.equals(tableRowElements.get(0).select("td").get(l).toString())) {
									if ("Designed".equals(rowItems.get(l).text())) {
										countDesigned++;
									}else{
										countManual++;
									}
								}
							}

						}
						

					} catch (IOException e) {
						e.printStackTrace();
					}

			 }
//			System.out.println("---->"+projectName.get(i));
//			System.out.println("Designed: " + countDesigned);
//			System.out.println("Manual: " + countManual);
//			System.out.println("Automated: " + countAutomated);
//			System.out.println("Ready: " + countReady);
			listProjectInfo.add(new ProjectInfo(projectName.get(i), countDesigned, countReady, countManual, countAutomated));
			numberProjectTestSuite = numberProjectTestSuite+projectTestSuitName.size();
		}
		
		return listProjectInfo;
	}
}
