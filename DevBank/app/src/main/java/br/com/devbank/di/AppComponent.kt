package br.com.devbank.di

import br.com.devbank.di.DataModule.repositoryModules
import br.com.devbank.di.AppModule.viewModelModules
import br.com.devbank.di.DomainModule.domainModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules() : List<Module> = listOf(
        *getViewModelModules,
        *getDataModules,
        *getDomainModules
    )

    private val getViewModelModules: Array<Module> get() = arrayOf(viewModelModules)
    private val getDataModules: Array<Module> get() = arrayOf(repositoryModules)
    private val getDomainModules: Array<Module> get() = arrayOf(domainModules)

}