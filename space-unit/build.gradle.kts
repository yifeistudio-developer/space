plugins {
    id("java")
}

group = "com.yifeistudio"
version = "1.0-SNAPSHOT"

repositories {
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

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}