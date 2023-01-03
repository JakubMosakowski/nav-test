package com.example.nav_experiments.ui.doublenested

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.findNavController
import com.example.nav_experiments.databinding.FragmentNestedBinding

class DoubleNestedFragment : Fragment() {

    private var _binding: FragmentNestedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val navController get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNestedBinding.inflate(inflater, container, false)
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
        println()
        println()
        println()
        navController.backQueue.forEach { println(it.destination) }
        println()
        println()
        println()

        val startDestination = navController.graph.findStartDestination()
        println("To be found: $startDestination")
        println("Back stack:")
        navController.backQueue.forEach { println(it.destination) }
        println("=============")

        navigateUpTo(startDestination.id)
    }

    private fun navigateUpTo(
        destinationId: Int,
        fallback: () -> Unit = { },
    ) {

        navController.navigateUp()
        println("Back stack:")
        navController.backQueue.forEach { println(it.destination) }

        while (navController.currentBackStackEntry?.destination?.id != destinationId) {
            navController.navigateUp()
            println("Back stack:")
            navController.backQueue.forEach { println(it.destination) }
        }
    }

    fun navigate(@IdRes resId: Int, @Nullable args: Bundle? = null) {
        navController.navigate(resId, args)
    }

    private fun isInBackStack(@IdRes destinationId: Int): Boolean {
        return try {
            navController.getBackStackEntry(destinationId)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}
