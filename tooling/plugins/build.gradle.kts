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
    plugins {
        register("composeMultiplatformAppPlugin") {
            id = "plugin.template.compose.multiplatform.app"
            implementationClass = "work.racka.template.plugins.ComposeMultiplatformAppPlugin"
        }
        register("composeMultiplatformLibPlugin") {
            id = "plugin.template.compose.multiplatform.lib"
            implementationClass = "work.racka.template.plugins.ComposeMultiplatformLibPlugin"
        }
        register("kotlinMultiplatformLibPlugin") {
            id = "plugin.template.kotlin.multiplatform.lib"
            implementationClass = "work.racka.template.plugins.KotlinMultiplatformLibPlugin"
        }
    }
}