import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin.*

val kotlinVersion = "1.3.10"

buildscript {
    repositories {
        mavenLocal()
        jcenter()
    }
}

plugins {
    val kotlinVersion = "1.3.10"
    val springBootVersion = "2.1.0.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.springframework.boot") version springBootVersion apply false
}

apply {
    plugin("kotlin")
    plugin("io.spring.dependency-management")
    plugin("org.jetbrains.kotlin.plugin.spring")
}

group = "com.github.jntakpe"
version = "0.0.1-SNAPSHOT"

configurations {
    all {
        exclude(module = "junit")
        exclude(module = "spring-webmvc")
    }
}

the<DependencyManagementExtension>().apply {
    imports {
        mavenBom(BOM_COORDINATES){ bomProperty("kotlin.version", kotlinVersion) }
    }
}

configurations {
    all {
        exclude(module = "junit")
    }
}

repositories {
    mavenCentral()
}


dependencies {
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testRuntimeOnly("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    val build by tasks
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=compatibility")
        }
    }
    withType<Test> {
        useJUnitPlatform()
    }
}
