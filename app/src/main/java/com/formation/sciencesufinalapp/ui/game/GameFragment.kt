package com.formation.sciencesufinalapp.ui.game

import GameViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.formation.sciencesufinalapp.databinding.FragmentGameBinding
import com.formation.sciencesufinalapp.ui.signup.SignUpViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by activityViewModels()
    private val authViewModel: SignUpViewModel by activityViewModels()

    // Binding object instance with access to the views in the game_fragment.xml layout
    private lateinit var binding: FragmentGameBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout XML file and return a binding object instance
        binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup a click listener for the Submit and Skip buttons.
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }
        // Update the UI
        binding.score.text = "0"
        binding.numberQuestions.text = "1 sur ${viewModel.allWordsList.size}"
        // Observe the currentScrambledWord LiveData.
// Observe the scrambledCharArray LiveData, passing in the LifecycleOwner and the observer.





        viewModel.currentScrambledWord.observe(viewLifecycleOwner,
            // observer les changements de la variables currentScrambledWord dans le viewModel
            { newWord ->  binding.scrambledWord.text = newWord;
                //mettre à jour la view scrambledWord  dans l'ecran
                binding.guessEdit.setText("")
                // mettre à jour le textEdit dans l'écran
            })




    }

    /*
   * Checks the user's word, and updates the score accordingly.
   * Displays the next scrambled word.
   * After the last word, the user is shown a Dialog with the final score.
   */
    private fun onSubmitWord() {
        val playerWord = binding.guessEdit.text.toString()

        if (viewModel.isUserWordCorrect(playerWord)) {
            //setErrorTextField(false)
                binding.score.text = viewModel.score.toString()
                binding.numberQuestions.text = "${(viewModel.currentWordCount + 1).toString()} sur ${viewModel.allWordsList.size}"
            if (!viewModel.nextWord()) {
                showFinalScoreDialog()
                saveScore()
            }
        } else {
            //setErrorTextField(true)
        }
    }

    /*
    * Skips the current word without changing the score.
    */
    private fun onSkipWord() {
        if (viewModel.nextWord()) {
            //setErrorTextField(false)
        } else {
            showFinalScoreDialog()
        }
    }

    /*
     * Gets a random word for the list of words and shuffles the letters in it.
     */


    /*
    * Creates and shows an AlertDialog with the final score.
    */

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
                //exitGame()
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
        viewModel.reinitializeData()
       // setErrorTextField(false)
    }

    /*
     * Exits the game.
     */
    private fun exitGame() {
        activity?.finish()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    /*
    * Sets and resets the text field error status.
    */

    /*
    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

     */

    /*
     * Displays the next scrambled word on screen.
     */

}

