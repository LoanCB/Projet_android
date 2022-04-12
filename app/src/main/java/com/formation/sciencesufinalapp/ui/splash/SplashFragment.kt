package com.formation.sciencesufinalapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.formation.sciencesufinalapp.R
import com.formation.sciencesufinalapp.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {


    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progress.setOnClickListener {
            val action = SplashFragmentDirections.actionSplashFragmentToSignupFragment()
            view.findNavController().navigate(action)
        }
    }


}