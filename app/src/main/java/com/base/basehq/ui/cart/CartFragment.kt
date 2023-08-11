package com.base.basehq.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.base.basehq.R
import com.base.basehq.databinding.FragmentCartBinding
import com.base.basehq.ui.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentCartBinding.inflate(inflater)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory.instance
        )[CartViewModel::class.java]


        lifecycleScope.launch {
            viewModel.getCartProducts().collect {
                println(it)
            }
        }


        return binding.root
    }

}