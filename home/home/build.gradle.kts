plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "com.valmiraguiar.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL_API",
                "\"https://ddragon.leagueoflegends.com/cdn/\""
            )
            buildConfigField("String", "API_CURRENT_PATCH", "\"14.17.1\"")
            buildConfigField("String", "BASE_SPLASH_URL", "\"https://ddragon.leagueoflegends.com/cdn/img/champion/splash\"")
        }
        release {
            buildConfigField("String", "BASE_URL_API", "\"https://localhost/\"")
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":home:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // DI - Koin
    implementation(libs.koin)
    implementation(libs.koin.core)
    implementation(libs.koin.navigation)
    implementation(libs.koin.compose)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    // Coroutines
    testImplementation(libs.coroutines.test)

    //Arch
    testImplementation(libs.androix.arch.core.executor)

    // JUnit Ktx
    testImplementation(libs.junit.ktx)

    // Core Ktx
    testImplementation(libs.test.core.ktx)

    // Google Truth
    testImplementation(libs.google.truth)

    // Mockk
    testImplementation(libs.mockk)

    // Turbine
    testImplementation(libs.cash.turbine)
}