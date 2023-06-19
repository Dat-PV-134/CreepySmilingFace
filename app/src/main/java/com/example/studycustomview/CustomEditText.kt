package com.example.studycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet

class CustomEditText(context: Context?, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatEditText(context!!, attrs) {
    private val sqrtSymbol = "√"
    private val sqrtSymbolLength = sqrtSymbol.length

    private val sqrtSymbolPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        // Thiết lập thuộc tính của văn bản căn bản
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize *= 1.5f // Cỡ chữ căn lớn hơn cỡ chữ mặc định
    }

    init {
        // Đặt nghe sự kiện TextWatcher để theo dõi và xử lý thay đổi văn bản
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                formatText()
            }
        })
    }

    private fun formatText() {
        // Xử lý văn bản để thêm hoặc loại bỏ biểu tượng căn
        val text = text?.toString()?.trim()
        val formattedText = if (text?.startsWith(sqrtSymbol) == true) {
            text.substring(sqrtSymbolLength) // Loại bỏ biểu tượng căn
        } else {
            "$sqrtSymbol$text" // Thêm biểu tượng căn
        }
        setText(formattedText)
        setSelection(formattedText.length) // Đặt con trỏ ở cuối văn bản
    }

    private fun hasSqrtSymbol(): Boolean {
        val text = text?.toString()?.trim()
        return text?.startsWith(sqrtSymbol) == true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Vẽ biểu tượng căn vào văn bản nếu thỏa mãn điều kiện
        if (hasSqrtSymbol()) {
            val xPos = sqrtSymbolPaint.measureText(sqrtSymbol) / 2
            val yPos = (height / 2 - (sqrtSymbolPaint.descent() + sqrtSymbolPaint.ascent()) / 2)
            canvas?.drawText(sqrtSymbol, xPos, yPos, sqrtSymbolPaint)
        }
    }
}