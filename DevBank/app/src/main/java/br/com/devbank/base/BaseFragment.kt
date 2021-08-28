package br.com.devbank.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import br.com.devbank.utils.Command

abstract class BaseFragment : Fragment() {

    protected abstract val command: MutableLiveData<Command>

}