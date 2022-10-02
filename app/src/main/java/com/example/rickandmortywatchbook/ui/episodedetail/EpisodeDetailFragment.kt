package com.example.rickandmortywatchbook.ui.episodedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortywatchbook.R
import com.example.rickandmortywatchbook.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmortywatchbook.ui.home.CharactersAdapter



class EpisodeDetailFragment : Fragment() {
    lateinit var binding: FragmentEpisodeDetailBinding
    lateinit var viewModel: EpisodeDetailViewModel
    lateinit var adapter: CharactersAdapter
    val args by navArgs<EpisodeDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(EpisodeDetailViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEpisodeById(args.episodeId)
        binding.characterList.layoutManager = LinearLayoutManager(requireActivity())
        adapter = CharactersAdapter( this::itemClick)
        binding.characterList.adapter = adapter
        viewModel.characterResponse.observe(viewLifecycleOwner) {
            adapter.characterItems = it
            binding.castCountTextView.text = it.size.toString() + " characters"
        }
        viewModel.episodeResponse.observe(viewLifecycleOwner) {
                with(it) {
                binding.episodeName.text = name
                binding.airdateTextView.text = air_date
                binding.episodeCountTextView.text = episode
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_episodeDetailFragment_to_homeFragment)
        }
        }

        fun itemClick(characterId: String) {
            val bundle = Bundle()
            bundle.putString("character_id", characterId)
            findNavController().navigate(R.id.action_episodeDetailFragment_to_characterDetailFragment, bundle)

        }

    }