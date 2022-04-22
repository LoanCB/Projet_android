package com.formation.sciencesufinalapp.ui.game

import GameViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.formation.sciencesufinalapp.databinding.FragmentGameBinding
import com.formation.sciencesufinalapp.ui.signup.SignUpViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by activityViewModels()
    private val authViewModel: SignUpViewModel by activityViewModels()
    private lateinit var binding: FragmentGameBinding
    private var chances = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * Inflate the layout for this fragment
        * Inflate the layout XML file and return a binding object instance
        */
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup a click listener for the Submit and Skip buttons and update UI
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }
        resetUi()

        viewModel.currentScrambledWord.observe(viewLifecycleOwner) { newWord ->
            binding.scrambledWord.text = newWord
            binding.guessEdit.setText("")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetUi() {
        binding.score.text = "0"
        binding.numberQuestions.text = "1 chat sur 15"
    }

    @SuppressLint("SetTextI18n")
    private fun nextWordUi() {
        binding.wrongWord.text = ""
        binding.numberQuestions.text = "${viewModel.currentWordCount} chats sur 15"
    }

    private fun saveScore(){
        val savedGame = mapOf<String,String>("player" to authViewModel.currentPlayer.value!!, "score" to viewModel.score.toString())
        viewModel.saveGame(savedGame)
    }

    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Felicitation !")
            .setMessage("votre score est ${viewModel.score.toString()}")
            .setCancelable(true)
            .setNegativeButton("Quitter") { _, _ ->
                exitGame()
            }
            .setPositiveButton("Rejouer") { _, _ ->
                restartGame()
            }
            .show()
    }

    /*
     * Re-initializes the data in the ViewModel and updates the views with the new data, to
     * restart the game.
     */
    private fun restartGame() {
        resetUi()
        viewModel.reinitializeData()
    }

     // Exits the game
    private fun exitGame() {
        viewModel.reinitializeData()
        val action = GameFragmentDirections.actionGameFragmentToHomeFragment()
        view?.findNavController()?.navigate(action)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    /*
    * Checks the user's word, and updates the score accordingly.
    * Displays the next scrambled word.
    * After the last word, the user is shown a Dialog with the final score.
    */
    @SuppressLint("SetTextI18n")
    private fun onSubmitWord() {
        val playerWord = binding.guessEdit.text.toString()

        if (viewModel.isUserWordCorrect(playerWord)) {
            binding.score.text = viewModel.score.toString()
            onSkipWord()
        } else {
            chances -= 1
            binding.wrongWord.text = "Mauvaise réponse, il vous reste ${chances} chances"
        }
        if (chances == 0) {
            onSkipWord()
        }
        if (!viewModel.nextWord(next = false)) {
            showFinalScoreDialog()
            saveScore()
        }
    }

    // Skips the current word without changing the score.
    @SuppressLint("SetTextI18n")
    private fun onSkipWord() {
        chances = 3
        if (viewModel.nextWord()) {
            nextWordUi()
        } else {
            showFinalScoreDialog()
            saveScore()
        }
    }

}
