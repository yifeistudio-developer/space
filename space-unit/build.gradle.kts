plugins {
    id("java")
    signing
    `java-library`
    `maven-publish`
}

val ossrhUsername: String by project
val ossrhPassword: String by project

group = "com.yifeistudio"
version = "1.0-RELEASE"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    compileOnly("com.fasterxml.jackson.core:jackson-core:2.13.3")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.13.3")
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
