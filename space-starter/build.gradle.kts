import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.yifeistudio"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven(url = "https://s01.oss.sonatype.org/content/groups/public/")
}

val lombokVersion = "1.18.22"
val spaceVersion = "1.0-SNAPSHOT"
val springBootVersion = "2.6.6"

dependencies {

    implementation("com.yifeistudio:space-unit:${spaceVersion}")

    compileOnly("org.springframework.boot:spring-boot:${springBootVersion}")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure:${springBootVersion}")
    compileOnly("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
    compileOnly("org.springframework.boot:spring-boot-starter-aop:${springBootVersion}")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${springBootVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
