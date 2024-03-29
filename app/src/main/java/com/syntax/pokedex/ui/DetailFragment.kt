package com.syntax.pokedex.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import coil.load
import com.syntax.pokedex.PokemonViewModel
import com.syntax.pokedex.R
import com.syntax.pokedex.data.local.databasemodel.DatabasePokemon
import com.syntax.pokedex.databinding.FragmentDetailBinding


class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name: String = requireArguments().getString("name", "test")

        viewModel.loadPokeDetails(name)

        viewModel.pokemonDetails.observe(
            viewLifecycleOwner,
            Observer {
                binding.imgPokemonPic.load(it.picture)
                binding.txtPokeId.text = "# "+ it.pokeId.toString()
                binding.txtPokeName.text = it.name.capitalize()
                binding.txtPokeHeight.text = (it.height.toFloat().div(10)).toString()  + " m"
                binding.txtPokeWeight.text = (it.weight.toFloat().div(10)).toString() + " Kg"
                binding.cvPokemonDetail.setBackgroundResource(
                    when(it.primaryType){
                        "fire" -> R.drawable.gradient_fire
                        "grass" -> R.drawable.gradient_grass
                        "water" -> R.drawable.gradient_water
                        "bug" -> R.drawable.gradient_bug
                        "poison" -> R.drawable.gradient_poison
                        "psychic" ->R.drawable.gradient_psychic
                        "electric" -> R.drawable.gradient_electric
                        "fighting" -> R.drawable.gradient_fighting
                        "dragon" -> R.drawable.gradient_dragon
                        "dark" -> R.drawable.gradient_dark
                        "normal" -> R.drawable.gradient_normal
                        "fairy" -> R.drawable.gradient_fairy
                        "flying" -> R.drawable.gradient_flying
                        "steel" -> R.drawable.gradient_steel
                        "ice" -> R.drawable.gradient_ice
                        "rock" -> R.drawable.gradient_rock
                        "ghost" -> R.drawable.gradient_ghost
                        "ground" -> R.drawable.gradient_ground
                        else -> R.drawable.gradient_normal

                    }
                )
                binding.imgTypeOne.setImageResource(
                    when(it.primaryType){
                        "fire" -> R.drawable.fire
                        "grass" -> R.drawable.grass
                        "water" -> R.drawable.water
                        "bug" -> R.drawable.bug
                        "poison" -> R.drawable.poison
                        "psychic" ->R.drawable.psychic
                        "electric" -> R.drawable.electric
                        "fighting" -> R.drawable.fighting
                        "dragon" -> R.drawable.dragon
                        "dark" -> R.drawable.dark
                        "normal" -> R.drawable.normal
                        "fairy" -> R.drawable.fairy
                        "flying" -> R.drawable.flying
                        "steel" -> R.drawable.steel
                        "ice" -> R.drawable.ice
                        "rock" -> R.drawable.rock
                        "ghost" -> R.drawable.ghost
                        else -> R.drawable.ground
                    }
                )

                if(it.secondaryType!= null){
                    binding.imgTypeTwo.setImageResource(
                        when(it.secondaryType){
                            "fire" -> R.drawable.fire
                            "grass" -> R.drawable.grass
                            "water" -> R.drawable.water
                            "bug" -> R.drawable.bug
                            "poison" -> R.drawable.poison
                            "psychic" ->R.drawable.psychic
                            "electric" -> R.drawable.electric
                            "fighting" -> R.drawable.fighting
                            "dragon" -> R.drawable.dragon
                            "dark" -> R.drawable.dark
                            "normal" -> R.drawable.normal
                            "fairy" -> R.drawable.fairy
                            "flying" -> R.drawable.flying
                            "steel" -> R.drawable.steel
                            "ice" -> R.drawable.ice
                            "rock" -> R.drawable.rock
                            "ghost" -> R.drawable.ghost
                            "ground" -> R.drawable.ground
                            else -> R.drawable.ground

                        }
                    )
                }else{
                    binding.imgTypeTwo.isInvisible = true
                }

                if(it.isFavorite){
                    binding.removeBtn.isVisible = true
                    binding.addBtn.isVisible = false
                    binding.removeBtn.setOnClickListener {
                        viewModel.removeFavorite(name)
                        binding.removeBtn.isVisible = false
                        binding.addBtn.isVisible = true
                    }
                }else if(!it.isFavorite){
                    binding.removeBtn.isVisible = false
                    binding.addBtn.isVisible = true
                    binding.addBtn.setOnClickListener {
                        viewModel.addToFavorites(name)
                        binding.removeBtn.isVisible = true
                        binding.addBtn.isVisible = false
                    }
                }


                binding.hpBar.progress = it.hp
                binding.attackBar.progress = it.attack
                binding.defenseBar.progress = it.defense
                binding.sAttackBar.progress = it.specialAttack
                binding.sDefenseBar.progress = it.specialDefense
                binding.speedBar.progress = it.speed

                binding.hpText.text = "HP : " + it.hp.toString()+" "
                binding.attText.text = "ATT : " + it.attack.toString()+" "
                binding.defText.text = "DEF : " + it.defense.toString()+" "
                binding.sAttText.text = "SAtt : " + it.specialAttack.toString()+" "
                binding.sDefText.text = "SDef : " + it.specialDefense.toString()+" "
                binding.speedText.text = "Speed : " + it.speed.toString() +" "

                val animatorhp = ObjectAnimator.ofInt(binding.hpBar, "progress", 0,100)
                animatorhp.duration = 800
                animatorhp.start()

                val animatoratt = ObjectAnimator.ofInt(binding.attackBar, "progress", 0,100)
                animatoratt.duration = 750
                animatoratt.start()

                val animatorDeff = ObjectAnimator.ofInt(binding.defenseBar, "progress", 0,100)
                animatorDeff.duration = 700
                animatorDeff.start()

                val animatorSatt = ObjectAnimator.ofInt(binding.sAttackBar, "progress", 0,100)
                animatorSatt.duration = 650
                animatorSatt.start()

                val animatorSdeff = ObjectAnimator.ofInt(binding.sDefenseBar, "progress", 0,100)
                animatorSdeff.duration = 600
                animatorSdeff.start()

                val animatorSp = ObjectAnimator.ofInt(binding.attackBar, "progress", 0,100)
                animatorSp.duration = 550
                animatorSp.start()

                binding.backBtn.setOnClickListener {
                    findNavController().navigateUp()
                }

                binding.txtPokemonDescription.text = it.description?.replace("\n", " ")

            }

        )

    }
}

