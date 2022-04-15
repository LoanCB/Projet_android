package com.formation.sciencesufinalapp.ui.home

import GameViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.formation.sciencesufinalapp.databinding.FragmentHomeBinding
import com.formation.sciencesufinalapp.ui.signup.SignUpViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.outlinedButton2.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGameFragment()
            view.findNavController().navigate(action)
        }

        binding.outlinedButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAdminFragment()
            view.findNavController().navigate(action)
        }

        binding.exitButton.setOnClickListener {
            activity?.finish()
        }

        viewModel.currentPlayer.observe(viewLifecycleOwner) { playerName ->
            binding.helloText.text = playerName
        }

        val recyclerview = binding.recyclerview

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(activity)

        gameViewModel.savedGames.observe(viewLifecycleOwner
        ) { newList ->
            if (newList.isNotEmpty()) {
                binding.lastScore.text = "${newList[0]["player"]} : ${newList[0]["score"]}";

                // ArrayList of class ItemsViewModel
                val data = ArrayList<ItemsViewModel>()

                // the image with the count of view
                for (item in newList) {
                    data.add(ItemsViewModel(name = item["player"]!!, item["score"]!!))
                }

                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(data)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
            }
        }
    }
}
