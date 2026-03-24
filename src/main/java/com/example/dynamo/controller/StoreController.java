package com.example.dynamo.controller;

import com.example.dynamo.model.StoreItem;
import com.example.dynamo.repository.StoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreItemRepository repository;

    // 1. 初始化測試資料：塞入一個產品與兩則評論
    @PostMapping("/init")
    public String initData() {
        String productId = "101";

        // 儲存產品 Metadata
        StoreItem product = new StoreItem();
        product.setPk("PROD#" + productId);
        product.setSk("METADATA");
        product.setName("iPhone 15 Pro");
        product.setContent("這是最新的旗艦手機");
        repository.save(product);

        // 儲存第一則評論
        StoreItem rev1 = new StoreItem();
        rev1.setPk("PROD#" + productId);
        rev1.setSk("REV#1711280000"); // 模擬時間戳
        rev1.setContent("拍照超強！");
        repository.save(rev1);

        // 儲存第二則評論
        StoreItem rev2 = new StoreItem();
        rev2.setPk("PROD#" + productId);
        rev2.setSk("REV#1711290000");
        rev2.setContent("續航力普通。");
        repository.save(rev2);

        return "初始化完成：已塞入 1 個產品與 2 則評論到同一個 Partition！";
    }

    @GetMapping("/{id}")
    public List<StoreItem> getProductData(@PathVariable String id) {
        return repository.getProductWithReviews(id);
    }
}