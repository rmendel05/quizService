package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rmendel.framework.IApiObject;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "sortSpec")
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="Defines the order of items to be returned by a query")
public class SortSpecDto implements IApiObject {

	public SortSpecDto() {
	}

	public SortSpecDto(String sortByAttribute) {
		this.sortByAttribute = sortByAttribute;
	}

	@ApiModelProperty(value = "The object type (read-only)", required = false)
	public String getObjectNotion() {
		return "sortSpec";
	}
	public void setObjectNotion() {}

	@ApiModelProperty(value = "Ignored for this object type", required = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Low value for specifying a range of integers", required = true, allowableValues = "wordCount")
	public String getSortByAttribute() {
		return sortByAttribute;
	}
	public void setSortByAttribute(String sortByAttribute) {
		this.sortByAttribute = sortByAttribute;
	}

	private String id = null;
	private String sortByAttribute = null;
}
