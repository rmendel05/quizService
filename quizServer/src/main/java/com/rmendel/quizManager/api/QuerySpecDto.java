package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import com.rmendel.framework.IApiObject;

@XmlRootElement(name = "querySpec")
public class QuerySpecDto implements IApiObject {

	public QuerySpecDto() {
	}

	public QuerySpecDto(FilterSpecDto filter, SortSpecDto sort, PageSpecDto page) {
		this.filter = filter;
		this.sort = sort;
		this.page = page;
	}

	public String getObjectNotion() {
		return "querySpec";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FilterSpecDto getFilter() {
		return filter;
	}
	public void setFilter(FilterSpecDto filter) {
		this.filter = filter;
	}
	
	public SortSpecDto getSort() {
		return sort;
	}
	public void setSort(SortSpecDto sortElement) {
		this.sort = sortElement;
	}
	
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
