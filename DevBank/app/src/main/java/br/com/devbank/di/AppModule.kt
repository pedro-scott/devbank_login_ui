package br.com.devbank.di

import br.com.devbank.features.resgitertwo.apresentation.viewmodel.RegisterTwoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val viewModelModules = module {
        viewModel { RegisterTwoViewModel(registerTwoUseCase = get()) }
    }

}