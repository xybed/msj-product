package com.msj.product.mapper;

import com.msj.common.entity.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImageCustomMapper {
    void batchInsert(@Param("list") List<ProductImage> list);
}