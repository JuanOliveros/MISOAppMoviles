package com.example.vinilos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.FragmentAlbumBinding
import com.example.vinilos.models.Album
import com.example.vinilos.ui.adapters.AlbumCommentAdapter
import com.example.vinilos.ui.adapters.AlbumDetailAdapter
import com.example.vinilos.viewmodels.AlbumViewModel

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: AlbumDetailAdapter? = null
    private lateinit var commentRecyclerView: RecyclerView
    private var commentViewModelAdapter: AlbumCommentAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        val view = binding.root
        viewModelAdapter = AlbumDetailAdapter()
        commentViewModelAdapter = AlbumCommentAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumTracksList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

        commentRecyclerView = binding.albumCommentsList
        commentRecyclerView.layoutManager = LinearLayoutManager(context)
        commentRecyclerView.adapter = commentViewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val args: AlbumFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application, args.id)).get(
            AlbumViewModel::class.java)
        viewModel.album.observe(viewLifecycleOwner, Observer<Album> {
            it.apply {
                binding.album = this
                viewModelAdapter!!.tracks = this.tracks!!
                commentViewModelAdapter!!.comments = this.comments!!
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
        val createAlbum: Button = binding.linkTrackButton
        createAlbum.setOnClickListener(View.OnClickListener {
            val action = AlbumFragmentDirections.actionAlbumDetailToAlbumTrack(args.id)
            binding.root.findNavController().navigate(action)
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