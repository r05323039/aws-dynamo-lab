package com.example.dynamo.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@NoArgsConstructor
@DynamoDbBean
public class StoreItem {

    private String pk;
    private String sk;
    private String name;
    private String content;

    @DynamoDbPartitionKey
    public String getPk() { return pk; }

    @DynamoDbSortKey
    public String getSk() { return sk; }

    public String getName() { return name; }
    public String getContent() { return content; }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
