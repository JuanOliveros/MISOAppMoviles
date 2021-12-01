package com.example.vinilos.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
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

        val genreSpinner: Spinner = binding.albumGenreField
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.genres_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genreSpinner.adapter = adapter
        }

        val recordLabelSpinner: Spinner = binding.albumRecordLabelField
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.record_labels_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            recordLabelSpinner.adapter = adapter
        }

        val submitButton : Button = binding.submitAlbumCreateButton
        submitButton.setOnClickListener(View.OnClickListener {
            val params = mutableMapOf<String, String>()
            params["name"] = binding.albumNameField.text.toString()
            params["cover"] = binding.albumCoverField.text.toString()
            params["releaseDate"] = binding.albumReleaseDateField.text.toString()
            params["genre"] = binding.albumGenreField.selectedItem.toString()
            params["recordLabel"] = binding.albumRecordLabelField.selectedItem.toString()
            params["description"] = binding.albumDescriptionField.text.toString()
            Log.i("fragment", params.toString())
            albumCreateViewModel.saveData(params)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}