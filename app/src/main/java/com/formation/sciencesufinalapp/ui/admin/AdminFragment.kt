package com.formation.sciencesufinalapp.ui.admin

import GameViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.formation.sciencesufinalapp.R
import com.formation.sciencesufinalapp.data.Cat
import com.formation.sciencesufinalapp.data.CatViewModel
import com.formation.sciencesufinalapp.databinding.FragmentAdminBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var mCatViewModel: CatViewModel
    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        mCatViewModel = ViewModelProvider(this).get(CatViewModel::class.java)

        _binding = FragmentAdminBinding.inflate(inflater, container, false)

        var sum = gameViewModel.allWordsList.size

        val newCats = mCatViewModel.readAllData

        newCats.observe(viewLifecycleOwner) { cat ->
            sum += 1
        }

        binding.sumOfCats.setText(sum.toString())


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.outlinedButton.setOnClickListener{
            val catName = binding.nicknameInput.text.toString()
            val newCats = mCatViewModel.readAllData
            val listNewCats : MutableList<String> = ArrayList()
            newCats.observe(viewLifecycleOwner) {cat ->
                listNewCats.add(cat.)
            }


            mCatViewModel = ViewModelProvider(this).get(CatViewModel::class.java)
            mCatViewModel.addCat(Cat(0,catName))
            binding.nicknameInput.setText("")

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

        }

        binding.backToHome.setOnClickListener{
            view.findNavController().navigate(R.id.action_adminFragment_to_homeFragment)
        }
    }
}