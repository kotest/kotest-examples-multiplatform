package io.kotest.examples.multiplatform

import kotlin.js.Date
import kotlin.random.Random

actual fun generateUUID(): UUID {
   // this is not meant to be a high quality uuid, just showing the principal of each platform
   // having its own implementation
   return UUID(Date().getTime().toString() + Random.nextInt(10000000, 99999999).toString())
}