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

        val sum = gameViewModel.allWordsList.size

        binding.sumOfCats.setText(sum.toString())


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.outlinedButton.setOnClickListener{
            val catName = binding.nicknameInput.text.toString()

            val newCats = mCatViewModel.readAllData

            val catList: MutableList<String> = ArrayList()

            newCats.observe(viewLifecycleOwner) { cats ->
                cats.forEach { cat ->
                    catList.add(cat.name)
                }
            }

            if (!catList.contains(catName) and !gameViewModel.allWordsList.contains(catName) and (catName.length > 2) and (catName != "Chat")){

            mCatViewModel = ViewModelProvider(this).get(CatViewModel::class.java)
            mCatViewModel.addCat(Cat(0,catName))
            binding.nicknameInput.setText("")

                val str = binding.sumOfCats.text.toString()
                val num = str.toInt()
                binding.sumOfCats.setText((num + 1).toString())

            Toast.makeText(requireContext(), "Chat ajouté !", Toast.LENGTH_LONG).show()}

            else {
                Toast.makeText(requireContext(), "Nom invalide !", Toast.LENGTH_LONG).show()
            }
        }

        binding.backToHome.setOnClickListener{
            view.findNavController().navigate(R.id.action_adminFragment_to_homeFragment)
        }
    }
}