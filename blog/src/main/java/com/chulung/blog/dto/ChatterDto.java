package com.chulung.blog.dto;

import java.time.YearMonth;
import java.util.List;

import com.chulung.blog.model.Chatter;

public class ChatterDto {
	private YearMonth yearMonth;
	private List<Chatter> chatters;

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}

	public List<Chatter> getChatters() {
		return chatters;
	}

	public void setChatters(List<Chatter> chatters) {
		this.chatters = chatters;
	}

}
