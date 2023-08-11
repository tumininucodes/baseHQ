package com.base.basehq.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.base.basehq.R
import com.base.basehq.databinding.FragmentCategoryBinding
import com.base.basehq.domain.interfaces.OnProductClickListener
import com.base.basehq.data.db.product.Product
import com.base.basehq.ui.product.ProductAdapter
import com.base.basehq.utils.NetworkResult
import com.base.basehq.utils.capitalise
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoryFragment : Fragment(), OnProductClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoryViewModel by viewModel()
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater)

        val args = CategoryFragmentArgs.fromBundle(
            Bundle(arguments)
        )

        adapter = ProductAdapter(requireContext(), this)

        lifecycleScope.launch {
            viewModel.getProductsInCategory(args.category).collect { productsState ->
                when (productsState) {
                    is NetworkResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvProducts.adapter = adapter
                        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
                        binding.rvProducts.setHasFixedSize(true)
                        adapter.submitList(productsState.data)
                    }
                    is NetworkResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

        binding.tvCategory.text = args.category.capitalise()

        binding.ivBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeHost).popBackStack()
        }

        return binding.root
    }

    override fun onItemClick(item: Product, position: Int) {
        Navigation.findNavController(requireActivity(), R.id.homeHost).navigate(
            CategoryFragmentDirections.actionCategoryFragmentToProductFragment(
                item)
        )
    }

}