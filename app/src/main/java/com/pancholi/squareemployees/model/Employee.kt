package com.pancholi.squareemployees.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee(
  @JsonProperty("uuid")
  val uuid: String,
  @JsonProperty("full_name")
  val fullName: String,
  @JsonProperty("phone_number")
  val phoneNumber: String?,
  @JsonProperty("email_address")
  val emailAddress: String,
  @JsonProperty("biography")
  val biography: String?,
  @JsonProperty("photo_url_small")
  val photoUrlSmall: String?,
  @JsonProperty("photo_url_large")
  val photoUrlLarge: String?,
  @JsonProperty("team")
  val team: String,
  @JsonProperty("employee_type")
  val type: Type
) : Parcelable {

  enum class Type(val value: String) {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    CONTRACTOR("Contractor")
  }

  fun getFormattedNumber(): String? {
    return PhoneNumberFormatter().getFormattedNumber(phoneNumber)
  }
}