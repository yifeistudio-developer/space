version = "2.0.3-RELEASE"

val jacksonVersion = "2.15.2"
val junitVersion = "5.10.0"

dependencies {
    compileOnly("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${jacksonVersion}")
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

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
