import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    kotlin("jvm") version "1.3.40"
}

repositories {
    mavenCentral()
}

group = "io.github.serpro69"
version = "0.1"

val agent: Configuration by configurations.creating

dependencies {
    agent("org.aspectj:aspectjweaver:1.9.4")
    compileOnly("org.aspectj:aspectjrt:1.9.4")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
    
    doFirst {
        jvmArgs("-javaagent:${agent.singleFile}")
    }
}