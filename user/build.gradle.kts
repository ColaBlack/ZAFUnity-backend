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
    implementation(project(":service"))
    implementation(project(":common"))
    implementation(project(":model"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("com.sun.mail:javax.mail:1.6.2")
    // https://mvnrepository.com/artifact/org.redisson/redisson
    implementation("org.redisson:redisson:3.37.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}