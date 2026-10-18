package com.ndx.www.mapper;

import com.ndx.www.entity.GoodsStock;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface GoodsStockMapper {
    /**
     * 批量upsert：有则更新，无则插入
     * 联合主键(goods_id,warehouse_id)冲突时，累加库存
     */
    int batchUpsertStock(List<GoodsStock> list);
}
