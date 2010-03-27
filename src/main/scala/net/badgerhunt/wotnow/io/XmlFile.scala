package net.badgerhunt.wotnow.io

import net.badgerhunt.wotnow.render.WotTable
import java.io.{FileOutputStream, BufferedWriter, File}
import xml.{XML, PrettyPrinter}
import net.badgerhunt.wotnow.domain.{StringToPriority, Wot}

// todo - check out the scala.mobile package in 2.8!

object XmlFile {
  implicit def stringToPriority(s: String) = new StringToPriority(s)
  val userHome = System.getProperty("user.home")
  val file = new File("%s/.wotnow/wots.xml".format(userHome))
  private val formatter = new PrettyPrinter(150, 2)

  def restore: List[Wot] = {
    val xml = XML.loadFile(file)
    val wotNodes = xml \ "wot"
    wotNodes.foldLeft(List(): List[Wot]) {(list, wotNode) =>
      val values = List("@id", "title", "status", "priority").map(subNode => (wotNode \ subNode).text)
      Wot(values(0).toInt, values(1), values(2), values(3).toPriority) :: list
    }
  }

  def save(wots: WotTable) = {
    file.getParentFile.mkdir
    val writer = new BufferedWriter(new java.io.OutputStreamWriter(new FileOutputStream(file)))
    writer.write(formatter.format(wots.asXML))
    writer.close
    Some(file)
  }
}

