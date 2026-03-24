provider "aws" {
  region = var.aws_region
  # Terraform 會自動抓取環境變數 AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_SESSION_TOKEN
}

resource "aws_dynamodb_table" "online_store" {
  name           = var.table_name
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "pk"
  range_key      = "sk"

  attribute {
    name = "pk"
    type = "S"
  }

  attribute {
    name = "sk"
    type = "S"
  }

  tags = {
    Name        = var.table_name
    Environment = "Dev-KK"
  }
}