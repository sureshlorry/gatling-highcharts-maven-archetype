package GoogleSimulation.simulations

//import com.sun.deploy.config.Config
import com.typesafe.config.{Config, ConfigFactory}
import scala.collection.JavaConversions._

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")

    val repeat = ConfigFactory.load("application.conf")
      .getInt("gsConf.repeat")
    println(repeat)
   /* val configMap = config.entrySet().toList.map(
      entry =>(entry.getKey(),entry.getValue())
    ).toMap

    println {configMap.get("first")}

    println {configMap.get("last")}*/
  }
}