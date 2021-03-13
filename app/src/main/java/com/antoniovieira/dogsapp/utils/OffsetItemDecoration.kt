package com.antoniovieira.dogsapp.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OffsetItemDecoration(
    private val context: Context,
    @DimenRes private val offset: Int
) : RecyclerView.ItemDecoration() {

    private val offsetValue by lazy {
        context.resources.getDimensionPixelOffset(offset)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapterCount = parent.adapter?.itemCount ?: return
        val itemPosition = parent.getChildAdapterPosition(view)

        val layoutManager = parent.layoutManager
        if (layoutManager is LinearLayoutManager) {
            if (itemPosition >= adapterCount) return

            outRect.bottom = offsetValue
        }
    }

}