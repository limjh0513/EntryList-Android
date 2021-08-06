package kr.hs.dgsw.jaehyeon.viewModel

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.jaehyeon.network.model.Response.ManagerResponse
import kr.hs.dgsw.jaehyeon.repository.EntryListRepository
import kr.hs.dgsw.jaehyeon.view.adapter.ManagerAdapter
import kr.hs.dgsw.jaehyeon.viewModel.utils.BaseViewModel

class ManagerViewModel: BaseViewModel() {
    val repository = EntryListRepository()
    val adapter: ManagerAdapter = ManagerAdapter()
    val getManagerData = MutableLiveData<List<ManagerResponse>>()
    val getDeleteResult = MutableLiveData<Boolean>()

    init{
        getManagerList()
    }

    fun getManagerList(){
        addDisposable(repository.getManagerList(), object : DisposableSingleObserver<List<ManagerResponse>>(){
            override fun onSuccess(t: List<ManagerResponse>) {
                getManagerData.value = t
            }

            override fun onError(e: Throwable) {
                onErrorValue.value = e
            }

        })
    }

    fun deleteVisitant(id: Int){
        addDisposable(repository.deleteVisitant(id), object: DisposableSingleObserver<Boolean>(){
            override fun onSuccess(t: Boolean) {
                getDeleteResult.value = t
            }

            override fun onError(e: Throwable) {
                onErrorValue.value = e
            }
        })
    }
}