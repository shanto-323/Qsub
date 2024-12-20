plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
  alias(libs.plugins.google.gms.google.services)
}

android {
  namespace = "com.example.qsub"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.qsub"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
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
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.firebase.auth)
  implementation(libs.firebase.firestore)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)

  //Material3
  implementation("androidx.compose.material3:material3:1.2.1")
  implementation("androidx.compose.material:material-icons-extended:1.7.3")

  //Dagger Hilt
  kapt("androidx.hilt:hilt-compiler:1.2.0")
  kapt("com.google.dagger:hilt-android-compiler:2.51.1")
  implementation("com.google.dagger:hilt-android:2.51.1")
  implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
  implementation("androidx.hilt:hilt-work:1.2.0")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
  implementation( "androidx.hilt:hilt-navigation-compose:1.2.0")
  implementation ("androidx.navigation:navigation-compose:2.7.7")

  val navVersion = "2.7.7"
  implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
  implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
  implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")
  androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")
  implementation("androidx.navigation:navigation-compose:$navVersion")


  val pagingVersion = "3.3.1"
  implementation("androidx.paging:paging-runtime:$pagingVersion")
  testImplementation("androidx.paging:paging-common:$pagingVersion")
  implementation("androidx.paging:paging-compose:3.3.1")

  implementation (libs.accompanist.navigation.animation)
}