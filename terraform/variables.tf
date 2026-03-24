variable "aws_region" {
  description = "AWS Region"
  type        = string
  default     = "us-east-1"
}

variable "table_name" {
  description = "DynamoDB Table Name"
  type        = string
  default     = "OnlineStore"
}