import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "com.nominori"
version = "1.0.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.oshi:oshi-core:6.2.2")
    implementation("io.ktor:ktor-client-core:2.0.3")
    implementation("io.ktor:ktor-client-cio:2.0.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.0.3")
    implementation("io.ktor:ktor-serialization-gson:2.0.3")
    implementation("com.andreapivetta.kolor:kolor:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.10")
    implementation("org.apache.commons:commons-text:1.9")
    implementation("com.google.guava:guava:31.1-jre")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations.compileClasspath.get().forEach {
        from(if (it.isDirectory) it else zipTree(it))
    }
}

application {
    mainClass.set("MainKt")
}