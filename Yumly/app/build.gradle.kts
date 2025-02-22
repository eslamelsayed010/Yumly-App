plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.yumly"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.yumly"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //lottie
    implementation(libs.lottie)
    implementation(libs.material.v190)

   //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.play.services.auth)
    implementation(libs.google.firebase.auth)
    implementation(libs.facebook.login)

    //nav
    implementation(libs.navigation.fragment.v253)
    implementation(libs.navigation.ui.v253)

    //retrofit, glide
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.glide)

    //Room
    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)

    //JavaRX
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    //RxJava Retrofit
    implementation(libs.rxjava3.retrofit.adapter)

    //RXJava Room
    implementation(libs.room.rxjava3)

    //youtube
    implementation(libs.core)

    //gson
    implementation(libs.gson)


}