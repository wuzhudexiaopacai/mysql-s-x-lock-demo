package com.ndx.www.controller;


import com.ndx.www.entity.GoodsStock;
import com.ndx.www.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private StockService stockService;

    /**
     * 接口1：执行快照任务，将B表数据写入A表
     * POST /stock/snapshot
     */
    @PostMapping("/snapshot")
    public String snapshot() {
        stockService.createStockSnapshot();
        return "快照执行成功";
    }

    /**
     * 接口2：商品入库upsert，无则插入有则更新，synchronized锁mapper
     * POST /stock/batch-upsert
     * 请求体示例：
     * [
     *     {"goodsId":1001,"warehouseId":1,"goodsName":"无线鼠标","stockNum":50},
     *     {"goodsId":1002,"warehouseId":1,"goodsName":"机械键盘","stockNum":20}
     * ]
     */
    @PostMapping("/batch-upsert")
    public String batchUpsert(@RequestBody List<GoodsStock> stockList) {
        stockService.batchStockUpsert(stockList);
        return "入库upsert执行成功";
    }
}
