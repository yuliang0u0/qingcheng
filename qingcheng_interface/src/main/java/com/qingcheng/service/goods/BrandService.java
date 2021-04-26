package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

/**
 * 品牌业务逻辑层接口
 * @author yuliang0u0
 * @create 2021-04-25 13:26
 */
public interface BrandService {

    List<Brand> findAll();

    PageResult<Brand> findPage(int page, int size);

    List<Brand> findList(Map<String,Object> searchMap);

    PageResult<Brand> findPage(Map<String,Object> searchMap,int page, int size);

    Brand findById(Integer id);

    void add(Brand brand);

    void update(Brand brand);

    void delete(Integer id);

}
