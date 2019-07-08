package com.turastory.androidtoolbox.build.android

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.turastory.androidtoolbox.build.Version
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

fun Project.configureAndroidCommons(code: Int, name: String) {
    apply(plugin = "android-library")
    apply(plugin = "kotlin-android")
    configure<LibraryExtension> {
        configureCommons(code, name)
    }
}

fun Project.isAndroidProject(): Boolean {
    println("List of plugins for project $name")
    plugins.forEach {
        println(it)
    }
    return plugins.hasPlugin("com.android.library") ||
        plugins.hasPlugin("com.android.application")
}

fun TestedExtension.configureCommons(
    code: Int,
    name: String
) {
    compileSdkVersion(Version.Android.compileSdkVersion)
    buildToolsVersion(Version.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Version.Android.minSdkVersion)
        targetSdkVersion(Version.Android.targetSdkVersion)
        versionCode = code
        versionName = name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}