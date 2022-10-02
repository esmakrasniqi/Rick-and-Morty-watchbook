package com.example.rickandmortywatchbook.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortywatchbook.R
import com.example.rickandmortywatchbook.data.models.Character
import com.example.rickandmortywatchbook.data.models.Episode

import com.example.rickandmortywatchbook.databinding.FragmentCharacterDetailBinding
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {
    lateinit var binding: FragmentCharacterDetailBinding
    lateinit var viewModel: CharacterDetailViewModel
    lateinit var adapter: EpisodesAdapter
    val args by navArgs<CharacterDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CharacterDetailViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterById(args.characterId)
//viewModel.characterRespose.observe(viewLifecycleOwner, Observer<Character>() {
        viewModel.characterRespose.observe(viewLifecycleOwner) {
            with(it) {
                Picasso.get().load(image).into(binding.characterImage)
                binding.characterName.text = name

                binding.genderInfo.tagTextView.text = "Gender"
                binding.genderInfo.dataTextView.text = gender

                binding.originInfo.tagTextView.text = "Origin"
                binding.originInfo.dataTextView.text = origin.name

                binding.statusInfo.tagTextView.text = "Status"
                binding.statusInfo.dataTextView.text = status

                binding.typeInfo.tagTextView.text = "Type"
                binding.typeInfo.dataTextView.text = type

                binding.specieInfo.tagTextView.text = "Specie"
                binding.specieInfo.dataTextView.text = species

                binding.locationInfo.tagTextView.text = "Location"
                binding.locationInfo.dataTextView.text = location.name

            }
            //}
            binding.episodesList.layoutManager = LinearLayoutManager(requireActivity())
            adapter = EpisodesAdapter( this::onEpisodeClick)
            binding.episodesList.adapter = adapter
            viewModel.episodeResponse.observe(viewLifecycleOwner) {
                val episodes = it
                adapter.episodeItems = episodes
                binding.episodeCountTextView.text = it.size.toString() + " episodes"
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_characterDetailFragment_to_homeFragment)
        }
    }

    fun onEpisodeClick(episodeId: String) {
        val bundle = Bundle()
        bundle.putString("episode_id", episodeId)
        findNavController().navigate(R.id.action_characterDetailFragment_to_episodeDetailFragment, bundle)

    }
}
