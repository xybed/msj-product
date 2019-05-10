package com.msj.product.mapper;

import com.msj.common.entity.PCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PCategoryCustomMapper {
    void batchInsert(@Param("list") List<PCategory> list);

    List<PCategory> queryByLevel(Integer level);

    List<PCategory> queryByFid(Integer fid);
}