package handlers

import javax.inject.Inject

import play.api.http._
import play.api.mvc._
import play.api.routing.Router

class TrailingSlashRequestHandler @Inject()(router: Router,
                                            errorHandler: HttpErrorHandler,
                                            configuration: HttpConfiguration,
                                            filters: HttpFilters)
    extends HttpRequestHandler {

  private val default =
    new DefaultHttpRequestHandler(router, errorHandler, configuration, filters)

  override def handlerForRequest(
      request: RequestHeader): (RequestHeader, Handler) = {
    default.handlerForRequest(removeTrailingSlash(request))
  }

  private def removeTrailingSlash(origReq: RequestHeader): RequestHeader = {
    if (origReq.path.endsWith("/") && origReq.path != "/") {
      val path = origReq.path.stripSuffix("/")
      if (origReq.rawQueryString.isEmpty) {
        origReq.withTarget(origReq.target.withPath(path).withUriString(path))
      } else {
        origReq.withTarget(
          origReq.target
            .withUriString(path + s"?$"$"${origReq.rawQueryString}")
            .withPath(path))
      }
    } else {
      origReq
    }
  }
}
