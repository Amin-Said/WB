package me.aminsaid.wb.presentation.weatherInfo

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import me.aminsaid.core.utils.getDayOfWeek
import me.aminsaid.wb.databinding.ItemForecastBinding
import me.aminsaid.weather.WeatherType
import me.aminsaid.weather.setWeatherImageState
import me.aminsaid.weatherforecast.domain.model.DailyForecast

class ForecastAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DailyForecast>() {

        override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
          return oldItem.Date == newItem.Date
        }

        override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
            return oldItem.hashCode() == newItem.hashCode()

        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ForecastViewHolder(
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<DailyForecast>) {
        differ.submitList(list)
    }

    class ForecastViewHolder
    constructor(
        private val binding: ItemForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: DailyForecast) = with(binding.root) {
            binding.weatherDateTv.text = item.Date?.getDayOfWeek()
            binding.weatherMaxTempTv.text =
                "${item.Temperature?.Maximum?.Value?.toInt()} ${item.Temperature?.Maximum?.Unit}"
            binding.weatherMinTempTv.text =
                "${item.Temperature?.Minimum?.Value?.toInt()} ${item.Temperature?.Minimum?.Unit}"
            binding.weatherStateTv.text = WeatherType.fromWMO(item.Day?.Icon ?:0).weatherDesc
            binding.weatherStateIv.setWeatherImageState(item.Day?.Icon ?:0)
        }
    }


}

