plugins {
    id("java")
    id("io.freefair.lombok") version "8.4"
}

group = "be.floshie"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.5")
    implementation("io.vavr:vavr:0.10.4")
    implementation ("org.slf4j:slf4j-api:1.7.30")
    implementation ("org.slf4j:slf4j-simple:1.7.30")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}