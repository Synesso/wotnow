package net.badgerhunt.wotnow.domain

import org.specs.Specification

class PrioritiesSpec extends Specification {

  "an urgent priority" should {
    "not trump another urgent priority" in {
      Urgent compareTo Urgent must_== 0
    }

    "trump all other priorities" in {
      Urgent compareTo High must_== -1 // todo - must be less than 0, also is -1 right or should it be 1?
      Urgent compareTo Medium must_== -2
      Urgent compareTo Low must_== -3
      Urgent compareTo Defer must_== -4
    }
  }

  "a high priority" should {
    "be trumped by an urgent priority" in {
      High compareTo Urgent must_== 1
    }

    "not trump another high priority" in {
      High compareTo High must_== 0
    }

    "trump all other priorities" in {
      High compareTo Medium must_== -1
      High compareTo Low must_== -2
      High compareTo Defer must_== -3
    }
  }

  "a medium priority" should {
    "be trumped by more pressing priorities" in {
      Medium compareTo Urgent must_== 2
      Medium compareTo High must_== 1
    }

    "not trump another medium priority" in {
      Medium compareTo Medium must_== 0
    }

    "trump all other priorities" in {
      Medium compareTo Low must_== -1
      Medium compareTo Defer must_== -2
    }
  }

  "a low priority" should {
    "be trumped by more pressing priorities" in {
      Low compareTo Urgent must_== 3
      Low compareTo High must_== 2
      Low compareTo Medium must_== 1
    }

    "not trump another low priority" in {
      Low compareTo Low must_== 0
    }

    "trump all other priorities" in {
      Low compareTo Defer  must_== -1
    }
  }

  "a deferral priority" should {
    "be trumped by all other priorities" in {
      Defer compareTo Urgent must_== 4
      Defer compareTo High must_== 3
      Defer compareTo Medium must_== 2
      Defer compareTo Low must_== 1
    }

    "not trump another deferral priority" in {
      Defer compareTo Defer must_== 0
    }
  }
}