plugins {
    kotlin("jvm") version "2.0.20"
}

group = "dev.jacktrowbridge"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("net.minestom:minestom-snapshots:8953ff467e")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}