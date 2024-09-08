plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.9.22"  // Updated to latest stable version
  id("org.jetbrains.intellij") version "1.17.2"
}

group = "com.kirikodevv"
version = "1.0.0"

repositories {
  mavenCentral()
}

// Configure Gradle IntelliJ Plugin
intellij {
  version.set("2024.2.1")  // Updated to latest WebStorm version
  type.set("IU") // Target IDE Platform

  plugins.set(listOf("JavaScript"))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    sinceBuild.set("240.*")
    untilBuild.set("242.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }

  runIde {
     ideDir.set(file("/Users/jonahelbaz/Applications/WebStorm.app/Contents"))

    systemProperty("ide.mac.url.schemes", "app.context-grab.com")
  }
}