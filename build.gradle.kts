// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        flatDir {
            dirs("libs")
        }
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

