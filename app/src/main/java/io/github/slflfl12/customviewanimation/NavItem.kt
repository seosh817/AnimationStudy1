package io.github.slflfl12.customviewanimation

import androidx.annotation.StringRes
import androidx.navigation.NavDirections

data class NavItem(
    @StringRes
    val textResId: Int,
    val direction: NavDirections
)