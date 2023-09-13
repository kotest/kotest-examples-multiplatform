buildscript {
   repositories {
      mavenCentral()
      mavenLocal()
   }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
   java
   id("java-library")
   alias(libs.plugins.kotlin.multiplatform)
   alias(libs.plugins.kotest.multiplatform)
}

allprojects {
   repositories {
      mavenCentral()
      mavenLocal()
      maven("https://oss.sonatype.org/content/repositories/snapshots")
   }
}

kotlin {

   targets {
      jvm {
         compilations.all {
            kotlinOptions {
               jvmTarget = "17"
            }
         }
      }
      js(IR) {
         browser()
         //nodejs()
      }
      linuxX64()
      macosX64()
      mingwX64()
   }

   targets.all {
      compilations.all {
         kotlinOptions {
            verbose = true
         }
      }
   }

   sourceSets {

      val commonMain by getting {
         dependencies {
            implementation(libs.kotlinx.coroutines.core)
         }
      }

      val commonTest by getting {
         dependencies {
            implementation(libs.kotest.assertions.core)
            implementation(libs.kotest.framework.engine)
            implementation(libs.kotest.framework.datatest)
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
         }
      }

      val jvmTest by getting {
         dependencies {
            implementation(libs.kotest.runner.junit5)
         }
      }
   }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
   kotlinOptions {
      languageVersion = "1.9"
      apiVersion = "1.9"
   }
}

tasks.named<Test>("jvmTest") {
   useJUnitPlatform()
   filter {
      isFailOnNoMatchingTests = false
   }
   testLogging {
      showExceptions = true
      showStandardStreams = true
      events = setOf(
         org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
         org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
      )
      exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
   }
}
