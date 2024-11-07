plugins {
    id("java")
    kotlin("jvm")
}

group = "cn.cola.ai"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("cn.bigmodel.openapi:oapi-java-sdk:release-V4-2.1.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}