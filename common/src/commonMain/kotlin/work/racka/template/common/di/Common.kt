package work.racka.template.common.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal object Common {
    fun KoinApplication.install() = apply {
        modules(sharedModule(), platform())
    }

    private fun sharedModule() = module {

    }
}

internal expect fun platform(): Module