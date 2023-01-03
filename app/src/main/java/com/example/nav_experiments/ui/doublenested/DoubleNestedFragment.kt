package com.example.nav_experiments.ui.doublenested

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.findNavController
import com.example.nav_experiments.databinding.FragmentDoubleNestedBinding

class DoubleNestedFragment : Fragment() {

    private var _binding: FragmentDoubleNestedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val navController get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoubleNestedBinding.inflate(inflater, container, false)
        binding.textHome.setOnClickListener {
            navigateBackToStartDestination()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateBackToStartDestination() {
        val startDestination = navController.graph.findStartDestination()
        println("To be found: $startDestination")
        println("Back stack:")
        navController.backQueue.forEach { println(it.destination) }
        println("=============")

        navigateUpTo(startDestination.id)
    }

    private fun navigateUpTo(
        destinationId: Int,
    ) {
        while (navController.currentBackStackEntry?.destination?.id != destinationId) {
            navController.navigateUp()
            println("Back stack:")
            navController.backQueue.forEach { println(it.destination) }
        }
    }
}
