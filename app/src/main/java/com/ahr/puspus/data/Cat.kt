package com.ahr.puspus.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    @DrawableRes val photo: Int,
    val name: String,
    val description: String
) : Parcelable
