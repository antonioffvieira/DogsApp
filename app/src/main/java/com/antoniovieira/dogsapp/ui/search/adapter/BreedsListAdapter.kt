package com.antoniovieira.dogsapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.data.model.Breed
import com.antoniovieira.dogsapp.databinding.ListItemBreedBinding

class BreedsListAdapter(
    private val onItemSelected: (Breed) -> Unit
) : RecyclerView.Adapter<BreedsListAdapter.BreedViewHolder>() {

    var breeds: List<Breed> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding = ListItemBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedViewHolder(binding)
    }

    override fun getItemCount() = breeds.size

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(breeds[position])
    }

    inner class BreedViewHolder(private val binding: ListItemBreedBinding): RecyclerView.ViewHolder(binding.root) {

        private val emptyValue by lazy {
            binding.root.context.getString(R.string.empty_value)
        }

        fun bind(breed: Breed) {
            binding.breedName.text = breed.name
            binding.breedGroup.text = if (!breed.group.isNullOrEmpty()) breed.group else emptyValue
            binding.breedOrigin.text = breed.origin ?: emptyValue

            binding.root.setOnClickListener {
                onItemSelected(breed)
            }
        }
    }
}