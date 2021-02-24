package io.kotest.examples.mpp

import kotlin.js.Date
import kotlin.random.Random

actual fun generateUUID(): UUID = UUID(Date().getTime().toString() + Random.nextInt(100000, 999999).toString())