package com.seosh817.animationcollection

import androidx.annotation.StringRes
import androidx.navigation.NavDirections

data class NavItem(
    @StringRes
    val textResId: Int,
    val direction: NavDirections
)