plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.bmonroigj.rickompose"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
        maybeCreate("mock")
        getByName("mock") {
            initWith(getByName("debug"))
            versionNameSuffix = "-mock"
        }
    }

    sourceSets {
        getByName("debug") {
            java.srcDir("src/shared/java")
        }
        getByName("release") {
            java.srcDir("src/shared/java")
        }
    }

    testBuildType = "mock"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources.excludes += "META-INF/AL2.0"
        resources.excludes += "META-INF/LGPL2.1"
    }
}

dependencies {
    implementation(Libs.CORE_KTX)

    // UI
    implementation(Libs.ACTIVITY_KTX)
    implementation(Libs.APPCOMPAT)

    // Architecture Components
    implementation(Libs.LIFECYCLE_RUNTIME_KTX)
    implementation(Libs.LIFECYCLE_VIEW_MODEL_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)
    testImplementation(Libs.ARCH_TESTING)

    // Compose
    implementation(Libs.ACTIVITY_COMPOSE)
    implementation(Libs.COMPOSE_ANIMATION)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_RUNTIME)
    implementation(Libs.COMPOSE_TOOLING)
    androidTestImplementation(Libs.COMPOSE_TEST)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Coroutines
    implementation(Libs.COROUTINES)
    testImplementation(Libs.COROUTINES_TEST)

    // Instrumentation tests
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)

    // Local unit tests
    testImplementation(Libs.JUNIT)
}

kapt {
    correctErrorTypes = true
}