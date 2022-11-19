plugins {
    kotlin("multiplatform") version "1.7.0"
    id("maven-publish")
}

group = "com.github.rocketarminek"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.eventstore:db-client-java:4.0.0")
                implementation("com.google.code.gson:gson:2.10")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.slf4j:slf4j-simple:2.0.4")
            }
        }
        val jvmMain by getting
        val jvmTest by getting
    }
}
