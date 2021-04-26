package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuliang0u0
 * @create 2021-04-25 13:29
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference // 注入远程的接口，本地的用autowire
    private BrandService brandService;

    @GetMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService.findPage(page,size);
    }

    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String,Object> searchMap){
        return brandService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Brand> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return brandService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Brand findById(Integer id){
        return brandService.findById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        // 测试公共异常类
//        int i = 10 / 0;
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        brandService.delete(id);
        return new Result();
    }


}
