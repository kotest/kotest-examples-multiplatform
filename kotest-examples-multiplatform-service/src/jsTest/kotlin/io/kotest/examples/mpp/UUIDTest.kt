package io.kotest.examples.mpp

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldHaveLength

class UUIDTest : FunSpec() {
    init {
        test("uuids should have length 19") {
            generateUUID().value.shouldHaveLength(19)
        }
    }
}