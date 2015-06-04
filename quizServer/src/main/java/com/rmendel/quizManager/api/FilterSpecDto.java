package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import com.rmendel.framework.IApiObject;

@XmlRootElement(name = "filterSpec")
public class FilterSpecDto implements IApiObject {

	public FilterSpecDto() {
	}

	public FilterSpecDto(String filterType, String minValue, String maxValue) {
		this.filterType = filterType;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public String getObjectNotion() {
		return "filterSpec";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	private String id = null;
	private String filterType = null;
	private String minValue = null;
	private String maxValue = null;
}
