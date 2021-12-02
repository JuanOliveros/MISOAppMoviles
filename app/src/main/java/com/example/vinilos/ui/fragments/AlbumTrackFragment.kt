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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentAlbumBinding
import com.example.vinilos.databinding.FragmentAlbumTrackBinding
import com.example.vinilos.models.Album
import com.example.vinilos.ui.adapters.AlbumDetailAdapter
import com.example.vinilos.ui.adapters.AlbumCommentAdapter
import com.example.vinilos.viewmodels.AlbumTrackViewModel
import com.example.vinilos.viewmodels.AlbumViewModel

class AlbumTrackFragment : Fragment() {

    private lateinit var albumTrackViewModel: AlbumTrackViewModel
    private var _binding: FragmentAlbumTrackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumTrackBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        val args: AlbumFragmentArgs by navArgs()
        albumTrackViewModel = ViewModelProvider(this, AlbumTrackViewModel.Factory(activity.application, args.id)).get(
            AlbumTrackViewModel::class.java)

        val submitButton : Button = binding.createTrackBtn
        submitButton.setOnClickListener(View.OnClickListener {
            val params = mutableMapOf<String, String>()
            params["name"] = binding.trackNameField.text.toString()
            params["duration"] = binding.trackDurationField.text.toString()

            albumTrackViewModel.saveData(params,args.id)

            albumTrackViewModel.createResult.observe(viewLifecycleOwner, Observer<Int> {
                it.apply {
                    if (this == 200) {
                        Toast.makeText(activity, "La canción fue creada", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(activity, "¡Hubo un error!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}