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

publishing {
    publications {
        create<MavenPublication>("release") {
            // Use the components that Gradle provides by default for Android libraries
            from(components.findByName("release"))

            // Artifact details:
            groupId = "com.openreplay.tracker"
            artifactId = "tracker"
            version = "1.0.6"

            // Pom configuration for additional metadata
            pom {
                name.set("OpenReplay Android Tracker")
                description.set("A brief description of what your library does.")
                url.set("https://www.openreplay.com")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("shekarsiri")
                        name.set("Shekar Sirikonda")
                        email.set("shekar@openreplay.com")
                    }
                }

                scm {
                    connection.set("scm:git:git@example.com:yourcompany/library.git")
                    developerConnection.set("scm:git:ssh://example.com:yourcompany/library.git")
                    url.set("http://github.com/openreplay/android")
                }
            }
        }
    }

//    repositories {
//        maven {
//            // Repository URL and credentials for uploading the artifact
//            url = uri("https://your.maven.repo.url")
//            credentials {
//                username = "repo_user"
//                password = "repo_password"
//            }
//        }
//    }
}
