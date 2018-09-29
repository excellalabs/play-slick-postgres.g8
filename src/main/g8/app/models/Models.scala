package models

case class Message(message: String)

case class Product(id: Option[Int], sku: String, name: String, description: String)
