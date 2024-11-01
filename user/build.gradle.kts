plugins {
    id("java")
    kotlin("jvm")
}

group = "cn.cola.user"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-mail")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}