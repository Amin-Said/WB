package me.aminsaid.weather

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

fun ImageView.setWeatherImageState(code: Int) {
    this.setImageDrawable(
        AppCompatResources.getDrawable(
            this.context,
            WeatherType.fromWMO(code).iconRes
        )
    )
}