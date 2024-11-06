plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm")
}

group = "cn.cola"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
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
        // https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter
        implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
        implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.9")
//        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("com.mysql:mysql-connector-j")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        implementation("cn.hutool:hutool-all:5.8.26")
        implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
        implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.4.0")
//        implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
//        implementation("org.springframework.cloud:spring-cloud-loadbalancer:3.1.5")
//        implementation("com.alibaba.cloud:spring-cloud-alibaba-sentinel-gateway:2021.0.5.0")
//        implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2021.0.5.0")
    }

//    dependencyManagement {
//        imports {
//            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2021.0.5.0")
//        }
//    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}