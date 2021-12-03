package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.CollectorPerformerItemBinding
import com.example.vinilos.models.Performer

class CollectorPerformerAdapter : RecyclerView.Adapter<CollectorPerformerAdapter.CollectorPerformerViewHolder>(){

    var performers :List<Performer> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorPerformerViewHolder {
        val withDataBinding: CollectorPerformerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorPerformerViewHolder.LAYOUT,
            parent,
            false)
        return CollectorPerformerViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorPerformerViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.performer = performers[position]
        }
    }

    override fun getItemCount(): Int {
        return performers.size
    }

    class CollectorPerformerViewHolder(val viewDataBinding: CollectorPerformerItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_performer_item
        }
    }
}