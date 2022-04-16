plugins {
    id("java")
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
    withJavadocJar()
    withSourcesJar()
}

val sourcesJar by tasks.registering(Jar::class) {
    //如果没有配置main会报错
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(sourcesJar)
            artifactId = project.name
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }

    // 仓库配置
    repositories {
        maven {

            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            val isSnapshotVersion = version.toString().endsWith("SNAPSHOT")

            credentials {

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
