package kr.hs.dgsw.jaehyeon.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.jaehyeon.viewModel.utils.BaseViewModel
import kr.hs.dgsw.jaehyeon.viewModel.utils.SingleLiveEvent

class LoginViewModel: BaseViewModel() {
    val firstPwInput = MutableLiveData<String>()
    val secondPwInput = MutableLiveData<String>()
    val cancelOnclick = SingleLiveEvent<Any>()
    val completeOnclick = SingleLiveEvent<Any>()

    fun onClickCancelBtn(view: View){
        cancelOnclick.call()
    }

    fun onClickCompleteBtn(view: View){
        completeOnclick.call()
    }
}