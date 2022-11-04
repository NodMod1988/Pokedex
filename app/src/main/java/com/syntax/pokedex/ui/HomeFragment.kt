package com.syntax.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.syntax.pokedex.PokemonViewModel
import com.syntax.pokedex.adapter.HomeAdapter
import com.syntax.pokedex.databinding.FragmentHomeBinding


class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.loadPokeList()


        binding = FragmentHomeBinding.inflate(inflater)



        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeAdapter()
        binding.pokeRecycler.adapter = homeAdapter


        viewModel.pokeList.observe(
            viewLifecycleOwner,
            Observer {
                homeAdapter.submitList(it)
            }
        )
    }
}