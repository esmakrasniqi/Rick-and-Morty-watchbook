package com.example.rickandmortywatchbook.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortywatchbook.R
import com.example.rickandmortywatchbook.data.models.Character
import com.example.rickandmortywatchbook.databinding.DialogAdvancedFiltersBinding
import com.example.rickandmortywatchbook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var adapter: CharactersAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacters()

        binding.filterButton.setOnClickListener {
            showDetailedFilterDialog()
        }

        binding.characterList.layoutManager = LinearLayoutManager(requireActivity())
        adapter = CharactersAdapter( this::itemClick)
        binding.characterList.adapter = adapter
        viewModel.charactersList.observe(viewLifecycleOwner, Observer<List<Character>>() {
            adapter.characterItems = it
        })
        binding.searchEditText.doOnTextChanged{ text, start, before, count ->
            viewModel.charactersList.value?.let { safeCharacters ->
                adapter.characterItems = safeCharacters.filter { character ->
                    character.name.startsWith(text.toString(), true)
                }
            }

        }

    }

    private fun showDetailedFilterDialog() {
        val dialogBinding = DialogAdvancedFiltersBinding.inflate(requireActivity().layoutInflater)
        val dialog = Dialog(requireActivity())
        dialog.setContentView(dialogBinding.root)
        val layoutParams = dialog.window!!.attributes
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window!!.attributes = layoutParams
        dialogBinding.closeButton.setOnClickListener { dialog.dismiss() }
        dialogBinding.applyButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    fun itemClick(characterId: String) {
        val bundle = Bundle()
        bundle.putString("character_id", characterId)
        findNavController().navigate(R.id.action_homeFragment_to_characterDetailFragment, bundle)

    }
}