package kr.hs.dgsw.jaehyeon.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.jaehyeon.network.model.Response.VisitantResponse
import kr.hs.dgsw.jaehyeon.repository.EntryListRepository
import kr.hs.dgsw.jaehyeon.view.activity.MainActivity
import kr.hs.dgsw.jaehyeon.view.adapter.MainAdapter
import kr.hs.dgsw.jaehyeon.viewModel.utils.BaseViewModel
import kr.hs.dgsw.jaehyeon.viewModel.utils.SingleLiveEvent

class MainViewModel : BaseViewModel() {
    private val repository = EntryListRepository()

    val adapter: MainAdapter = MainAdapter()
    val visitantData = MutableLiveData<List<VisitantResponse>>()

    val clickWriteBtn = SingleLiveEvent<Any>()
    val clickManagerBtn = SingleLiveEvent<Any>()

    init{
        getVisitantList()
    }

    fun getVisitantList(){
        addDisposable(repository.getVisitantList(), object : DisposableSingleObserver<List<VisitantResponse>>(){
            override fun onSuccess(t: List<VisitantResponse>) {
                visitantData.value = t
            }

            override fun onError(e: Throwable) {
                onErrorValue.value = e
                e.printStackTrace()
            }
        })
    }

    fun managerBtnOnClick(view: View){
        clickManagerBtn.call()
    }

    fun writeBtnOnClick(view: View){
        clickWriteBtn.call()
    }
}