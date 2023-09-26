plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://s01.oss.sonatype.org/content/groups/public/")
    }
}

group = "com.yifeistudio"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}