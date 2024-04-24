plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.openreplay.tracker"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.commons.compress)
    implementation(libs.kotlin.stdlib)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

tasks {
    create("listComponents") {
        doLast {
            println("components")
            project.components.forEach { component ->
                println("Component: ${component.name}")
            }
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])
                groupId = "com.openreplay.tracker"
                artifactId = "openreplay"
                version = "1.1.2"
            }
        }
    }
}

//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("maven") {
//                // Use the components that Gradle provides by default for Android libraries
//                from(components["release"])
//
//                // Artifact details:
//                groupId = "com.openreplay.tracker"
//                artifactId = "openreplay"
//                version = "1.0.9"
//
//                // Pom configuration for additional metadata
//                pom {
//                    name.set("OpenReplay Android Tracker")
//                    description.set("A brief description of what your library does.")
//                    url.set("https://www.openreplay.com")
//
//                    licenses {
//                        license {
//                            name.set("The Apache License, Version 2.0")
//                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                        }
//                    }
//
//                    developers {
//                        developer {
//                            id.set("shekarsiri")
//                            name.set("Shekar Sirikonda")
//                            email.set("shekar@openreplay.com")
//                        }
//                    }
//
//                    scm {
//                        connection.set("scm:git:git@example.com:yourcompany/library.git")
//                        developerConnection.set("scm:git:ssh://example.com:yourcompany/library.git")
//                        url.set("http://github.com/openreplay/android")
//                    }
//                }
//
//                pom.withXml {
//                    val dependenciesNode = asNode().appendNode("dependencies")
//                    configurations.getByName("implementation").allDependencies.forEach { dependency ->
//                        val dependencyNode = dependenciesNode.appendNode("dependency")
//                        dependencyNode.appendNode("groupId", dependency.group)
//                        dependencyNode.appendNode("artifactId", dependency.name)
//                        dependencyNode.appendNode("version", dependency.version)
//                    }
//                }
//            }
//        }
//    }
//}
