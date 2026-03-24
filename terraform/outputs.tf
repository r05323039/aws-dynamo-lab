output "dynamodb_table_name" {
  value       = aws_dynamodb_table.online_store.name
  description = "The name of the DynamoDB table"
}

output "dynamodb_table_arn" {
  value       = aws_dynamodb_table.online_store.arn
  description = "The ARN of the DynamoDB table"
}