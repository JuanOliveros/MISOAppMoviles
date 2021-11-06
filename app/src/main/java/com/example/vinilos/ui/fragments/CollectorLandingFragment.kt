package com.example.vinilos.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.widget.Button
import com.example.vinilos.EntranceActivity
import com.example.vinilos.databinding.FragmentCollectorLandingBinding
import com.example.vinilos.viewmodels.CollectorLandingViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CollectorLandingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CollectorLandingFragment : Fragment() {

    private lateinit var collectorLandingViewModel: CollectorLandingViewModel
    private var _binding: FragmentCollectorLandingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        collectorLandingViewModel =
            ViewModelProvider(this).get(CollectorLandingViewModel::class.java)

        _binding = FragmentCollectorLandingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textTitleView: TextView = binding.textTitle
        collectorLandingViewModel.title.observe(viewLifecycleOwner, Observer {
            textTitleView.text = it
        })

        val textContentView: TextView = binding.textContent
        collectorLandingViewModel.content.observe(viewLifecycleOwner, Observer {
            textContentView.text = it
        })

        val exit: Button = binding.exitCollectorButton
        exit.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, EntranceActivity::class.java)
            startActivity(intent)
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}