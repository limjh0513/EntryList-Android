package kr.hs.dgsw.jaehyeon.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ActivityMainBinding
import kr.hs.dgsw.jaehyeon.network.model.Response.VisitantResponse
import kr.hs.dgsw.jaehyeon.view.fragment.WriteFragment
import kr.hs.dgsw.jaehyeon.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), DialogInterface.OnDismissListener{
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.activity = this
        binding.vm = viewModel

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        observerViewModel()
    }

    fun observerViewModel(){
        binding.reyVisit.adapter = viewModel.adapter
        binding.reyVisit.layoutManager = linearLayoutManager

        with(viewModel){
            adapter.context = this@MainActivity.applicationContext

            visitantData.observe(this@MainActivity){
                adapter.visitants = it as ArrayList<VisitantResponse>
                adapter.notifyDataSetChanged()
            }

            clickWriteBtn.observe(this@MainActivity){
                val fragment = WriteFragment().getInstance()
                fragment.show(supportFragmentManager, fragment.tag)
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        viewModel.getVisitantList()
    }
}