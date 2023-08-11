package com.base.basehq.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.base.basehq.R
import com.base.basehq.databinding.FragmentHomeBinding
import com.base.basehq.domain.interfaces.OnCategoryClickListener
import com.base.basehq.ui.categories.CategoryAdapter
import com.base.basehq.utils.NetworkResult
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), OnCategoryClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater)

        adapter = CategoryAdapter(this)

        lifecycleScope.launch {
            viewModel.getAllCategories().collect { resultState ->
                when (resultState) {
                    is NetworkResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvCategories.adapter = adapter
                        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                        binding.rvCategories.setHasFixedSize(true)
                        adapter.submitList(resultState.data)
                    }
                    is NetworkResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }


        return binding.root
    }

    override fun onItemClick(item: String, position: Int) {
        Navigation.findNavController(requireActivity(), R.id.homeHost).navigate(
            HomeFragmentDirections.actionHomeFragmentToCategoryFragment(item)
        )
    }

}