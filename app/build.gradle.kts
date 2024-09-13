plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

}

android {
    namespace = "mobilt_java23.carl_sundberg.apiintegrationv4"
    compileSdk = 34

    defaultConfig {
        applicationId = "mobilt_java23.carl_sundberg.apiintegrationv4"
        minSdk = 30
        targetSdk = 34
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

    implementation ("com.squareup.okhttp3:okhttp:4.2.2")
    // Retrofit for API calls
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// Gson for JSON parsing
  //  implementation  ("com.google.code.gson:gson:2.8.8")



    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation ("com.google.android.material:material:1.4.0")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.legacy.support.v4)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}