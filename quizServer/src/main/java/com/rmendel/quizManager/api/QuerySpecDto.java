package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rmendel.framework.IApiObject;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "querySpec")
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="Defines filter, sort and page criteria for returning items from the data cache")
public class QuerySpecDto implements IApiObject {

	public QuerySpecDto() {
	}

	public QuerySpecDto(FilterSpecDto filter, SortSpecDto sort, PageSpecDto page) {
		this.filter = filter;
		this.sort = sort;
		this.page = page;
	}

	@ApiModelProperty(value = "The object type (read-only)", required = false)
	public String getObjectNotion() {
		return "querySpec";
	}
	public void setObjectNotion() {}

	@ApiModelProperty(value = "Ignored for this object type", required = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Optional filter criteria", required = false)
	public FilterSpecDto getFilter() {
		return filter;
	}
	public void setFilter(FilterSpecDto filter) {
		this.filter = filter;
	}
	
	@ApiModelProperty(value = "Optional sort criteria", required = false)
	public SortSpecDto getSort() {
		return sort;
	}
	public void setSort(SortSpecDto sortElement) {
		this.sort = sortElement;
	}
	
	@ApiModelProperty(value = "Optional page criteria", required = false)
	public PageSpecDto getPage() {
		return page;
	}
	public void setPage(PageSpecDto page) {
		this.page = page;
	}

	private String id = null;
	private FilterSpecDto filter = null;
	private SortSpecDto sort = null;
	private PageSpecDto page = null;
}
