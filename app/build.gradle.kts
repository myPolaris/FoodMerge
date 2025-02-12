plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.food.carnival.merge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.food.carnival.merge"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        resConfigs("en", "in", "pt-rBR")
        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("../app/food.jks")
            storePassword = "food123"
            keyAlias = "Food"
            keyPassword = "food123"
            enableV1Signing = true
            enableV2Signing = true
        }
        create("testFood"){
            storeFile = file("../app/test.jks")
            storePassword = "test123"
            keyAlias = "TestFood"
            keyPassword = "test123"
            enableV1Signing = true
            enableV2Signing = true
        }
    }


    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("testFood")
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        getByName("debug") {
            signingConfig = signingConfigs.getByName("testFood")
            isDebuggable = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    packagingOptions {
        doNotStrip.add("*/armeabi-v7a/*.so")
        doNotStrip.add("*/arm64-v8a/*.so")
        doNotStrip.add("**/*.class")
        exclude("META-INF/*")
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/proguard/androidx-annotations.pro")
        exclude("META-INF/DEPENDENCIES")
        dex {
            useLegacyPackaging = true
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
        }
    }
    lintOptions {
        isAbortOnError = false
        isCheckReleaseBuilds = false
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    dexOptions {
        javaMaxHeapSize = "4g"
    }
}
dependencies {
    implementation(fileTree("libs") { include("*.jar") })
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
}