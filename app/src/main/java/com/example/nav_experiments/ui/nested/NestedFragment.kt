package com.example.nav_experiments.ui.nested

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.findNavController
import com.example.nav_experiments.R
import com.example.nav_experiments.databinding.FragmentNestedBinding

class NestedFragment : Fragment() {

    private var _binding: FragmentNestedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNestedBinding.inflate(inflater, container, false)
        binding.textHome.setOnClickListener {
            findNavController().navigate(R.id.navigateToDoubleNestedNavigation)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
