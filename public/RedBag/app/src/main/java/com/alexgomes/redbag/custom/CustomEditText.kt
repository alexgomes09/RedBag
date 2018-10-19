package com.alexgomes.redbag.custom

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.text.InputFilter
import android.text.InputType
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alexgomes.redbag.R
import com.alexgomes.redbag.dp
import kotlinx.android.synthetic.main.partial_edit_text_with_label.view.*


/**
 * Created by agomes on 9/12/18.
 */
class CustomEditText : LinearLayout {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.partial_edit_text_with_label, this, true)

        orientation = LinearLayout.VERTICAL

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)

        val fontName = typedArray.getString(R.styleable.CustomTextView_custom_font)
        val typeface = if (fontName != null) {
            FontManager.getFont(context, fontName)
        } else {
            FontManager.getFont(context, context.getString(R.string.font_regular))
        }

        //set type face to edit text and label
        edit_text.typeface = typeface

        //Label
        val label = typedArray.getString(R.styleable.CustomEditText_label)
        if (TextUtils.isEmpty(label)) {
            tv_label.visibility = GONE
        } else {
            tv_label.text = label
        }

        //sets the hint of edit text
        val hint = typedArray.getString(R.styleable.CustomEditText_hint)
        edit_text.hint = hint

        //input type for edittext
        val inputTypeString = typedArray.getString(R.styleable.CustomEditText_inputType)
        if (inputTypeString != null) {
            val inputType: Int
            when (inputTypeString) {
                "text" -> inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                "email" -> inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                "number" -> inputType = InputType.TYPE_CLASS_NUMBER
                "decimal" -> inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                "textMultiLine" -> inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
                "textPassword" -> {
                    inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                    edit_text.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                "numberPassword" -> inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD or InputType.TYPE_CLASS_NUMBER
                "phone" -> inputType = InputType.TYPE_CLASS_PHONE
                else -> inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            }

            if (inputType == InputType.TYPE_TEXT_FLAG_MULTI_LINE) {
                edit_text.setRawInputType(inputType)
            } else {
                edit_text.inputType = inputType
            }
        } else {
            edit_text.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        }

        //setting max lengh
        val maxLength = typedArray.getInt(R.styleable.CustomEditText_maxLength, 0)
        if (maxLength > 0) {
            setMaxLength(maxLength)
        }

        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val params = layoutParams as ViewGroup.MarginLayoutParams

        params.topMargin = 10.dp
        params.bottomMargin = 10.dp
    }

    fun setText(text: CharSequence) {
        edit_text.setText(text)
    }

    fun setError(error: CharSequence) {
        edit_text.error = error
    }

    fun getText(): String {
        return edit_text.text.toString()
    }

    fun setMaxLength(length: Int) {
        val FilterArray = arrayOfNulls<InputFilter>(1)
        FilterArray[0] = InputFilter.LengthFilter(length)
        edit_text.filters = FilterArray
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putString("text", edit_text.text.toString())
        super.onSaveInstanceState()
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            val bundle = state
            setText(bundle.getString("text"))
        }
        super.onRestoreInstanceState(state)
    }

}
