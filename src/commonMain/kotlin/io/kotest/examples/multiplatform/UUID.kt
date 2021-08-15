package io.kotest.examples.multiplatform

/**
 * This is a UUID struct, but the implementations that generate the uuids are located
 * in the target specific source sets.
 */
data class UUID(val value: String)

expect fun generateUUID(): UUID