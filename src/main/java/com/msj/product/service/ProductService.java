package com.msj.product.service;

import com.msj.common.dto.ProductDetail;
import com.msj.common.dto.ProductDto;
import com.msj.common.entity.PCategory;

import java.util.List;

public interface ProductService {
    List<PCategory> getPCategory(Integer fid);

    List<ProductDto> getProductList(Integer categoryId, Integer pageIndex, Integer pageSize);
    
    List<ProductDto> searchProductList(String keyword, Integer pageIndex, Integer pageSize);

    ProductDetail getProductDetail(Integer id);

    List<ProductDto> getProductShoppingCart(List<Integer> idList);

    Integer getProductStock(Integer id);
}
