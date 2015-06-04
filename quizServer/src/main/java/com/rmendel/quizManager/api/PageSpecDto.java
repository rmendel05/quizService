package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import com.rmendel.framework.IApiObject;

@XmlRootElement(name = "pageSpec")
public class PageSpecDto implements IApiObject {

	public PageSpecDto() {
	}

	public PageSpecDto(Integer rowsPerPage, Integer pageOffset) {
		this.rowsPerPage = rowsPerPage;
		this.pageOffset = pageOffset;
	}

	public String getObjectNotion() {
		return "pageSpec";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public Integer getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	private String id = null;
	private Integer rowsPerPage = null;
	private Integer pageOffset = null;
}
