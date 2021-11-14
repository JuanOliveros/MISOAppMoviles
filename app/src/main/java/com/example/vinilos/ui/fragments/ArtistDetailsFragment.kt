package com.example.vinilos.ui.fragments

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentArtistDetailsBinding
import com.example.vinilos.models.Perfomer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.android.synthetic.main.fragment_artist_details.*

import android.widget.TextView
import com.example.vinilos.R
import com.squareup.picasso.Picasso

class ArtistDetailsFragment () : Fragment() {

    private var _binding: FragmentArtistDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var artist: Perfomer

    companion object {
        const val CONTENT = "content"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mapper = jacksonObjectMapper()

        arguments?.let {
            artist = mapper.readValue(it.get(CONTENT).toString())
        }

        _binding = FragmentArtistDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //val textView: TextView = root.findViewById(R.id.)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val root: View = binding.root
        val artistImage: ImageView = view.findViewById(R.id.artist_image)
        Picasso.get().load(artist.image).into(artistImage)
        val artistName: TextView = view.findViewById(R.id.artist_detail_name)
        artistName.text = artist.name
        val artistDes: TextView = view.findViewById(R.id.artist_detail_description)
        artistDes.text = artist.description

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}