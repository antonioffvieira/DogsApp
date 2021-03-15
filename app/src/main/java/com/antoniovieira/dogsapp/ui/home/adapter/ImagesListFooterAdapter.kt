package com.antoniovieira.dogsapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antoniovieira.dogsapp.databinding.ListItemImageFooterBinding
import com.antoniovieira.dogsapp.utils.ExceptionHelper

class ImagesListFooterAdapter(
    private val onRetryClicked: () -> Unit
) : LoadStateAdapter<ImagesListFooterAdapter.FooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val binding =
            ListItemImageFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FooterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class FooterViewHolder(private val binding: ListItemImageFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMessage.text =
                    binding.root.context.getString(ExceptionHelper.getExceptionMessage(loadState.error).second)
            }

            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading
            binding.errorMessage.isVisible = loadState !is LoadState.Loading

            binding.retryButton.setOnClickListener { onRetryClicked() }
        }

    }

}