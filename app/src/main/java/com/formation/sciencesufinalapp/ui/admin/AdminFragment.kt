package com.formation.sciencesufinalapp.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.formation.sciencesufinalapp.R
import com.formation.sciencesufinalapp.data.Cat
import com.formation.sciencesufinalapp.data.CatViewModel
import com.formation.sciencesufinalapp.databinding.FragmentAdminBinding

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
    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var mCatViewModel: CatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.outlinedButton.setOnClickListener{
            val catName = binding.nicknameInput.text.toString()
            mCatViewModel = ViewModelProvider(this).get(CatViewModel::class.java)
            mCatViewModel.addCat(Cat(0,catName))

        }
    }

}