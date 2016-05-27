package test

import java.net.URL

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding._
import akka.http.scaladsl.model.Uri
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.duration._
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures

/*
* References:
*   (Akka Streams) Testing streams (v.2.4.6)
*     -> http://doc.akka.io/docs/akka/2.4.6/scala/stream/stream-testkit.html
*/

class SampleTest extends FlatSpec with Matchers with ScalaFutures {
  import SampleTest._

  implicit val system = ActorSystem("SampleTest")
  implicit val materializer = ActorMaterializer()

  implicit val ec = system.dispatcher

  behavior of "Fetching web page as a stream, via 'akka-http'"

  it should "be possible to read a web resource" in {

    val delimiter: Flow[ByteString, ByteString, NotUsed] =
      Framing.delimiter(
        ByteString("\n"),   // could be "\r\n"
        maximumFrameLength = 100000,
        allowTruncation = true)

    val f = Http().singleRequest(Get(uri)).flatMap { res =>
      val lines = res.entity.dataBytes.via(delimiter).map(_.utf8String)
      lines.runForeach { line =>
        println( "::: line ::: "+ line )
      }
    }

    f.foreach { _ =>
      system.terminate()
    }

    Thread.sleep(5000)
  }
}

object SampleTest {
  // Run 'npm serve data' to serve this file (see 'README.md').
  //
  val uri = Uri( "http://localhost:3000/a.txt" )
}