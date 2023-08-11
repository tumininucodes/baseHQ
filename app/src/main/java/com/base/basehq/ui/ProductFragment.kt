package com.base.basehq.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.base.basehq.R
import com.base.basehq.databinding.FragmentProductBinding
import com.base.basehq.domain.models.Product
import com.bumptech.glide.Glide

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductViewModel
    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductBinding.inflate(inflater)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory.instance
        )[ProductViewModel::class.java]

        product = ProductFragmentArgs.fromBundle(
            Bundle(arguments)
        ).product

        binding.ivBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeHost).popBackStack()
        }

        loadUIfromArgument()







        return binding.root
    }


    private fun loadUIfromArgument() {
        Glide.with(requireContext()).load(product.image).into(binding.ivProductImage)

        binding.tvProductTitle.text = product.title

        val price = requireContext().getString(R.string.price, product.price)
        binding.tvProductPrice.text = price

        binding.tvProductRating.text = product.rating?.rate.toString()

        binding.tvProductDescription.text = product.description
    }

}