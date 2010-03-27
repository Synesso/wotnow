package net.badgerhunt.wotnow.render

import org.specs.Specification
import net.badgerhunt.wotnow.domain.{Urgent, Wot}
import net.badgerhunt.matchers.beCanonicallyEquivalentTo

class WotTableSpec extends Specification {
  "a wot table with no wots" should {
    val subject = new WotTable()
    "render an empty table" in {
      subject.render must_==
      """
      |----------------------------------
      || Id | Title | Status | Priority |
      |----------------------------------
      |----------------------------------
      """.stripMargin.trim
    }

    "render as valid XML" in {
      subject.asXML must_== <wotnow></wotnow>
    }
    
  }

  "a wot table with wots" should {
    val subject = new WotTable(new Wot("The title of the wot", "In progress, due today", Urgent))

    "expand column to the length of the largest field" in {
      subject.render must_==
      """
      |-----------------------------------------------------------------
      || Id | Title                | Status                 | Priority |
      |-----------------------------------------------------------------
      || 1  | The title of the wot | In progress, due today | Urgent   |
      |-----------------------------------------------------------------
      """.stripMargin.trim
    }

    "justify numeric columns to the right" in {}

    "render as valid XML" in {
      subject.asXML must beCanonicallyEquivalentTo{
        <wotnow>
          <wot id="1">
            <title>The title of the wot</title>
            <status>In progress, due today</status>
            <priority>Urgent</priority>
          </wot>
        </wotnow>
      }
    }
  }
}