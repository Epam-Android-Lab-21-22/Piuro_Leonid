package com.lealpy.socialnetworkui.presentation.favorite_songs

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SongItemDecoration(
    private val mDivider : Drawable,
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        drawDividers(canvas, parent)
    }

    private fun drawDividers(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        for (i in 0 until parent.childCount) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as? RecyclerView.LayoutParams
            if(params != null) {
                val top: Int = child.bottom + params.bottomMargin
                val bottom = top + mDivider.intrinsicHeight
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(canvas)
            }
        }
    }

}
