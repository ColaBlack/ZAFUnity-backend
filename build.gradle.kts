plugins {
    java
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm")
}

group = "cn.cola"
version = "1.0.0"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.alibaba.cloud:spring-cloud-alibaba-sentinel-gateway")
        implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
        implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel")
        implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0")
        implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.9")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("com.mysql:mysql-connector-j")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        compileOnly("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        implementation("cn.hutool:hutool-all:5.8.26")
        implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
        implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.4.0")
        implementation("org.apache.dubbo:dubbo")
        implementation("org.apache.dubbo:dubbo-nacos-spring-boot-starter")
    }

    dependencyManagement {
        imports {
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2022.0.0.0-RC2")
            mavenBom("org.apache.dubbo:dubbo-bom:3.3.0")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
