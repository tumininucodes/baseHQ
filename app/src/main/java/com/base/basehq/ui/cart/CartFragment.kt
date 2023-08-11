package com.base.basehq.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.basehq.data.db.cart.CartProduct
import com.base.basehq.databinding.FragmentCartBinding
import com.base.basehq.domain.interfaces.OnCartProductClickListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CartFragment : Fragment(), OnCartProductClickListener {

    private val viewModel: CartViewModel by viewModel()
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentCartBinding.inflate(inflater)

        adapter = CartAdapter(requireContext(), this)

        lifecycleScope.launch {
            viewModel.getCartProducts().collect {
                binding.rvCart.adapter = adapter
                binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCart.setHasFixedSize(true)
                adapter.submitList(it)
            }
        }

        return binding.root
    }

    override fun onItemClick(item: CartProduct, position: Int) {
        viewModel.removeCartProduct(item)
    }

}