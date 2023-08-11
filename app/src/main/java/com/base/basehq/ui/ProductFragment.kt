package com.base.basehq.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.base.basehq.R
import com.base.basehq.databinding.FragmentProductBinding
import com.base.basehq.domain.models.Product
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductBinding.inflate(inflater)

        val product = ProductFragmentArgs.fromBundle(
            Bundle(arguments)
        ).product

        loadUIfromArgument(product)

        binding.ivBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeHost).popBackStack()
        }

        binding.btnAddToCart.setOnClickListener {
            viewModel.insertCartProduct(product.toCartProduct())
            binding.btnAddToCart.visibility = View.INVISIBLE
            binding.btnAddToCart.isEnabled = false
            binding.lyAddedToCart.visibility = View.VISIBLE
        }

        binding.btnCheckout.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeHost).navigate(
                ProductFragmentDirections.actionProductFragmentToCartFragment()
            )
        }

        return binding.root
    }


    private fun loadUIfromArgument(product: Product) {
        Glide.with(requireContext()).load(product.image).into(binding.ivProductImage)

        binding.tvProductTitle.text = product.title

        val price = requireContext().getString(R.string.price, product.price)
        binding.tvProductPrice.text = price

        binding.tvProductRating.text = product.rating?.rate.toString()

        binding.tvProductDescription.text = product.description
    }

}