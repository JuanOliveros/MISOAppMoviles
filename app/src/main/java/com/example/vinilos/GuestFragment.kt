package com.example.vinilos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentGuestBinding
import com.example.vinilos.GuestViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [GuestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GuestFragment : Fragment() {

    private lateinit var guestViewModel: GuestViewModel
    private var _binding: FragmentGuestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        guestViewModel =
            ViewModelProvider(this).get(GuestViewModel::class.java)

        _binding = FragmentGuestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textTitleView: TextView = binding.textTitle
        guestViewModel.title.observe(viewLifecycleOwner, Observer {
            textTitleView.text = it
        })

        val textContentView: TextView = binding.textContent
        guestViewModel.content.observe(viewLifecycleOwner, Observer {
            textContentView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}