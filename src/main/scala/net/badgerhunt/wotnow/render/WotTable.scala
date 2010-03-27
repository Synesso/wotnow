package net.badgerhunt.wotnow.render

import net.badgerhunt.wotnow.domain.Wot

class WotTable(wots: Wot*) {

  lazy val render = {
    val divider = "-" * (13 + maxFieldLengths.reduceLeft(_+_))
    "%s\n%s\n%s\n%s%s\n".format(divider, pad(titles), divider, padAllWots, divider).trim
  }

  lazy val asXML = <wotnow>{wots.map(_.asXML)}</wotnow>

  private val titles = List("Id", "Title", "Status", "Priority")
  private lazy val maxFieldLengths = wots.foldLeft(titles.toList.map(_.length)) {(lengths, wot) =>
  //    def maxLength(index: Int, stringable: type def toString: String) // todo - how to define a type as something that has a toString
    import Math._

    val maxId = max(lengths(0), wot.id.toString.length)
    val maxTitle = max(lengths(1), wot.title.length)
    val maxStatus = max(lengths(2), wot.status.length)
    val maxPriority = max(lengths(3), wot.priority.toString.length)
    List(maxId, maxTitle, maxStatus, maxPriority)
  }

  private def padAllWots = {
    val result = wots.toList.map(pad).mkString("\n")
    if (wots.size == 0) result else result + "\n"
  }
  private def pad(wot: Wot): String = pad(wot.id.toString, wot.title, wot.status, wot.priority.toString)
  private def pad(strings: String*): String = pad(strings.toList)
  private def pad(strings: List[String]) = {
    strings.toList.zip(maxFieldLengths).foldLeft("") {(acc, next) =>
      val (string, length) = next
      val lengthVariation = length - string.length
      val treatedString = if (lengthVariation < 0) string.substring(0, length)
  else string + (" " * lengthVariation)
      "%s| %s ".format(acc, treatedString)
    } + "|"
  }
}