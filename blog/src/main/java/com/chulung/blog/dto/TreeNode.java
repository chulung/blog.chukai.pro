package com.chulung.blog.dto;

import java.util.List;

import com.chulung.blog.enumerate.TreeNodeTypeEnum;

public class TreeNode {
	private Integer id;
	private String text;
	private TreeNodeTypeEnum type;
	private List<TreeNode> nodes;

	public TreeNode(Integer id, String text, TreeNodeTypeEnum i) {
		super();
		this.id = id;
		this.text = text;
		this.type = i;
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

	public TreeNodeTypeEnum getType() {
		return type;
	}

	public void setType(TreeNodeTypeEnum type) {
		this.type = type;
	}

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

}
