plugins {
    id("java")
    `java-library`
    `maven-publish`
}

group = "com.yifeistudio"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")
    compileOnly("com.fasterxml.jackson.core:jackson-core:2.13.2")

    testRuntimeOnly("com.fasterxml.jackson.core:jackson-core:2.13.2")
    testRuntimeOnly("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

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
                username = "yifeistudio"
                password = "YiFei110930008&"
            }
            url = if (isSnapshotVersion) {
                uri(snapshotsRepoUrl)
            } else {
                uri(releasesRepoUrl)
            }
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
