package com.base.basehq.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.base.basehq.R
import com.base.basehq.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater)

        val args = CategoryFragmentArgs.fromBundle(
            Bundle(arguments)
        )

        binding.tvCategory.text = args.category

        binding.ivBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeHost).popBackStack()
        }

        return binding.root
    }

}