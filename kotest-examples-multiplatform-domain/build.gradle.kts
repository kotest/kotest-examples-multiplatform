plugins {
    id("java")
    kotlin("multiplatform")
    id("java-library")
}

repositories {
    mavenCentral()
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
        js {
            browser()
            nodejs()
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(kotlin("stdlib"))
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        all {
            languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            languageSettings.useExperimentalAnnotation("kotlin.experimental.ExperimentalTypeInference")
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.apiVersion = "1.4"
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