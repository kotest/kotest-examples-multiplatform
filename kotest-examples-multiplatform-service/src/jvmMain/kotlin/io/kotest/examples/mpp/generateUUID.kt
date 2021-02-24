package io.kotest.examples.mpp

actual fun generateUUID(): UUID = UUID(java.util.UUID.randomUUID().toString())