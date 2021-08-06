package kr.hs.dgsw.jaehyeon.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.jaehyeon.viewModel.utils.BaseViewModel
import kr.hs.dgsw.jaehyeon.viewModel.utils.SingleLiveEvent

class SignupViewModel: BaseViewModel() {

    val firstPassword = MutableLiveData<String>()
    val secondPassword = MutableLiveData<String>()
    val clickCancelBtn = SingleLiveEvent<Any>()
    val clickCompleteBtn = SingleLiveEvent<Any>()


    fun onClickCancelBtn(view: View){
        clickCancelBtn.call()
    }

    fun onClickCompleteBtn(view: View){
        clickCompleteBtn.call()
    }
}