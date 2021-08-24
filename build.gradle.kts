buildscript {
   repositories {
      mavenCentral()
      mavenLocal()
   }
}

plugins {
   java
   id("java-library")
   kotlin("multiplatform") version "1.5.21"
   id("io.kotest.multiplatform") version "5.0.0.5"
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
               jvmTarget = "1.8"
            }
         }
      }
      js(IR) {
         browser()
         nodejs()
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
            implementation(kotlin("stdlib"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
         }
      }

      val commonTest by getting {
         dependencies {
            implementation("io.kotest:kotest-assertions-core:5.0.0.417-SNAPSHOT")
            implementation("io.kotest:kotest-framework-engine:5.0.0.417-SNAPSHOT")
         }
      }

//      val jvmTest by getting {
//         dependencies {
//            implementation("io.kotest:kotest-runner-junit5-jvm:5.0.0-LOCAL")
//         }
//      }
   }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
   kotlinOptions {
      apiVersion = "1.5"
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
