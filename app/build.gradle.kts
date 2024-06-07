plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.rabbitfarmerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rabbitfarmerapp"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation (libs.androidx.coordinatorlayout.v130)

    implementation (libs.picasso)
    implementation (libs.androidx.coordinatorlayout)
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation(libs.firebase.auth.v2400)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation (libs.material)
    implementation (libs.androidx.drawerlayout.v120)
    implementation (libs.constraintlayout)
    implementation (libs.androidx.lifecycle.livedata.ktx.v281)
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v281)
    implementation (libs.navigation.fragment)
    implementation (libs.navigation.ui)
    implementation (libs.firebase.messaging)
    implementation (libs.firebase.inappmessaging.display.v2200)
    implementation (libs.firebase.storage)
    implementation (libs.firebase.database)

    implementation (libs.firebase.analytics.v2201)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.firebase.firestore)
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit.v113)
    androidTestImplementation( libs.espresso.core)
}
