package net.badgerhunt.wotnow.domain

case class Wot(id: Int, title: String, status: String, priority: Priority) {
  def this(title: String, status: String, priority: Priority) = this(1, title, status, priority)
  def asXML = <wot id={id.toString}><title>{title}</title><status>{status}</status><priority>{priority}</priority></wot>
}