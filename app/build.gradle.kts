plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.kelompokbaru"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kelompokbaru"
        minSdk = 21
        targetSdk = 33
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

    kapt {
        generateStubs = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.gms:google-services:4.3.10")
    implementation("com.google.firebase:firebase-perf-ktx:20.5.0")
    platform("com.google.firebase:firebase-bom:29.0.1")
    implementation("com.google.firebase:firebase-bom:31.2.2")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")


    // untuk database room


    implementation ("androidx.room:room-runtime:2.4.1")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
//    annotationProcessor ("androidx.room:room-compiler:2.4.1")

    

    ///////////////////////////////////////////////////////////////////////////////////

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Retrofit
    implementation("com.github.bumptech.glide:glide:4.15.0")
    implementation("com.loopj.android:android-async-http:1.4.9")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    debugImplementation("com.github.chuckerteam.chucker:library:3.3.0")

    //Bundar
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //pissaco
    implementation ("com.squareup.picasso:picasso:2.71828")

    //dataStore
    implementation ("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")



}