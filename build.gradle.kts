val ktor_version = "1.6.7"
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String = "0.36.1"

plugins {
    application
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "com.weronika"
version = "0.0.1"
application {
    mainClass.set("com.weronika.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {

    //*** Exposed library for SQL database management
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

    //*** H2 Database
    implementation("com.h2database:h2:1.4.199")

    //*** Authentication
    implementation ("io.ktor:ktor-auth:$ktor_version")

    //*** Serialization
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")

    //*** Content Negotiation
    implementation("io.ktor:ktor-jackson:$ktor_version")

    //*** import kotlinx.serialization.Serializable
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    //*** Basics
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    //*** Test
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}