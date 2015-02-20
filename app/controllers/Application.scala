package controllers

import play.api._
import play.api.mvc._
import java.io.File

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }
  
  def showViz = Action {
    println("showing viz")
    Ok(views.html.index())
  }
  
  /*
  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("docfile").map { docfile =>
      import java.io.File
      val filename = docfile.filename 
      println("got file" + filename)
      val contentType = docfile.contentType
      docfile.ref.moveTo(new File("/tmp/picture"))
      Ok("File uploaded")
    }.getOrElse {
      Redirect(routes.Application.index).flashing(
        "error" -> "Missing file"
      )
    }
  }*/
  
  def uploadFile = Action(parse.multipartFormData) { request =>
    request.body.file("fileUpload").map { docfile =>
      val videoFilename = docfile.filename
      val contentType = docfile.contentType.get
      docfile.ref.moveTo(new File("/tmp/" + docfile.filename))
    }.getOrElse {
      Redirect(routes.Application.index)
    }
    //Ok("File has been uploaded")
    Ok(views.html.index())
  }

}
