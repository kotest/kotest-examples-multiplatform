package io.kotest.examples.mpp

import io.kotest.matchers.string.shouldStartWith
import kotlin.test.Test

class KotlinTest {
   @Test
   fun testByKotlinTest() {
      "hello world".shouldStartWith("hello")
   }
}
