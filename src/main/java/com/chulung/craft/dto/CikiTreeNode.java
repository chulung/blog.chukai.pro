package com.chulung.craft.dto;

import java.util.List;

import com.chulung.craft.enumerate.CateLevelEnum;

public class CikiTreeNode {
	private Integer id;
	private String text;
	private CateLevelEnum cateLevel;
	private List<CikiTreeNode> nodes;

	public CikiTreeNode(Integer id, String text, CateLevelEnum cateLevel) {
		super();
		this.id = id;
		this.text = text;
		this.cateLevel = cateLevel;
	}

	public CikiTreeNode(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CateLevelEnum getCateLevel() {
		return cateLevel;
	}

	public void setCateLevel(CateLevelEnum cateLevel) {
		this.cateLevel = cateLevel;
	}

	public List<CikiTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<CikiTreeNode> nodes) {
		this.nodes = nodes;
	}

}
