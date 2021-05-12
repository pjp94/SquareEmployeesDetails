package com.pancholi.squareemployees.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pancholi.squareemployees.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

  companion object {
    const val EXTRA_IMAGE = "ExtraImage"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_image)
    setImage()
  }

  private fun setImage() {
    val url = intent.getStringExtra(EXTRA_IMAGE)

    Picasso.get()
      .load(Uri.parse(url))
      .into(fullImage)
  }
}