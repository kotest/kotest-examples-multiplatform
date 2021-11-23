package io.kotest.examples.mpp

import io.kotest.core.spec.style.FunSpec
import io.kotest.examples.multiplatform.generateUUID
import io.kotest.matchers.string.shouldHaveLength

// this test only runs for JS implementations
class UUIDJsTest : FunSpec() {
    init {
        test("uuids should have length 21") {
            generateUUID().value.shouldHaveLength(21)
        }
    }
}
