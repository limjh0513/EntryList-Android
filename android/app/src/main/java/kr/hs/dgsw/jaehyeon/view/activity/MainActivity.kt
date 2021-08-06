package kr.hs.dgsw.jaehyeon.view.activity

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ActivityMainBinding
import kr.hs.dgsw.jaehyeon.network.model.Response.VisitantResponse
import kr.hs.dgsw.jaehyeon.view.fragment.LoginDialogFragment
import kr.hs.dgsw.jaehyeon.view.fragment.SignupDialogFragment
import kr.hs.dgsw.jaehyeon.view.fragment.WriteDialogFragment
import kr.hs.dgsw.jaehyeon.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), DialogInterface.OnDismissListener{
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.activity = this
        binding.vm = viewModel
        binding.lifecycleOwner = this

        observeViewModel()
    }

    private fun observeViewModel(){
        binding.reyVisit.adapter = viewModel.adapter
        binding.reyVisit.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        with(viewModel){
            adapter.context = this@MainActivity.applicationContext

            visitantData.observe(this@MainActivity){
                adapter.visitants = it as ArrayList<VisitantResponse>
                adapter.notifyDataSetChanged()
            }

            clickWriteBtn.observe(this@MainActivity){
                val dialogFragment = WriteDialogFragment().getInstance()
                dialogFragment.show(supportFragmentManager, dialogFragment.tag)
            }

            clickManagerBtn.observe(this@MainActivity){
                checkExistPassword()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        viewModel.getVisitantList()
    }

    private fun checkExistPassword(){
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val firstPw = sharedPref.getString("firstPw1", null)
        val secondPw = sharedPref.getString("secondPw2", null)

        if(firstPw == null || secondPw == null){
            val dialogFragment = SignupDialogFragment().getInstance()
            dialogFragment.show(supportFragmentManager, dialogFragment.tag)
        } else {
            val dialogFragment = LoginDialogFragment().getInstance()
            dialogFragment.show(supportFragmentManager, dialogFragment.tag)
        }
    }
}