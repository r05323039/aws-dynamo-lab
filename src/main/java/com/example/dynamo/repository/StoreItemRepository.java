package com.example.dynamo.repository;

import com.example.dynamo.model.StoreItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class StoreItemRepository {
    private final DynamoDbTable<StoreItem> table;

    public StoreItemRepository(DynamoDbEnhancedClient enhancedClient) {
        this.table = enhancedClient.table("OnlineStore", TableSchema.fromBean(StoreItem.class));
        try {
            System.out.println("正在檢查或建立 OnlineStore 資料表...");
            this.table.createTable();
        } catch (Exception e) {
            // 如果表已經存在，這行會噴錯，我們直接 catch 掉不處理
            System.out.println("資料表已存在或建立中。");
        }
    }

    // 1. Create: 新增任何項（產品或評論）
    public void save(StoreItem item) {
        table.putItem(item);
    }

    public List<StoreItem> getProductWithReviews(String productId) {
        String partitionKey = "PROD#" + productId;

        // Query 條件：PK 等於特定值。這會掃描同一個分區內的所有 SK
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(k -> k.partitionValue(partitionKey));

        return table.query(queryConditional)
                .items()
                .stream()
                .collect(Collectors.toList());
    }

    public void delete(String pk, String sk) {
        table.deleteItem(r -> r.key(k -> k.partitionValue(pk).sortValue(sk)));
    }
}
