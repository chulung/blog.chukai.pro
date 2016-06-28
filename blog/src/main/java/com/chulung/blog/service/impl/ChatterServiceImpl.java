package com.chulung.blog.service.impl;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.blog.dto.ChatterDto;
import com.chulung.blog.mapper.ChatterMapper;
import com.chulung.blog.model.Chatter;
import com.chulung.blog.service.ChatterService;

@Service
public class ChatterServiceImpl implements ChatterService {
	@Autowired
	private ChatterMapper chatterMapper;

	@Override
	public List<ChatterDto> getchatterList() {
		List<ChatterDto> chatterDtos = new ArrayList<>();
		chatterMapper.selectList().parallelStream().collect(Collectors.groupingByConcurrent(c -> {
			return YearMonth.of(c.getCreateTime().getYear(), c.getCreateTime().getMonthValue());
		})).forEach((k, v) -> {
			Collections.sort(v, Comparator.comparing(Chatter::getCreateTime).reversed());
			chatterDtos.add(new ChatterDto(k, v));
		});
		Collections.sort(chatterDtos, Comparator.comparing(ChatterDto::getYearMonth).reversed());
		return chatterDtos;
	}
}
