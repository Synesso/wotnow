package net.badgerhunt.matchers

import org.specs.matcher.Matcher
import xml.{Node, PrettyPrinter}

// todo - a better way to implement this?
// todo - contribute this to specs?
case class beCanonicallyEquivalentTo(thatXML: Node) extends Matcher[Node] {
  val formatter = new PrettyPrinter(120, 2)
  def apply(thisXML: => Node) = {
    val equivalent = formatter.format(thatXML).equals(formatter.format(thisXML))
    (equivalent, "ok", "ko")
  }
}