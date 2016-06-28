package com.chulung.blog.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.blog.dto.TreeNode;
import com.chulung.blog.enumerate.TreeNodeTypeEnum;
import com.chulung.blog.mapper.CikiMapper;
import com.chulung.blog.model.Ciki;
import com.chulung.blog.service.CikiService;

@Service
public class CikiServiceImpl implements CikiService {
	@Autowired
	private CikiMapper cikiMapper;

	@Override
	public List<TreeNode> getCategoryTreeNode() {
		return Arrays.asList(getCikiTreeNodeByParentId(0).get(0));
	}

	private List<TreeNode> getCikiTreeNodeByParentId(Integer parentId) {
		Ciki record = new Ciki();
		record.setParentId(parentId);
		return this.cikiMapper.getCikiTitelListByParentId(record).parallelStream().map(c -> {
			TreeNode node = new TreeNode(c.getId(), c.getTitle());
			if (c.getType() == 0) {
				node.setType(c.getId() == 0 ? TreeNodeTypeEnum.CIKI : TreeNodeTypeEnum.CIKI_CATE);
				node.setNodes(this.getCikiTreeNodeByParentId(c.getId()));
			} else {
				node.setType(TreeNodeTypeEnum.CIKI_TEXT);
			}
			return node;
		}).collect(Collectors.toList());
	}

}
