package com.antoniovieira.dogsapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.antoniovieira.dogsapp.DogsApplication
import com.antoniovieira.dogsapp.databinding.FragmentSearchBinding
import javax.inject.Inject

class SearchFragment : Fragment() {

    companion object {
        const val TAG = "SearchFragment"

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

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}