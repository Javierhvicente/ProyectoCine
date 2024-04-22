val koin_version: String by project
val logging_version: String by project
val logback_version: String by project
plugins {
    kotlin("jvm") version "1.9.23"
    //Dokka documentation
    id("org.jetbrains.dokka") version "1.9.20"
    //SqlDelight
    id("app.cash.sqldelight") version "2.0.2"
    //Serialization Kotlin
    kotlin("plugin.serialization") version "1.9.23"
    //KSP
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Logger
    implementation("org.lighthousegames:logging:$logging_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    //SqlDelight
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    //Result ROP
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    //Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    //Koin
    implementation(platform("io.insert-koin:koin-bom:$koin_version"))
    implementation("io.insert-koin:koin-core") // Core
    implementation("io.insert-koin:koin-test")
    //Annotations
    implementation(platform("io.insert-koin:koin-annotations-bom:1.3.1")) // BOM
    implementation("io.insert-koin:koin-annotations") // Annotations
    ksp("io.insert-koin:koin-ksp-compiler:1.3.1") // KSP Compiler, debes poner el mismo que el de las anotaciones


    testImplementation(kotlin("test"))
    // Mock
    testImplementation("io.mockk:mockk:1.13.10")
    //Koin
    testImplementation("io.insert-koin:koin-test-junit5")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("dev.javierhvicente.database")
        }
    }
}