package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rmendel.framework.IApiObject;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "pageSpec")
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="Defines a range of items to be returned by a query")
public class PageSpecDto implements IApiObject {

	public PageSpecDto() {
	}

	public PageSpecDto(Integer rowsPerPage, Integer pageOffset) {
		this.rowsPerPage = rowsPerPage;
		this.pageOffset = pageOffset;
	}

	@ApiModelProperty(value = "The object type (read-only)", required = false)
	public String getObjectNotion() {
		return "pageSpec";
	}
	public void setObjectNotion() {}

	@ApiModelProperty(value = "Ignored for this object type", required = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Number of rows to return for each page", required = true)
	public Integer getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	@ApiModelProperty(value = "Starting from 0, specifies which page to return", required = true)
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
