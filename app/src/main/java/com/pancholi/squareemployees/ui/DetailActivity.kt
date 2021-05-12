package com.pancholi.squareemployees.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pancholi.squareemployees.R
import com.pancholi.squareemployees.model.Employee
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception

class DetailActivity : AppCompatActivity() {

  companion object {

    const val EXTRA_EMPLOYEE = "ExtraEmployee"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)
    setViews()
  }

  private fun setViews() {
    val employee = intent.getParcelableExtra<Employee>(EXTRA_EMPLOYEE)

    setImage(employee.photoUrlLarge)
    detailName.text = employee.fullName
    detailEmail.text = employee.emailAddress
    detailNumber.text = employee.phoneNumber
    detailTeam.text = employee.team
    detailType.text = employee.type.value
    detailBio.text = employee.biography
  }

  private fun setImage(url: String?) {
    Picasso.get()
      .load(Uri.parse(url))
      .into(detailImage, object: Callback {
        override fun onSuccess() {
          detailLoading.visibility = View.GONE
          detailImage.visibility = View.VISIBLE
        }

        override fun onError(e: Exception?) {
          detailLoading.visibility = View.GONE
        }
      })

    detailImage.setOnClickListener {
      val intent = Intent(this@DetailActivity, ImageActivity::class.java)
      intent.putExtra(ImageActivity.EXTRA_IMAGE, url)
      startActivity(intent)
    }
  }
}