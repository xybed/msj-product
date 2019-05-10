package com.msj.product.controller;

import com.alibaba.fastjson.JSONArray;
import com.msj.common.constant.Constants;
import com.msj.common.constant.Result;
import com.msj.common.constant.ResultEnum;
import com.msj.common.constant.ResultGenerator;
import com.msj.common.dto.ProductDto;
import com.msj.product.service.ProductService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping(value = "/category/{fid}", method = RequestMethod.GET)
    public Result getPCategory(@PathVariable Integer fid){
        if(fid == null)
            fid = 0;
        return ResultGenerator.genSuccessResult(productService.getPCategory(fid));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getProductList(@RequestParam("category_id") Integer categoryId,
                     @RequestParam(name = "page_index", required = false) Integer pageIndex,
                     @RequestParam(name = "page_size", required = false) Integer pageSize){
        if(StringUtils.isEmpty(categoryId)){
            return ResultGenerator.genFailResult(ResultEnum.PARAM_ERROR);
        }
        if(pageIndex == null)
            pageIndex = Constants.PAGE_INDEX;
        if(pageSize == null)
            pageSize = Constants.PAGE_SIZE;
        return ResultGenerator.genSuccessResult(productService.getProductList(categoryId, pageIndex, pageSize));
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Result searchProductList(@RequestParam("keyword") String keyword,
                    @RequestParam("page_index") Integer pageIndex, @RequestParam("page_size") Integer pageSize){
        if(StringUtils.isEmpty(keyword)){
            return ResultGenerator.genFailResult(ResultEnum.PARAM_ERROR);
        }
        if(pageIndex == null)
            pageIndex = Constants.PAGE_INDEX;
        if(pageSize == null)
            pageSize = Constants.PAGE_SIZE;
        return ResultGenerator.genSuccessResult(productService.searchProductList(keyword, pageIndex, pageSize));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getProductDetail(@PathVariable Integer id){
        if(StringUtils.isEmpty(id)){
            return ResultGenerator.genFailResult(ResultEnum.PARAM_ERROR);
        }
        return ResultGenerator.genSuccessResult(productService.getProductDetail(id));
    }

    @RequestMapping(value = "/shopping/cart", method = RequestMethod.GET)
    public List<ProductDto> getProductShoppingCart(@RequestParam("ids") String ids){
        if(StringUtils.isEmpty(ids)){
            return null;
        }
        List<Integer> idList = JSONArray.parseArray(ids, Integer.class);
        return productService.getProductShoppingCart(idList);
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public Integer getProductStock(@RequestParam Integer id){
        if(StringUtils.isEmpty(id)){
            return 0;
        }
        return productService.getProductStock(id);
    }
}
