package net.badgerhunt.wotnow.domain

trait Priority extends Comparable[Priority] {
  val order = List(Urgent, High, Medium, Low, Defer)
  override def compareTo(other: Priority) = order.indexOf(this) - order.indexOf(other)
}
case object Urgent extends Priority
case object High extends Priority
case object Medium extends Priority
case object Low extends Priority
case object Defer extends Priority

class StringToPriority(s: String) {
  def toPriority = s match {
    case "Urgent" => Urgent
    case "High" => High
    case "Medium" => Medium
    case "Low" => Low
    case "Defer" => Defer
    case _ => throw new NoSuchEnumException(s)
  }
}

class NoSuchEnumException(requested: => String) extends RuntimeException("No such enum value: '%s'".format(requested))
