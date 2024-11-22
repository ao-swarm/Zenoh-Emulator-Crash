package com.example.zenohemulatorcrash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.zenohemulatorcrash.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private var zSubscriber: ZSub? = null

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch(Dispatchers.Default) {
            zSubscriber = ZenohClient.get()
                .subscribe(
                    "crash/my/zenoh",
                    onClose = { Log.d(ZenohClient.TAG, "subscriber undeclared") }
                ).getOrNull()

            zSubscriber?.sampleFlow
                ?.onEach { sample -> Log.d(ZenohClient.TAG, "$sample") }
                ?.flowWithLifecycle(lifecycle)
                ?.flowOn(Dispatchers.Default)
                ?.launchIn(lifecycleScope)
                ?.also { Log.d(ZenohClient.TAG, "subscriber initialized") }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        zSubscriber?.undeclare()
    }
}