buildscript {

   repositories {
      mavenCentral()
      mavenLocal()
      maven("https://kotlin.bintray.com/kotlinx")
   }
}

plugins {
   java
   kotlin("multiplatform") version "1.4.30"
   id("java-library")
}

allprojects {
   repositories {
      mavenCentral()
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
   }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
   kotlinOptions {
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.time.ExperimentalTime"
      jvmTarget = "1.8"
      apiVersion = "1.4"
   }

}