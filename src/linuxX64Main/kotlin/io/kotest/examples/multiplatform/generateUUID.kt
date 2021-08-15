package io.kotest.examples.multiplatform

import kotlin.random.Random
import kotlin.system.getTimeNanos

actual fun generateUUID(): UUID {
   // this is not meant to be a high quality uuid, just showing the principal of each platform
   // having its own implementation
   return UUID(getTimeNanos().toString().padStart(20, '0') + Random.nextInt(10000000, 99999999).toString())
}
