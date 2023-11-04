@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "nik.borisov.hstest"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "nik.borisov.hstest"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Core
    implementation(libs.android.appCompat)
    implementation(libs.android.core)
    implementation(libs.android.activity)
    implementation(libs.android.fragment)

    //Navigation
    implementation(libs.android.navigationFragments)
    implementation(libs.android.navigationUI)

    //Coroutines
    implementation(libs.kotlin.coroutines.core)

    //Lifecycle
    implementation(libs.android.lifecycle.runtime)
    implementation(libs.android.lifecycle.viewmodel)
    implementation(libs.android.lifecycle.liveData)

    //Ui
    implementation(libs.android.constraintLayout)
    implementation(libs.google.material)

    //Hilt
    implementation(libs.google.hilt.android)
    kapt(libs.google.hilt.compiler)
    kapt(libs.kotlin.metadata)

    //Retrofit
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converterGson)

    //Picasso
    implementation(libs.picasso)

    //Room
    implementation(libs.android.room.room)
    implementation(libs.android.room.runtime)
    annotationProcessor(libs.android.room.compiler)
    kapt(libs.android.room.compiler)
}