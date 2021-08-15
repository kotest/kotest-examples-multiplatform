package io.kotest.examples.multiplatform

actual fun generateUUID(): UUID {
   return UUID(java.util.UUID.randomUUID().toString())
}