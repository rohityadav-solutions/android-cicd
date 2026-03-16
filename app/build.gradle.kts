plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.arbutusinfotech.upkeep"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.arbutusinfotech.upkeep"
        minSdk = 24
        targetSdk = 36
        versionCode = 42
        versionName = "4.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        if (System.getenv("KEYSTORE_BASE64") != null) {
            create("release") {
                storeFile     = file(System.getenv("KEYSTORE_BASE64")!!)
                storePassword = System.getenv("KEYSTORE_STORE_PASSWORD")
                keyAlias      = System.getenv("KEYSTORE_KEY_ALIAS")
                keyPassword   = System.getenv("KEYSTORE_KEY_PASSWORD")
            }
        } else {
            create("release") {
                // Signing with debug keys for local development
                // Release signing is handled automatically by GitHub Actions CI/CD
            }
        }
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}