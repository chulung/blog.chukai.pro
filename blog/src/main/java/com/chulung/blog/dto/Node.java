package com.chulung.blog.dto;

import java.util.List;

public class Node<T> {
	private int depth;
	private Node<T> parentNode;
	private T item;
	private List<Node<T>> ChildNodes;

	public Node(int depth, Node<T> parentNode, T item, List<Node<T>> childNodes) {
		super();
		this.depth = depth;
		this.parentNode = parentNode;
		this.item = item;
		ChildNodes = childNodes;
	}

	public Node<T> getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node<T> parentNode) {
		this.parentNode = parentNode;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public List<Node<T>> getChildNodes() {
		return ChildNodes;
	}

	public void setChildNodes(List<Node<T>> childNodes) {
		ChildNodes = childNodes;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
