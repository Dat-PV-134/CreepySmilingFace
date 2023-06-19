package com.example.studycustomview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class CustomViewGroup(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {
    private var mLeftWidth = 0
    private var mRightWidth = 0

    private val mTmpContainerRect: Rect = Rect()
    private val mTmpChildRect: Rect = Rect()

    override fun shouldDelayChildPressedState(): Boolean {
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val count = childCount

        mLeftWidth = 0
        mRightWidth = 0

        var maxHeight = 0
        var maxWidth = 0
        var childState = 0

        (0..count).forEach { i ->
            val child: View = getChildAt(i)
            if (child.visibility != GONE) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
            }

            val lp: LayoutParams = child.layoutParams as LayoutParams
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }


}