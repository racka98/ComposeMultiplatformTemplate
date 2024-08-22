plugins {
    `kotlin-dsl`
}

group = "work.racka.template.plugins"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.jetbrains.compose.gradlePlugin)
}

gradlePlugin {
    // IDs for these custom plugins are obtained from libs.versions.toml for ease of use in project
    plugins {
        register("composeMultiplatformAppPlugin") {
            id = libs.plugins.compose.multiplatform.app.get().pluginId
            implementationClass = "work.racka.template.plugins.ComposeMultiplatformAppPlugin"
        }
        register("composeMultiplatformLibPlugin") {
            id = libs.plugins.compose.multiplatform.lib.get().pluginId
            implementationClass = "work.racka.template.plugins.ComposeMultiplatformLibPlugin"
        }
        register("kotlinMultiplatformLibPlugin") {
            id = libs.plugins.kotlin.multiplatform.lib.get().pluginId
            implementationClass = "work.racka.template.plugins.KotlinMultiplatformLibPlugin"
        }
    }
}