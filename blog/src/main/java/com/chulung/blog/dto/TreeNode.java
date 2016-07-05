package com.chulung.blog.dto;

import java.util.List;

import com.chulung.blog.enumerate.CateLevelEnum;

public class TreeNode {
	private Integer id;
	private String text;
	private CateLevelEnum cateLevel;
	private List<TreeNode> nodes;

	public TreeNode(Integer id, String text, CateLevelEnum cateLevel) {
		super();
		this.id = id;
		this.text = text;
		this.cateLevel = cateLevel;
	}

	public TreeNode(Integer id, String text) {
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

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

}
