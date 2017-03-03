package com.chulung.website.dto;

import java.time.YearMonth;

/**
 * 文章数量归档
 * @author hasee
 *
 */
public class ArticleFiling implements Comparable<ArticleFiling> {
	
	//年月
	private YearMonth yearMonth;
	//篇数
	private int count;
	
	
	public ArticleFiling(YearMonth yearMonth, int count) {
		this.yearMonth = yearMonth;
		this.count = count;
	}

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArticleFiling() {
	}

	@Override
	public int compareTo(ArticleFiling o) {
		return this.yearMonth.compareTo(o.yearMonth);
	}
}
