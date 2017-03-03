package com.chulung.website.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.website.dto.CikiTreeNode;
import com.chulung.website.dto.JsonResult;
import com.chulung.website.enumerate.CateLevelEnum;
import com.chulung.website.mapper.CikiMapper;
import com.chulung.website.model.Ciki;
import com.chulung.website.service.CikiService;
import com.chulung.ciki.processor.TemplateProcessor;

@Service
public class CikiServiceImpl extends BaseService implements CikiService {
	@Autowired
	private CikiMapper cikiMapper;
	@Autowired
	private TemplateProcessor templateProcessor;

	@Override
	public List<CikiTreeNode> getCategoryTreeNode() {
		return Arrays.asList(getCikiTreeNodeByParentId(0).get(0));
	}

	private List<CikiTreeNode> getCikiTreeNodeByParentId(Integer parentId) {
		Ciki record = new Ciki();
		record.setParentId(parentId);
		return this.cikiMapper.getCikiTitelListByParentId(record).parallelStream().map(c -> {
			CikiTreeNode node = new CikiTreeNode(c.getId(), c.getTitle());
			if (c.getCateLevel() != CateLevelEnum.ITEM) {
				node.setNodes(this.getCikiTreeNodeByParentId(c.getId()));
			}
			node.setCateLevel(c.getCateLevel());
			return node;
		}).collect(Collectors.toList());
	}

	@Override
	public JsonResult<Ciki> getCikiById(Integer id) {
		Ciki record = new Ciki();
		record.setId(id);
		Ciki ciki = this.cikiMapper.selectOne(record);
		return ciki == null ? JsonResult.ofFailure("文章不存在") : JsonResult.ofSuccess(ciki);
	}

	@Override
	public void addCiki(Ciki ciki) {
		checkExistBlank(ciki);
		checkExistBlank(ciki.getId(), ciki.getTitle());
		ciki.setParentId(ciki.getId());
		ciki.setId(null);
		Ciki record = this.cikiMapper.selectByPrimaryKey(ciki.getParentId());
		if (record == null || record.getCateLevel() == CateLevelEnum.ITEM) {
			return;
		}
		ciki.setCateLevel(record.getCateLevel() == CateLevelEnum.L1 ? CateLevelEnum.L2 : CateLevelEnum.ITEM);
		ciki.setCreateTime(LocalDateTime.now());
		ciki.setUpdateTime(LocalDateTime.now());
		this.cikiMapper.insertSelective(ciki);
	}

	@Override
	public void updateCiki(Ciki ciki) {
		checkExistBlank(ciki.getId());
		ciki.setUpdateTime(LocalDateTime.now());
		if (this.cikiMapper.updateByPrimaryKeySelective(ciki) == 1) {
			try {
				this.templateProcessor.processor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
