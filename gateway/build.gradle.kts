plugins {
    id("java")
}

group = "cn.cola.gateway"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.1.5")
    implementation("com.github.xiaoymin:knife4j-gateway-spring-boot-starter:4.4.0")
}

tasks.test {
    useJUnitPlatform()
}