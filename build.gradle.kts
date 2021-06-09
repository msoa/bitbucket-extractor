import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
	kotlin("plugin.jpa") version "1.3.72"
	id("org.jmailen.kotlinter") version "3.2.0"
}

group = "com.msoa"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

buildscript {
	extra.set("kotlin_version", "1.4.0")
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

kotlinter {
	ignoreFailures = false
	indentSize = 4
	reporters = arrayOf("checkstyle", "plain")
	experimentalRules = false
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation( "org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework:spring-jdbc")
	implementation("javax.persistence:javax.persistence-api:2.2")
	implementation("org.hibernate:hibernate-entitymanager:5.2.12.Final")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core:5.2.4")
	implementation("org.postgresql:postgresql")

	implementation("com.squareup.okhttp3:okhttp:4.9.0")
	
	implementation("com.google.code.gson:gson:2.8.5")
	implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")

	implementation("org.apache.commons:commons-csv:1.8")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
