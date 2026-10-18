package com.ndx.www.service;


import com.ndx.www.entity.GoodsStock;
import com.ndx.www.mapper.GoodsStockMapper;
import com.ndx.www.mapper.GoodsStockSnapshotMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class StockService {

    @Resource
    private GoodsStockSnapshotMapper goodsStockSnapshotMapper;

    @Resource
    private GoodsStockMapper goodsStockMapper;

    /**
     * 接口1：生成库存快照，A表insert into A select B
     */
    public void createStockSnapshot() {
      goodsStockSnapshotMapper.insertSnapshotFromB();
    }

    /**
     * 接口2：入库批量upsert，业务层加synchronized锁，锁对象是mapper
     * ⚠️ 注意：synchronized(goodsStockMapper) 是JVM本地锁，只单实例生效！
     * 多服务器部署时，这个锁无效，必须用分布式锁
     */
    public void batchStockUpsert(List<GoodsStock> stockList) {
        // 锁为mapper对象
        synchronized (goodsStockMapper) {
            goodsStockMapper.batchUpsertStock(stockList);
        }
    }
}
