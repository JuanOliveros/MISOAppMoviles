package com.example.vinilos.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.vinilos.databinding.FragmentAlbumBinding
import com.example.vinilos.models.Album
import com.example.vinilos.viewmodels.AlbumViewModel

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        //_binding!!.lifecycleOwner = this
        val view = binding.root
        return view
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
                //binding.album = this
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