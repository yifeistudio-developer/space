version = "1.0-SNAPSHOT"

val ossrhUsername: String by project
val ossrhPassword: String by project

allprojects {
    group = "com.yifeistudio"
    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://s01.oss.sonatype.org/content/groups/public/")
    }
}
subprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("maven-publish")
        plugin("signing")
    }
    extensions.getByType<PublishingExtension>().repositories {
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

    extensions.getByType<PublishingExtension>().publications {
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
    extensions.getByType<SigningExtension>()
        .sign(extensions.getByType<PublishingExtension>().publications.named("mavenJava").get())
}
///~
