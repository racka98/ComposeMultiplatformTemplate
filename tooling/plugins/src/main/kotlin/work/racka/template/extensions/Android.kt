package work.racka.template.extensions

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import kotlin.jvm.optionals.getOrNull

internal fun Project.configureAndroid() {
    android {
        compileSdkVersion = libs.findVersion("android-compileSdk").getOrNull()?.strictVersion

        defaultConfig {
            minSdk = libs.findVersion("android-minSdk").getOrNull()?.strictVersion?.toIntOrNull()
            targetSdk = libs.findVersion("android-targetSdk").getOrNull()?.strictVersion?.toIntOrNull()
            applicationId = libs.findVersion("app-packagename").getOrNull()?.strictVersion
            versionCode = libs.findVersion("android-versioncode").getOrNull()?.strictVersion?.toIntOrNull()
            versionName = libs.findVersion("android-versionName").getOrNull()?.strictVersion
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }

        buildFeatures.buildConfig = true

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
            }
        }

        packagingOptions {
            resources {
                excludes += mutableSetOf(
                    "/META-INF/{AL2.0,LGPL2.1}",
                    "META-INF/licenses/ASM"
                )
                // Fixes conflicts caused by ByteBuddy library used in
                // coroutines-debug and mockito
                pickFirsts += mutableSetOf(
                    "win32-x86-64/attach_hotspot_windows.dll",
                    "win32-x86/attach_hotspot_windows.dll"
                )
            }
        }
    }

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())
    }
}

internal fun Project.android(action: BaseExtension.() -> Unit) = extensions.configure<BaseExtension>(action)