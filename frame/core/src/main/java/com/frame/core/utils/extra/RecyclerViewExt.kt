package com.frame.core.utils.extra

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

var RecyclerView.supportsChangeAnimations: Boolean
    get() = (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations
    set(value) {
        (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = value
    }