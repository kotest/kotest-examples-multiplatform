package io.kotest.examples.mpp

import io.kotest.core.spec.style.FunSpec
import io.kotest.examples.multiplatform.generateUUID
import io.kotest.matchers.string.shouldHaveLength

// this test only runs for JVM implementations
class UUIDJvmTest : FunSpec() {
    init {
        test("uuids should be in be type 4 format") {
            generateUUID().value.shouldHaveLength(36)
        }
    }
}