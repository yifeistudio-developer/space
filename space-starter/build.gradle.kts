import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    signing
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

val ossrhUsername: String by project
val ossrhPassword: String by project

version = "2.0.8-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val lombokVersion = "1.18.30"
val spaceVersion = "2.0.3-SNAPSHOT"
val nacosVersion = "0.2.12"

dependencies {

    api("com.yifeistudio:space-unit:${spaceVersion}")

    compileOnly("org.springframework.boot:spring-boot")

    compileOnly("org.springframework.boot:spring-boot-autoconfigure")
    compileOnly("org.springframework.boot:spring-boot-starter-aop")
    compileOnly("com.alibaba.boot:nacos-config-spring-boot-starter:${nacosVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    withSourcesJar()
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
            "Implementation-Version" to project.version))
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    // 仓库配置
    repositories {
        maven {

            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            val isSnapshotVersion = version.toString().endsWith("SNAPSHOT")

            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
            url = if (isSnapshotVersion) {
                uri(snapshotsRepoUrl)
            } else {
                uri(releasesRepoUrl)
            }
        }
    }
}

// 加密
signing {
    sign(publishing.publications["mavenJava"])
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
