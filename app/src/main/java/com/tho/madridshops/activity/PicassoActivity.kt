package com.tho.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.tho.madridshops.R
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Picasso")

        // Options to display images
        Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this).isLoggingEnabled = true

        Picasso.with(this)
                .load("https://i.pinimg.com/originals/50/54/3a/50543adfc79f3209893aa528d35142ba.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img1)

        Picasso.with(this)
                .load("http://i0.kym-cdn.com/photos/images/newsfeed/000/716/080/f27.jpg")
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(img2)

        Picasso.with(this)
                .load("https://pics.me.me/wtf-is-robert-downey-jrs-dog-doing-what-is-going-14557456.png")
                .placeholder(android.R.drawable.ic_input_add)
                .into(img3)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Sabemos que se ha pulsado la flecha de back
                finish()
                return true
            }
            else -> return true
        }
    }

}
