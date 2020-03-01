package com.gahee.myprography

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gahee.myprography.FilmsAdapter.Companion.FILM_DATA_KEY
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val film = intent.getParcelableExtra<Film>(FILM_DATA_KEY)

        tv_detail_film_title.text = film.title
        tv_detail_film_rate.text = film.rate
        tv_detail_film_director.text = film.director
        tv_detail_film_producer.text = film.producer
        tv_detail_film_description.text = film.description
        tv_detail_film_release_date.text = film.releaseDate

        //set up rating bar
        tv_detail_film_rate.text = getString(R.string.film_score, film.rate)
        detail_rating_bar.rating = film.rate.toFloat()/10

    }


}
