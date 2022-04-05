buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_AGP}")
    }
}

plugins {
    id("com.android.application") version Versions.ANDROID_GRADLE_PLUGIN apply false
    id("com.android.library") version Versions.ANDROID_GRADLE_PLUGIN apply false
    kotlin("android") version Versions.KOTLIN apply false
    kotlin("kapt") version Versions.KOTLIN apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}