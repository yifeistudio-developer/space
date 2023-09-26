plugins {
    id("java")
    signing
    `java-library`
    `maven-publish`
}

val ossrhUsername: String by project
val ossrhPassword: String by project

group = "com.yifeistudio"
version = "2.0.1-RELEASE"

val jacksonVersion = "2.15.2"
val junitVersion = "5.10.0"

dependencies {
    compileOnly("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    testRuntimeOnly("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    testRuntimeOnly("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        val sps = (options as StandardJavadocDocletOptions)
        sps.addBooleanOption("html5", true)
        sps.addStringOption("Xdoclint:none", "-quiet")
        sps.addStringOption("encoding", "UTF-8")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("space-unit")
                description.set("the basic unit of Space project.")
                url.set("https://github.com/yifeistudio-developer/space")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("yifeistudio.com")
                        name.set("hongyi")
                        email.set("develop@yifeistudio.com")
                    }
                    scm {
                        connection.set("scm:git://github.com/yifeistudio-developer/space.git")
                        developerConnection.set("scm:git://github.com/yifeistudio-developer/space.git")
                        url.set("https://github.com/yifeistudio-developer/space")
                    }
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

// 签名
signing {
    sign(publishing.publications["mavenJava"])
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
