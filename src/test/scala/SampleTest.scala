package test

import java.net.URL

import akka.actor.ActorSystem
import org.scalatest.{FlatSpec, Matchers}
import akka.stream._
import akka.stream.scaladsl._
import org.scalatest.concurrent.ScalaFutures
import akka.testkit.TestProbe
import sample._

import scala.concurrent.Await
import scala.concurrent.duration._

/*
* References:
*   (Akka Streams) Testing streams (v.2.4.6)
*     -> http://doc.akka.io/docs/akka/2.4.6/scala/stream/stream-testkit.html
*/

class SampleTest extends FlatSpec with Matchers with ScalaFutures {
  import SampleTest._

  implicit val as = ActorSystem("Akka_Streams_QuickStart")
  implicit val materializer = ActorMaterializer()

  implicit val ec = as.dispatcher

  behavior of "Fetching web page via 'akka-streams' and 'akka-http'"

  it should "be possible to read a web resource, line by line" in {
    val probe = TestProbe()

    val sourceUnderTest: Source[String, Any] = Sample.sourceAsByteString(url).via(cutAtLines)

    //...tbd...
    //  Check that
    //    - 1..n 'String's are provided
    //    - none of them contains a newline
    //    - print the beginning of the lines, to see they make sense
  }
}

object SampleTest {
  val url = new URL( "http://www.yle.fi" )
}