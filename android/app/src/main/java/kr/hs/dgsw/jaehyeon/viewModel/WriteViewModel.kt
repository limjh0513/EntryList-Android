package kr.hs.dgsw.jaehyeon.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.jaehyeon.repository.EntryListRepository
import kr.hs.dgsw.jaehyeon.viewModel.utils.BaseViewModel
import kr.hs.dgsw.jaehyeon.viewModel.utils.SingleLiveEvent

class WriteViewModel : BaseViewModel() {
    val writeResult = MutableLiveData<Boolean>()
    private val repository = EntryListRepository()

    val clickCancelBtn = SingleLiveEvent<Any>()

    val errorInputNumber = SingleLiveEvent<Any>()
    val errorInputResidence = SingleLiveEvent<Any>()
    val errorNotChecked = SingleLiveEvent<Any>()

    val phoneNumber = MutableLiveData<String>()
    val residence = MutableLiveData<String>()
    val isCheck1 = MutableLiveData<Boolean>()
    val isCheck2 = MutableLiveData<Boolean>()

    fun cancelBtnOnClick(view: View) {
        clickCancelBtn.call()
    }

    fun completeBtnOnClick(view: View) {

        if (phoneNumber.value?.length != 13) {
            errorInputNumber.call()
        } else {
            if (residence.value?.length!! < 2) {
                errorInputResidence.call()
            } else {
                if (isCheck1.value != true) {
                    errorNotChecked.call()
                } else {
                    if (isCheck2.value != true) {
                        errorNotChecked.call()
                    } else {
                        insertVisitant()
                    }
                }
            }
        }
    }

    private fun insertVisitant() {
        addDisposable(repository.insertVisitant(phoneNumber.value.toString(),
            residence.value.toString()), object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                writeResult.value = t
            }

            override fun onError(e: Throwable) {
                onErrorValue.value = e
            }
        })
    }
}