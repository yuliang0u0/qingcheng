package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author yuliang0u0
 * @create 2021-04-25 13:27
 */
@Service // 注意选择Dubbo下的
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询列表
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectAll();
        return new PageResult<Brand>(brands.getTotal(),brands.getResult());
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return brandMapper.selectByExample(example);
    }

    /**
     * 构造查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            if (searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
                criteria.andEqualTo("letter", searchMap.get("letter"));
            }
        }
        return example;
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        return new PageResult<Brand>(brands.getTotal(),brands.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param brand
     */
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    /**
     * 修改
     * @param brand
     */
    public void update(Brand brand) {
        // Selective：若传入的brand的某属性值为null，则数据库中对应记录的该字段还为原值，不会被更新为null（也叫忽略null值）
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }


}
