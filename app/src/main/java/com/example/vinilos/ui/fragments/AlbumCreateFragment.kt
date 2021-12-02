package com.example.vinilos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
        val view = binding.root

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
            albumCreateViewModel.saveData(params)

            albumCreateViewModel.createResult.observe(viewLifecycleOwner, Observer<Int> {
                it.apply {
                    if (this == 200) {
                        Toast.makeText(activity, "El álbum fue creado", Toast.LENGTH_SHORT).show()
                        val action = AlbumCreateFragmentDirections.actionAlbumCreateToNavAlbums()
                        view.findNavController().navigate(action)
                    } else {
                        Toast.makeText(activity, "¡Hubo un error!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}