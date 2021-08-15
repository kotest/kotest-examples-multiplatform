package io.kotest.examples.mpp

import io.kotest.core.spec.style.FunSpec
import io.kotest.examples.multiplatform.generateUUID
import io.kotest.matchers.string.shouldHaveLength

// this test only runs for native implementations
class UUIDNativeTest : FunSpec() {
   init {
      test("uuids should have length 28") {
         generateUUID().value.shouldHaveLength(28)
      }
   }
}
