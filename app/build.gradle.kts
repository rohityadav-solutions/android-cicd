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
        versionCode = 45
        versionName = "4.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val keystorePath = System.getenv("KEYSTORE_BASE64")
            val storePass = System.getenv("KEYSTORE_STORE_PASSWORD")
            val keyAlias = System.getenv("KEYSTORE_KEY_ALIAS")
            val keyPass = System.getenv("KEYSTORE_KEY_PASSWORD")

            if (keystorePath != null && storePass != null && keyAlias != null && keyPass != null) {
                storeFile = file(keystorePath)
                storePassword = storePass
                this.keyAlias = keyAlias
                keyPassword = keyPass
            }
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
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