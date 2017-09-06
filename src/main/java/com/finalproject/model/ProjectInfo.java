package com.finalproject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProjectInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "projectName")
	private String projectName;

	@Column(name = "designed")
	private long designed;
	@Column(name = "ready")
	private long ready;
	@Column(name = "manual")
	private long manual;
	@Column(name = "automated")
	private long automated;

	public ProjectInfo() {
	}

	public ProjectInfo(String projectName, long designed, long ready, long manual, long automated) {
		super();
		this.projectName = projectName;
		this.designed = designed;
		this.ready = ready;
		this.manual = manual;
		this.automated = automated;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public long getDesigned() {
		return designed;
	}

	public void setDesigned(long designed) {
		this.designed = designed;
	}

	public long getReady() {
		return ready;
	}

	public void setReady(long ready) {
		this.ready = ready;
	}

	public long getManual() {
		return manual;
	}

	public void setManual(long manual) {
		this.manual = manual;
	}

	public long getAutomated() {
		return automated;
	}

	public void setAutomated(long automated) {
		this.automated = automated;
	}

	public void toStringKQ() {
		System.out.println("Name Project: " + getProjectName() + "\n" + "Desgined: " + getDesigned() + "\n" + "Ready: "
				+ getReady() + "\n" + "Manual: " + getManual() + "\n" + "Automated: " + getAutomated());
	}
}
