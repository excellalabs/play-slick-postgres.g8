package controllers

import dao.ProductsDao
import javax.inject.{Inject, Singleton}
import models.Product
import play.api.libs.json.{Format, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton()
class ProductController @Inject()(
    cc: ControllerComponents,
    productsDao: ProductsDao)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {
  implicit val fmt: Format[Product] = Json.format[Product]

  def getAll: Action[AnyContent] = Action.async { implicit request =>
    val allProductsFuture: Future[Seq[Product]] = productsDao.getAll
    allProductsFuture.map { products =>
      Ok(Json.toJson(products))
    }
  }
}
