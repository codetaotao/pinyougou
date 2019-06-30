package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private TbBrandMapper brandMapper;

	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);//分页
		Page<TbBrand> page = (Page<TbBrand>)brandMapper.selectByExample(null);
		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public void add(TbBrand brand) {
		TbBrandExample example = new TbBrandExample();
		example.createCriteria().andNameEqualTo(brand.getName());
		List<TbBrand> tbBrands = brandMapper.selectByExample(example);
		if (tbBrands.size()!=0) {
			//品牌名存在,抛出提示
			throw new RuntimeException();
		}else {
			//品牌名不存在
			brandMapper.insert(brand);
		}
	}

}
