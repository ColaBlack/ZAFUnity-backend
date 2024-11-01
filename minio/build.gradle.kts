plugins {
    id("java")
}

group = "cn.cola.minio"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":common"))
    implementation("io.minio:minio:8.5.13")
}

tasks.test {
    useJUnitPlatform()
}