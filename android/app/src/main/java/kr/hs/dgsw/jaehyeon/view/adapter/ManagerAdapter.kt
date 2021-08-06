package kr.hs.dgsw.jaehyeon.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.ManagerItemBinding
import kr.hs.dgsw.jaehyeon.network.model.Response.ManagerResponse
import kr.hs.dgsw.jaehyeon.view.`interface`.DeleteOnclick
import kr.hs.dgsw.jaehyeon.viewModel.ManagerViewModel

class ManagerAdapter : RecyclerView.Adapter<ManagerAdapter.ViewHolder>() {
    lateinit var binding: ManagerItemBinding
    lateinit var viewModel: ManagerViewModel
    lateinit var context: Context

    var managerData = ArrayList<ManagerResponse>()
    lateinit var deleteOnclick: DeleteOnclick

    inner class ViewHolder(private val binding: ManagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ManagerResponse, context: Context) {
            binding.tvDateManger.text = data.visitDate
            binding.tvPhoneManager.text = data.phoneNumber
            binding.tvAreaManager.text = data.residence
            binding.btnDelete.setOnClickListener {
                deleteOnclick.onClick(data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.manager_item,
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(managerData[position], context)
    }

    override fun getItemCount(): Int {
        return managerData.size
    }
}