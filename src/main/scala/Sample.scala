package sample

import java.net.URL

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.Materializer
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

/*
* References:
*   Streams Cookbook (v.2.4.2)
*     -> http://doc.akka.io/docs/akka/2.4.2/scala/stream/stream-cookbook.html#Parsing_lines_from_a_stream_of_ByteStrings
*/

object Sample {

  /*
  * Source to access a web page
  *
  * Note: I don't really wish to materialize the source, yet. How to create a generic "recipe" 'Source'
  *     for fetching a web page?
  */
  def sourceAsByteString(url: URL)(implicit as: ActorSystem, mat: Materializer): Source[ByteString, Any] = {
    implicit val ec: ExecutionContext = as.dispatcher

    val req: HttpRequest = HttpRequest( uri = url.toString )

    // Q: Why does this become a 'Future' - I'd simply want a 'Source' of 'String's. AKa240516
    //
    val tmp: Source[ByteString, Any] = Http().singleRequest(req).map( resp => resp.entity.dataBytes )
    tmp
  }

  val cutAtLines: Flow[ByteString, String, NotUsed] = {

    Flow[ByteString]
      .via( Framing.delimiter(
        ByteString("\r\n"), maximumFrameLength = 1024, allowTruncation = false
      ))
      .map(_.utf8String)
  }
}
