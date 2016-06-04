package com.chulung.ciki.models;

import java.io.Serializable;
import java.util.List;

public class CategoryItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6402439650350695624L;
	private String name;
	private String link;
	private List<CategoryItem> categoryItems;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<CategoryItem> getCategoryItems() {
		return categoryItems;
	}

	public void setCategoryItems(List<CategoryItem> categoryItems) {
		this.categoryItems = categoryItems;
	}

}
