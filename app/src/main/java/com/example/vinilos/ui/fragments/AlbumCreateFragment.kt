package com.example.vinilos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentAlbumCreateBinding
import com.example.vinilos.viewmodels.AlbumCreateViewModel

class AlbumCreateFragment : Fragment() {

    private lateinit var albumCreateViewModel: AlbumCreateViewModel
    private var _binding: FragmentAlbumCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        albumCreateViewModel = ViewModelProvider(this).get(AlbumCreateViewModel::class.java)

        _binding = FragmentAlbumCreateBinding.inflate(inflater, container, false)

        val submitButton : Button = binding.submitButton
        submitButton.setOnClickListener(View.OnClickListener {
            val params = mutableMapOf<String, String>()
            params["albumName"] = binding.albumNameField.text.toString()
            params["albumCover"] = binding.albumCoverField.text.toString()
            params["albumReleaseDate"] = binding.albumReleaseDateField.text.toString()
            params["albumGenre"] = binding.albumGenreField.text.toString()
            params["albumRecordLabel"] = binding.albumRecordLabelField.text.toString()
            params["albumDescription"] = binding.albumDescriptionField.text.toString()
            albumCreateViewModel.saveData(params)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}