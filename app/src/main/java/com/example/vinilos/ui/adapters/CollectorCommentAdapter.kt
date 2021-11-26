package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.CollectorCommentItemBinding
import com.example.vinilos.models.Comment

class CollectorCommentAdapter : RecyclerView.Adapter<CollectorCommentAdapter.CollectorCommentViewHolder>(){

    var comments :List<Comment> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorCommentViewHolder {
        val withDataBinding: CollectorCommentItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorCommentViewHolder.LAYOUT,
            parent,
            false)
        return CollectorCommentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorCommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.comment = comments[position]
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    class CollectorCommentViewHolder(val viewDataBinding: CollectorCommentItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_comment_item
        }
    }
}