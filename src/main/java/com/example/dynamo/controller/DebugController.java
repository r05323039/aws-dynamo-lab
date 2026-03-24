package com.example.dynamo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@RestController
@RequestMapping("/api/debug")
public class DebugController {
    @Autowired
    private DynamoDbClient dynamoDbClient;

    @GetMapping("/dynamodb-check")
    public ResponseEntity<String> checkDb() {
        try {
            // 嘗試列出資料表來確認連線存活
            dynamoDbClient.listTables();
            return ResponseEntity.ok("DynamoDB 連線正常！");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("連線異常: " + e.getMessage());
        }
    }
}
