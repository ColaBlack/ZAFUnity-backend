plugins {
    id("java")
    kotlin("jvm")
}

group = "cn.cola.post"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":common"))
    implementation(project(":service"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":user"))
    implementation(project(":model"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}