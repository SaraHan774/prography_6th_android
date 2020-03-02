package com.gahee.myprography.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gahee.myprography.network.Film
import com.gahee.myprography.adapters.FilmsAdapter.Companion.FILM_DATA_KEY
import com.gahee.myprography.R
import kotlinx.android.synthetic.main.activity_detail.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val film = intent.getParcelableExtra<Film>(FILM_DATA_KEY)

        tv_detail_film_title.text = film.title
        tv_detail_film_rate.text = film.rate
        tv_detail_film_description.text = film.description

        tv_detail_film_director.append(film.director)
        tv_detail_film_producer.append(film.producer)
        tv_detail_film_release_date.append(film.releaseDate)

        //set up rating bar. 100점을 별 다섯 개로 표현하기 위해 20으로 나눈다.
        tv_detail_film_rate.text = getString(R.string.film_score, film.rate)
        detail_rating_bar.rating = film.rate.toFloat()/20


        val actionBar = supportActionBar
        actionBar?.apply{
            title = film.title
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
