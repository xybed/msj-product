package com.msj.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.msj.common.constant.Constants;
import com.msj.common.dto.ProductDetail;
import com.msj.common.dto.ProductDto;
import com.msj.common.entity.PCategory;
import com.msj.common.enums.PCategoryLevel;
import com.msj.common.enums.ProductStatus;
import com.msj.product.mapper.PCategoryCustomMapper;
import com.msj.product.mapper.ProductCustomMapper;
import com.msj.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private PCategoryCustomMapper pCategoryCustomMapper;
    @Resource
    private ProductCustomMapper productCustomMapper;

    @Override
    public List<PCategory> getPCategory(Integer fid) {
        if(fid == 0){
            return pCategoryCustomMapper.queryByLevel(PCategoryLevel.ONE.getCode());
        }else {
            return pCategoryCustomMapper.queryByFid(fid);
        }
    }

    @Override
    public List<ProductDto> getProductList(Integer categoryId, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<ProductDto> productList = productCustomMapper.queryProductByCategoryId(categoryId, ProductStatus.SHELF.getCode());
        for(ProductDto product : productList){
            product.setImage(Constants.URL_PREFIX + product.getImage());
        }
        return productList;
    }

    @Override
    public List<ProductDto> searchProductList(String keyword, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<ProductDto> productList = productCustomMapper.searchProduct(keyword, ProductStatus.SHELF.getCode());
        for(ProductDto product : productList){
            product.setImage(Constants.URL_PREFIX + product.getImage());
        }
        return productList;
    }

    @Override
    public ProductDetail getProductDetail(Integer id) {
        ProductDetail productDetail = productCustomMapper.queryProductDetail(id);
        List<String> images = new ArrayList<>();
        for(String image : productDetail.getImages()){
            image = Constants.URL_PREFIX + image;
            images.add(image);
        }
        if(images.size() > 0){
            productDetail.setImage(images.get(0));
        }
        productDetail.setImages(images);
        return productDetail;
    }

    @Override
    public List<ProductDto> getProductShoppingCart(List<Integer> idList) {
        List<ProductDto> productList = productCustomMapper.queryProductShoppingCart(idList);
        productList.forEach(product -> {
            product.setImage(Constants.URL_PREFIX + product.getImage());
        });
        return productList;
    }

    @Override
    public Integer getProductStock(Integer id) {
        return productCustomMapper.queryProductStock(id);
    }
}
