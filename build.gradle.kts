plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "de.the-cloisters"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val version = "5.7.2"
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:$version")
    testImplementation("io.kotest:kotest-assertions-core:$version")
    testImplementation("io.kotest:kotest-property:$version")
    testImplementation("io.kotest:kotest-framework-datatest:$version")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}