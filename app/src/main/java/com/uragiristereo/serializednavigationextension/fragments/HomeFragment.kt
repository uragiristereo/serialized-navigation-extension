package com.uragiristereo.serializednavigationextension.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uragiristereo.serializednavigationextension.SneRoute
import com.uragiristereo.serializednavigationextension.databinding.FragmentHomeBinding
import com.uragiristereo.serializednavigationextension.runtime.navigate

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGoToNotification.setOnClickListener {
            findNavController().navigate(SneRoute.Notification(message = "hello? & goodbye"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
