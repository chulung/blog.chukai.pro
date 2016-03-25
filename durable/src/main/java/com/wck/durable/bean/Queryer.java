package com.wck.durable.bean;

public class Queryer<T> {

	private T bean;
	private String orderBy = "";
	private Integer pageSize=100;
	private Integer page;

	private Queryer(T bean) {
		this.bean = bean;
	}

	public static <T> Queryer<T> of(T bean) {
		return new Queryer<>(bean);
	}

	public String getOrderByString() {
		return orderBy;
	}

	public Queryer<T> orderBy(String filedName) {
		this.orderBy = "order by " + filedName;
		return this;
	}

	public Queryer<T> desc() {
		if (this.orderBy == null) {
			throw new IllegalArgumentException("orderBy filed is null , invoke method orderBy()");
		}
		this.orderBy += " desc";
		return this;
	}

	public Queryer<T> asc() {
		if (this.orderBy == null) {
			throw new IllegalArgumentException("orderBy filed is null , invoke method orderBy()");
		}
		this.orderBy += " asc";
		return this;
	}

	public Queryer<T> page(int page) {
		if (page < 1) {
			throw new IllegalArgumentException("page mast >=1");
		}
		this.page = page;
		return this;
	}

	public Queryer<T> pageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	public Queryer<T> pageIn(PageIn pageIn){
		return this.page(pageIn.getPage()).pageSize(pageIn.getPageSize());
	}
	public int index() {
		return (page - 1) * pageSize;
	}

	public String pagingString() {
		if (this.page == null || this.pageSize == null) {
			return "";
		}
		return " limit " + index() + "," + this.pageSize;
	}

	public T getBean() {
		return bean;
	}
}
