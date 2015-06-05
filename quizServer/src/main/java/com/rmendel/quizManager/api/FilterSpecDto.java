package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rmendel.framework.IApiObject;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "filterSpec")
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="Defines a logical rule for selecting items to be returned by a query")
public class FilterSpecDto implements IApiObject {

	public FilterSpecDto() {
	}

	public FilterSpecDto(String filterType, String minValue, String maxValue) {
		this.filterType = filterType;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@ApiModelProperty(value = "The object type (read-only)", required = false)
	public String getObjectNotion() {
		return "filterSpec";
	}
	public void setObjectNotion() {}

	@ApiModelProperty(value = "Primary key (read-only)", required = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Type of filter being specified", required = true, allowableValues = "wordCount,distractorCount")
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	@ApiModelProperty(value = "Low inclusive value for specifying a range of integers", required = false)
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	@ApiModelProperty(value = "High inclusive value for specifying a range of integers", required = false)
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
