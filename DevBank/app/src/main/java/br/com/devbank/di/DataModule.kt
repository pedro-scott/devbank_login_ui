package br.com.devbank.di

import br.com.devbank.features.resgitertwo.data.repository.RegisterTwoRepository
import org.koin.dsl.module

object DataModule {

    val repositoryModules = module {
        single { RegisterTwoRepository() }
    }

}