plugins {
    id("java")
    signing
    `java-library`
    `maven-publish`
}

val ossrhUsername: String by project
val ossrhPassword: String by project

group = "com.yifeistudio"
version = "2.0.0-RELEASE"


repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    compileOnly("com.fasterxml.jackson.core:jackson-core:2.13.4")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    testRuntimeOnly("com.fasterxml.jackson.core:jackson-core:2.13.4")
    testRuntimeOnly("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
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
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
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

// 加密
signing {
    sign(publishing.publications["mavenJava"])
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

