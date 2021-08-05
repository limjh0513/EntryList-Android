package kr.hs.dgsw.jaehyeon.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.VisitantItemBinding
import kr.hs.dgsw.jaehyeon.network.model.Response.VisitantResponse

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    lateinit var binding: VisitantItemBinding
    lateinit var context: Context
    var visitants = ArrayList<VisitantResponse>()

    inner class ViewHolder(private val binding: VisitantItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(visitantResponse: VisitantResponse, context: Context){
            binding.tvDate.text = visitantResponse.visitDate
            binding.tvPhone.text = visitantResponse.phoneNumber
            binding.tvResidence.text = visitantResponse.residence
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.visitant_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val visitant : VisitantResponse = visitants[position]
        holder.bind(visitant, context)
    }

    override fun getItemCount(): Int {
        return visitants.size
    }
}