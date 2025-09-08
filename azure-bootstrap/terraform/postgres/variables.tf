variable "env" {}
variable "resource_group" {}
variable "location" {}
variable "admin_user" {}
variable "admin_password" {}
variable "sku" { default = "B_Standard_B1ms" }
variable "enable_replication" { default = false }
