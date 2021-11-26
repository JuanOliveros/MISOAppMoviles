package com.example.vinilos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.FragmentCollectorBinding
import com.example.vinilos.models.Collector
import com.example.vinilos.ui.adapters.CollectorPerformerAdapter
import com.example.vinilos.ui.adapters.CollectorCommentAdapter
import com.example.vinilos.viewmodels.CollectorViewModel

class CollectorFragment : Fragment() {

    private var _binding: FragmentCollectorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: CollectorPerformerAdapter? = null
    private lateinit var commentRecyclerView: RecyclerView
    private var commentViewModelAdapter: CollectorCommentAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        val view = binding.root
        viewModelAdapter = CollectorPerformerAdapter()
        commentViewModelAdapter = CollectorCommentAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.collectorPerformersList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

        commentRecyclerView = binding.collectorCommentsList
        commentRecyclerView.layoutManager = LinearLayoutManager(context)
        commentRecyclerView.adapter = commentViewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val args: CollectorFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this, CollectorViewModel.Factory(activity.application, args.id)).get(
            CollectorViewModel::class.java)
        viewModel.collector.observe(viewLifecycleOwner, Observer<Collector> {
            it.apply {
                binding.collector = this
                viewModelAdapter!!.performers = this.favoritePerformers!!
                commentViewModelAdapter!!.comments = this.comments!!
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}