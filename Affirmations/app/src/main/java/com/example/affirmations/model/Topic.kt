package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @param:StringRes val nameRes: Int,
    val courseCount: Int,
    @param:DrawableRes val imageRes: Int
)