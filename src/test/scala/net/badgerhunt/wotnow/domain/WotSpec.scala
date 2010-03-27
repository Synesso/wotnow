package net.badgerhunt.wotnow.domain

import org.specs.Specification


class WotSpec extends Specification {

  "a wot" should {
    val subject = new Wot("wot to do", "working on it", High)

    "generate valid XML" in {
      subject.asXML must \(<title>wot to do</title>)
//      subject.asXML must_== <wot><title>wot to do</title><status>working on it</status><priority>High</priority></wot>
      // todo - how does the xml matcher look?
    }
  }
}