import java.text.SimpleDateFormat
import java.util.Date
import org.gradle.jvm.tasks.Jar

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.4.1/userguide/tutorial_java_projects.html
 */

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the idea plugin, used for form builder
    idea
    // Apply the application plugin to add support for building an application
    application

    //Shadow plugin for fatJar
    id("com.github.johnrengelman.shadow") version ("5.0.0")
}

project.ext {
    set("mainClassName", "org.hvdw.jexiftoolgui.Application")
}

repositories {
    mavenCentral()
    jcenter()
    maven("https://www.jetbrains.com/intellij-repository/releases")
    maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")

}


dependencies {

    //// Log4J and SLF4J configuration dependencies.
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.30")
    implementation(group = "org.slf4j", name = "slf4j-simple", version = "1.7.30")
    //implementation(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = "2.11.2")
    implementation(group = "ch.qos.logback", name = "logback-core", version = "1.2.3")
    implementation(group = "com.fasterxml.jackson.dataformat", name = "jackson-dataformat-yaml", version = "2.9.9")
    implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.9.9")
    // Add dependencies of forms
    implementation(group = "com.intellij", name = "forms_rt", version = "7.0.3")

    // Missing helpers and common utils in java
    implementation(group="org.apache.commons", name="commons-lang3", version="3.9")

    // At some time maybe when I feel like adding charts to display ISOs, focal length, aperture and the like
    //implementation(group = "org.jfree", name = "jfreechart", version = "1.5.0")

    // Twelve monkeys imageio (supports bmp, jpeg, jpeg-2000, PNM (PBM/PGM/PPM/PAM), PSD, TIFF, HDR, IFF, PCX
    // PICT, SGI, TGA, ICNS, ICO & CUR, SVG, WMF)
    implementation("com.twelvemonkeys.imageio:imageio-core:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-bmp:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-hdr:3.6")
//    implementation("com.twelvemonkeys.imageio:imageio-icns:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-iff:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-jpeg:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-metadata:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-pcx:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-pict:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-pnm:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-psd:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-sgi:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-tga:3.6")
    implementation("com.twelvemonkeys.imageio:imageio-tiff:3.6")
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //metadata-extractor Drew Noakes https://github.com/drewnoakes/metadata-extractor
    //implementation("com.drewnoakes:metadata-extractor:2.14.0")

    // Minimal json (35Kb), to be used for Nominatim queries (not yet)
    implementation(group = "com.eclipsesource.minimal-json", name = "minimal-json", version = "0.9.5")

    // sqlite
    implementation(group = "org.xerial", name = "sqlite-jdbc", version = "3.32.3")


    // Testing dependencies
    testImplementation(group = "junit", name = "junit", version = "4.12")
    testImplementation(group = "com.github.stefanbirkner", name = "system-rules", version = "1.19.0")
    //testImplementation(group = "ch.qos.logback", name = "logback-core", version = "1.2.3")

}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    // Undo version 11 again. 95% or more of OSes and distributions still use java 8
    // Redo java_11. We will deliver a jre11 if neccessary. Simple as that
    //sourceCompatibility = JavaVersion.VERSION_11
    //targetCompatibility = JavaVersion.VERSION_11

}

// New fatJar Gradle/kotlin way
val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Implementation-Title"] = "Gradle Jar File Example"
        //attributes["Implementation-Version"] = version
        attributes["Main-Class"] = project.ext["mainClassName"] as String
        attributes["Build-Timestamp"] = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Date())
        attributes["Created-By"] = "Gradle ${gradle.gradleVersion}"
        attributes["SplashScreen-Image"] = "icons/jexiftoolgui-splashlogo.png"
        attributes["Multi-Release"] = "true"
    }
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
//    doLast {
//        def servicesDir = file(
//    }
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}

//task copyServices(type: Copy) {
//    from 'TwelveMonkeys/META-INF/services/'
//    include '*'
//    into 'build/tmp/fatJar'
//}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = project.ext["mainClassName"] as String
        attributes["Build-Timestamp"] = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Date())
        attributes["Created-By"] = "Gradle ${gradle.gradleVersion}"
        attributes["SplashScreen-Image"] = "icons/jexiftoolgui-splashlogo.png"
        attributes["Multi-Release"] = "true"
    }
}

application {
    // Define the main class for the application
    mainClassName = project.ext["mainClassName"] as String
}


idea {
    module {
        outputDir = file("build/resources/main")
        testOutputDir = file("build/resources/test")
    }
}


