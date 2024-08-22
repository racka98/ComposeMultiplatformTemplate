plugins {
    alias(libs.plugins.compose.multiplatform.app)
}

android {
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
