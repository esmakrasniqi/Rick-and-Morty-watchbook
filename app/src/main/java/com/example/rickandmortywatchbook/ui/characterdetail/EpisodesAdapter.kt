package com.example.rickandmortywatchbook.ui.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortywatchbook.data.models.Episode
import com.example.rickandmortywatchbook.databinding.ItemEpisodesBinding

class EpisodesAdapter(private val onEpisodeClick: (episodeId: String) -> Unit) : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {
    var episodeItems: List<Episode> =mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val episode = episodeItems[position]
        with(holder) {
            binding.episodeCountTextView.text = episode.episode
            binding.episodeNameTextView.text = episode.name
            binding.airdateTextView.text = episode.air_date
            binding.clickButton.setOnClickListener {
                onEpisodeClick.invoke(episode.id.toString())
            }
        }
        holder.itemView.setOnClickListener {
            onEpisodeClick.invoke(episode.id.toString())
        }
    }

    override fun getItemCount(): Int = episodeItems.size
}