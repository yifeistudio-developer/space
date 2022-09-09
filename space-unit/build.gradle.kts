plugins {
    id("java")
    signing
    `java-library`
    `maven-publish`
}

val ossrhUsername: String by project
val ossrhPassword: String by project

group = "com.yifeistudio"
version = "2.0-SNAPSHOT"

// Invalid POM: /com/yifeistudio/space-unit/1.0-RELEASE/space-unit-1.0-RELEASE.pom:
// Project name missing,
// Project description missing, Project URL missing,
// License information missing,
// SCM URL missing, Developer information missing

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    compileOnly("com.fasterxml.jackson.core:jackson-core:2.13.4")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    testRuntimeOnly("com.fasterxml.jackson.core:jackson-core:2.13.3")
    testRuntimeOnly("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

}

java {
    withSourcesJar()
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

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
