provider "azurerm" {
  features {}
}

resource "azurerm_postgresql_flexible_server" "db" {
  name                = "pg-${var.env}-server"
  resource_group_name = var.resource_group
  location            = var.location
  version             = "14"
  administrator_login          = var.admin_user
  administrator_password       = var.admin_password
  sku_name                     = var.sku
  storage_mb                   = 32768
  backup_retention_days        = 7
  geo_redundant_backup_enabled = var.enable_replication

  authentication {
    password_auth_enabled = true
  }
}

output "postgres_fqdn" {
  value = azurerm_postgresql_flexible_server.db.fqdn
}
