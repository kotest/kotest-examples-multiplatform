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
                implementation(project(":kotest-examples-multiplatform-domain"))
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        val commonTest by getting {
            dependencies {
                implementation("io.kotest:kotest-framework-api:4.4.1")
                implementation("io.kotest:kotest-assertions-core:4.4.1")
            }
        }

        val jsTest by getting {
            dependencies {
                implementation("io.kotest:kotest-framework-engine:4.4.1")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5:4.4.1")
            }
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