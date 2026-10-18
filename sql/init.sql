-- A表：库存快照表，保存B表每次快照数据【修复自增主键】
CREATE TABLE `goods_stock_snapshot` (
                                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '快照自增主键',
                                        `goods_id` BIGINT NOT NULL COMMENT '商品ID',
                                        `warehouse_id` INT NOT NULL COMMENT '仓库ID',
                                        `goods_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
                                        `stock_num` INT NOT NULL COMMENT '快照时刻库存数量',
                                        `snapshot_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '快照生成时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存快照表 A表';


-- B表：商品仓库库存基准表，联合主键(goods_id, warehouse_id)
CREATE TABLE `goods_stock` (
                               `goods_id` BIGINT NOT NULL COMMENT '商品ID',
                               `warehouse_id` INT NOT NULL COMMENT '仓库ID',
                               `goods_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
                               `stock_num` INT NOT NULL DEFAULT 0 COMMENT '当前库存数量',
                               `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`goods_id`, `warehouse_id`) -- 双字段联合主键：商品+仓库唯一一条库存记录
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品仓库库存基准表 B表';
