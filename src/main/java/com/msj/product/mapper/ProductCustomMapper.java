package com.msj.product.mapper;

import com.msj.common.dto.ProductDetail;
import com.msj.common.dto.ProductDto;
import com.msj.common.entity.Product;
import com.msj.common.pojo.ProductCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCustomMapper {
    Product queryByUrl(String url);

    int insertSelective(Product record);

    List<ProductCustom> queryProduct(Product product);

    Product queryProductById(Integer id);

    List<ProductDto> queryProductByCategoryId(@Param("categoryId") Integer categoryId, @Param("status") Integer status);
    
    List<ProductDto> searchProduct(@Param("keyword") String keyword, @Param("status") Integer status);

    ProductDetail queryProductDetail(@Param("id") Integer id);

    List<ProductDto> queryProductShoppingCart(@Param("idList") List<Integer> idList);

    Integer queryProductStock(Integer id);
}