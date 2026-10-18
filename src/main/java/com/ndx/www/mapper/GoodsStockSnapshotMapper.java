package com.ndx.www.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsStockSnapshotMapper {
    /**
     * 将B表goods_stock全部数据批量插入快照表A
     */
    int insertSnapshotFromB();
}
