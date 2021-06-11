plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.app.boruto.manga"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

val kotlin_version = "1.3.72"
val materialdesign_version = "1.4.0-beta01"
val activity_version = "1.2.3"
val fragment_version = "1.3.2"
val nav_version = "2.4.0-alpha01"
val lifecycle_version = "2.3.1"
val multidex_version = "2.0.1"
val glide_version = "4.12.0"

dependencies {
    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("androidx.core:core-ktx:1.3.2")

    //Components Android
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //Test
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("androidx.test.ext:junit:1.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")

    //Androidx e MaterialDesign
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("androidx.browser:browser:1.3.0")
    implementation ("com.google.android.material:material:$materialdesign_version")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:26.0.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-database-ktx")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //Activity-Kotlin
    implementation("androidx.activity:activity-ktx:$activity_version")

    //Fragment-Kotlin
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Glide
    implementation("com.github.bumptech.glide:glide:$glide_version")
    annotationProcessor("com.github.bumptech.glide:compiler:$glide_version")

    //Animatoo
    implementation("com.github.mohammadatif:Animatoo:master")

}