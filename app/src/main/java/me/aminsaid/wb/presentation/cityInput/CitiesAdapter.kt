package me.aminsaid.wb.presentation.cityInput

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.wb.databinding.ItemCityBinding

class CitiesAdapter:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onCityClicked: ((City) -> Unit)? = null

    private val diffCallback = object : DiffUtil.ItemCallback<City>() {

        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
          return oldItem.Key == newItem.Key
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
           return oldItem.hashCode() == newItem.hashCode()
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CitiesViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CitiesViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<City>) {
        differ.submitList(list)
    }

    inner class CitiesViewHolder
    constructor(
        private val binding: ItemCityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: City) = with(binding.root) {
            binding.root.setOnClickListener {
                onCityClicked?.invoke(item)

            }

            binding.cityNameTv.text = "${item.LocalizedName} - ${item.Country?.LocalizedName}"

        }
    }

}

