package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import com.rmendel.framework.IApiObject;

@XmlRootElement(name = "sortSpec")
public class SortSpecDto implements IApiObject {

	public SortSpecDto() {
	}

	public SortSpecDto(String sortByAttribute) {
		this.sortByAttribute = sortByAttribute;
	}

	public String getObjectNotion() {
		return "sortSpec";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSortByAttribute() {
		return sortByAttribute;
	}
	public void setSortByAttribute(String sortByAttribute) {
		this.sortByAttribute = sortByAttribute;
	}

	private String id = null;
	private String sortByAttribute = null;
}
