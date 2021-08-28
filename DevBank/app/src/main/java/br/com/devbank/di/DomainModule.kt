package br.com.devbank.di

import br.com.devbank.features.resgitertwo.domain.usecase.RegisterTwoUseCase
import org.koin.dsl.module

object DomainModule {

    val domainModules = module {
        single { RegisterTwoUseCase(registerTwoRepository = get()) }
    }

}