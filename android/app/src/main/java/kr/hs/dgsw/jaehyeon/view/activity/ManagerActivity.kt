package kr.hs.dgsw.jaehyeon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ActivityManagerBinding
import kr.hs.dgsw.jaehyeon.network.model.Response.ManagerResponse
import kr.hs.dgsw.jaehyeon.view.`interface`.DeleteOnclick
import kr.hs.dgsw.jaehyeon.viewModel.ManagerViewModel

class ManagerActivity : AppCompatActivity(), DeleteOnclick {
    lateinit var binding: ActivityManagerBinding
    lateinit var viewModel: ManagerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manager)
        viewModel = ViewModelProvider(this).get(ManagerViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel(){
        binding.reyVisitManager.adapter = viewModel.adapter
        binding.reyVisitManager.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(viewModel){
            adapter.context = this@ManagerActivity
            adapter.deleteOnclick = this@ManagerActivity

            getManagerData.observe(this@ManagerActivity){
                adapter.managerData = it as ArrayList<ManagerResponse>
                adapter.notifyDataSetChanged()
            }
            getDeleteResult.observe(this@ManagerActivity){
                Toast.makeText(this@ManagerActivity, "성공적으로 삭제했습니다.", Toast.LENGTH_SHORT).show()
                getManagerList()
            }
        }
    }

    override fun onClick(id: Int) {
        viewModel.deleteVisitant(id)
    }
}