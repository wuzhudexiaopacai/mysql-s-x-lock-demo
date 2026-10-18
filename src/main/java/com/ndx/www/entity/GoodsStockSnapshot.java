package com.ndx.www.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GoodsStockSnapshot {
    private Long id;
    private Long goodsId;
    private Integer warehouseId;
    private String goodsName;
    private Integer stockNum;
    private LocalDateTime snapshotTime;
}
