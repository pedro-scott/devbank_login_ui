package br.com.devbank.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import br.com.devbank.utils.Command

abstract class BaseFragment : Fragment() {

    abstract protected val command: MutableLiveData<Command>

}