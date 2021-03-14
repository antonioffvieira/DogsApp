package com.antoniovieira.dogsapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.antoniovieira.dogsapp.DogsApplication
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.databinding.FragmentSearchBinding
import com.antoniovieira.dogsapp.ui.search.adapter.BreedsListAdapter
import com.antoniovieira.dogsapp.utils.OffsetItemDecoration
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class SearchFragment : Fragment() {

    companion object {
        const val TAG = "SearchFragment"

        private const val VIEW_SWITCHER_LOADING_POSITION = 0
        private const val VIEW_SWITCHER_CONTENT_POSITION = 1

        private const val EMPTY_QUERY = ""

        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val searchViewModel by viewModels<SearchViewModel> {
        viewModelFactory
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var breedsListAdapter: BreedsListAdapter

    private val offsetItemDecoration by lazy {
        OffsetItemDecoration(requireContext(), R.dimen.images_list_offset)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as DogsApplication).applicationComponent.searchComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        showContent()
    }

    private fun setupUI() {
        initAdapter()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val breed = query ?: return false

                showLoading()

                searchViewModel.searchBreedByName(breed)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val closeButton: View? = binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            binding.searchView.setQuery(EMPTY_QUERY, false)
            breedsListAdapter.breeds = emptyList()
        }

        searchViewModel.breeds.observe(viewLifecycleOwner, {
            showContent()
            breedsListAdapter.breeds = it
        })

        searchViewModel.errorTitleAndMessage.observe(viewLifecycleOwner, {
            showPopup(it)
        })
    }

    private fun initAdapter() {
        breedsListAdapter = BreedsListAdapter {
            // TODO Open detail page
        }

        with(binding.breedsList) {
            addItemDecoration(offsetItemDecoration)
            adapter = breedsListAdapter
        }
    }

    private fun showContent() {
        binding.viewSwitcher.displayedChild = VIEW_SWITCHER_CONTENT_POSITION
    }

    private fun showLoading() {
        binding.viewSwitcher.displayedChild = VIEW_SWITCHER_LOADING_POSITION
    }

    private fun showPopup(errorTitleAndMessage: Pair<Int, Int>) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(errorTitleAndMessage.first)
            .setMessage(errorTitleAndMessage.second)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.breedsList.removeItemDecoration(offsetItemDecoration)

        _binding = null
    }

}