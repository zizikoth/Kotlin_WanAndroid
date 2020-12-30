package com.base.widget.titleview

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.view.ViewCompat
import com.base.widget.R
import com.base.widget.databinding.LayoutTitleViewBinding
import com.frame.core.utils.extra.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 2:18 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: LayoutTitleViewBinding

    /*** 标题 ***/
    private var titleText: String = if (isInEditMode) "标题" else ""
    private var titleSize: Float = if (isInEditMode) 50f else dimen(R.dimen.sp18)
    private var titleColor: Int = if (isInEditMode) Color.BLACK else color(R.color.color_333333)
    private var titleBold: Boolean = true
    private var titleMarqueeEnable: Boolean = false

    /*** 副标题 ***/
    private var subtitleText: String =  ""
    private var subtitleSize: Float = if (isInEditMode) 30f else dimen(R.dimen.sp13)
    private var subtitleColor: Int = if (isInEditMode) Color.BLACK else color(R.color.color_333333)
    private var subtitleBold: Boolean = false

    /*** 左侧图标 ***/
    private var leftText: String = ""
    private var leftTextSize: Float = if (isInEditMode) 40f else dimen(R.dimen.sp14)
    private var leftTextColor: Int = if (isInEditMode) Color.BLACK else color(R.color.color_333333)
    private var leftTextBold: Boolean = false
    private var leftDrawablePadding: Float = if (isInEditMode) 13f else dimen(R.dimen.dp5)
    private var leftDrawable: Int = R.drawable.ic_back
    private var leftShown: Boolean = true

    /*** 右侧图标 ***/
    private var rightText: String =  ""
    private var rightTextSize: Float = if (isInEditMode) 40f else dimen(R.dimen.sp14)
    private var rightTextColor: Int = if (isInEditMode) Color.BLACK else color(R.color.color_333333)
    private var rightTextBold: Boolean = false
    private var rightDrawable: Int = 0
    private var rightDrawablePadding: Float = if (isInEditMode) 13f else dimen(R.dimen.dp5)


    private var mElevation: Float = if (isInEditMode) 10f else dimen(R.dimen.dp2)


    /*** 底部阴影 ***/
    private var shadowShown: Boolean = true

    /*** 背景颜色 ***/
    private var background: Int = Color.WHITE

    private var mLeftListener: LeftClickListener? = null
    private var mRightListener: RightClickListener? = null
    private var mTitleListener: TitleClickListener? = null

    init {
        val attr: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
        initOption(attr)
        initView()
        initListener()
        attr.recycle()
    }

    private fun initOption(attr: TypedArray) {
        titleText = attr.getString(R.styleable.TitleView_title_title_text) ?: titleText
        titleSize = attr.getDimension(R.styleable.TitleView_title_title_size, titleSize)
        titleColor = attr.getColor(R.styleable.TitleView_title_title_color, titleColor)
        titleBold = attr.getBoolean(R.styleable.TitleView_title_title_bold, titleBold)
        titleMarqueeEnable =
            attr.getBoolean(R.styleable.TitleView_title_marquee_enable, titleMarqueeEnable)

        subtitleText = attr.getString(R.styleable.TitleView_title_subtitle_text) ?: subtitleText
        subtitleSize = attr.getDimension(R.styleable.TitleView_title_subtitle_size, subtitleSize)
        subtitleColor = attr.getColor(R.styleable.TitleView_title_subtitle_color, subtitleColor)
        subtitleBold = attr.getBoolean(R.styleable.TitleView_title_subtitle_bold, subtitleBold)

        leftText = attr.getString(R.styleable.TitleView_title_left_text) ?: leftText
        leftTextSize = attr.getDimension(R.styleable.TitleView_title_left_text_size, leftTextSize)
        leftTextColor = attr.getColor(R.styleable.TitleView_title_left_text_color, leftTextColor)
        leftTextBold = attr.getBoolean(R.styleable.TitleView_title_left_text_bold, leftTextBold)
        leftDrawablePadding = attr.getDimension(
            R.styleable.TitleView_title_left_drawable_padding,
            leftDrawablePadding
        )
        leftDrawable = attr.getResourceId(R.styleable.TitleView_title_left_drawable, leftDrawable)
        leftShown = attr.getBoolean(R.styleable.TitleView_title_left_shown, leftShown)

        rightText = attr.getString(R.styleable.TitleView_title_right_text) ?: rightText
        rightTextSize =
            attr.getDimension(R.styleable.TitleView_title_right_text_size, rightTextSize)
        rightTextColor = attr.getColor(R.styleable.TitleView_title_right_text_color, rightTextColor)
        rightTextBold = attr.getBoolean(R.styleable.TitleView_title_right_text_bold, rightTextBold)
        rightDrawable =
            attr.getResourceId(R.styleable.TitleView_title_right_drawable, rightDrawable)
        rightDrawablePadding = attr.getDimension(
            R.styleable.TitleView_title_right_drawable_padding,
            rightDrawablePadding
        )

        shadowShown = attr.getBoolean(R.styleable.TitleView_title_shadow_shown, shadowShown)

        background = attr.getColor(R.styleable.TitleView_title_background, Color.WHITE)
    }

    private fun initView() {
        binding = bindView(R.layout.layout_title_view)
        binding.apply {
            // 标题
            mTvTitle.text = titleText
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize)
            mTvTitle.setTextColor(titleColor)
            if (titleBold) {
                mTvTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }
            if (titleMarqueeEnable) {
                mTvTitle.ellipsize = TextUtils.TruncateAt.MARQUEE
                mTvTitle.setSingleLine()
                mTvTitle.isSelected = true
                mTvTitle.isFocusable = true
                mTvTitle.isFocusableInTouchMode = true
            }
            // 副标题
            if (subtitleText.isNotEmpty()) {
                mTvSubTitle.visible()
                mTvSubTitle.text = subtitleText
                mTvSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, subtitleSize)
                mTvSubTitle.setTextColor(subtitleColor)
                if (subtitleBold) {
                    mTvSubTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                }
            }
            // 左侧图标
            if (leftShown) {
                mTvLeft.visibility = View.VISIBLE
                //左侧图标
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    mTvLeft.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, 0, 0, 0)
                } else {
                    mTvLeft.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, 0, 0, 0)
                }
                mTvRight.compoundDrawablePadding = leftDrawablePadding.toInt()
                //左侧文字
                if (leftText.isNotEmpty()) {
                    mTvLeft.text = leftText
                    mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize)
                    mTvLeft.setTextColor(leftTextColor)
                    if (leftTextBold) {
                        mTvLeft.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                }
            } else {
                mTvLeft.visibility = View.GONE
            }
            // 右侧文字
            if (rightText.isNotEmpty()) {
                mTvRight.visibility = View.VISIBLE
                mTvRight.text = rightText
                mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize)
                mTvRight.setTextColor(rightTextColor)
                if (rightTextBold) {
                    mTvRight.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                }
            }
            if (rightDrawable != 0) {
                mTvRight.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    mTvRight.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, rightDrawable, 0)
                } else {
                    mTvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightDrawable, 0)
                }
                mTvRight.compoundDrawablePadding = rightDrawablePadding.toInt()
            }
            //设置背景颜色
            setBackgroundColor(background)
            //是否显示边框阴影
            if (shadowShown && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this@TitleView.outlineProvider = ViewOutlineProvider.BOUNDS
                ViewCompat.setElevation(this@TitleView, mElevation)
                clipToPadding = false
            }
        }
    }

    private fun initListener() {
        binding.apply {
            mTvLeft.onClick {
                if (mLeftListener == null) {
                    if (context is Activity) {
                        (context as Activity).finish()
                    }
                } else {
                    mLeftListener?.onLeftClick()
                }
            }
            mTvTitle.onClick {
                mTitleListener?.onTitleClick()
            }
            mTvRight.onClick {
                mRightListener?.onRightClick()
            }
        }
    }

    /**
     * 设置标题
     * @param title String? 标题
     */
    fun setTitle(title: String?) {
        title.let {
            binding.mTvTitle.text = it
        }

    }

    /**
     * 设置标题字体大小
     * @param dimenSp Int R.dimen.sp10
     */
    fun setTitleSize(@DimenRes dimenSp: Int) {
        binding.mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimen(dimenSp))
    }

    /**
     * 设置标题字体颜色
     * @param colorInt Int Color.White
     */
    fun setTitleColor(@ColorInt colorInt: Int) {
        binding.mTvTitle.setTextColor(colorInt)
    }

    /**
     * 是否开启标题跑马灯
     * @param enable Boolean
     */
    fun enableTitleMarquee(enable: Boolean) {
        binding.apply {
            mTvTitle.ellipsize = TextUtils.TruncateAt.MARQUEE
            mTvTitle.setSingleLine()
            mTvTitle.isSelected = enable
            mTvTitle.isFocusable = enable
            mTvTitle.isFocusableInTouchMode = enable
        }
    }

    /**
     * 是否允许标题粗体
     * @param enable Boolean
     */
    fun enableTitleBold(enable: Boolean) {
        binding.mTvRight.typeface = if (enable) {
            Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    /**
     * 设置副标题
     * @param subTitle String? 副标题
     */
    fun setSubTitle(subTitle: String?) {
        subTitle.let {
            binding.mTvSubTitle.visible()
            binding.mTvSubTitle.text = it
        }
    }

    /**
     * 副标题字体大小
     * @param dimenSp Int R.dimen.sp10
     */
    fun setSubTitleSize(@DimenRes dimenSp: Int) {
        binding.mTvSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimen(dimenSp))
    }

    /**
     * 设置副标题颜色
     * @param colorInt Int Color.white
     */
    fun setSubTitleColor(@ColorInt colorInt: Int) {
        binding.mTvSubTitle.setTextColor(colorInt)
    }

    /**
     * 是否允许副标题粗体
     * @param enable Boolean
     */
    fun enableSubTitleBold(enable: Boolean) {
        binding.mTvSubTitle.typeface = if (enable) {
            Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    /**
     * 左侧图标
     * @param drawableRes Int
     */
    fun setLeftDrawable(@DrawableRes drawableRes: Int) {
        binding.apply {
            mTvLeft.visible()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mTvLeft.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableRes, 0, 0, 0)
            } else {
                mTvLeft.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0)
            }
        }
    }

    /**
     * 是否显示左侧图标
     * @param shown Boolean
     */
    fun showLeft(shown: Boolean) {
        binding.mTvLeft.setVisible(shown)
    }

    /**
     * 设置右侧图标
     * @param drawableRes Int
     */
    fun setRightDrawable(@DrawableRes drawableRes: Int) {
        binding.apply {
            mTvRight.visible()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mTvRight.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableRes, 0)
            } else {
                mTvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableRes, 0)
            }
            mTvRight.compoundDrawablePadding = rightDrawablePadding.toInt()
        }
    }

    /**
     * 设置右侧文字
     * @param moreText String? 文字
     */
    fun setRightText(moreText: String?) {
        if (!moreText.isNullOrEmpty()) {
            binding.mTvRight.visible()
            binding.mTvRight.text = moreText
        }
    }

    /**
     * 设置右侧文字大小
     * @param dimenSp Int R.dimen.sp10
     */
    fun setRightTextSize(@DimenRes dimenSp: Int) {
        binding.mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimen(dimenSp))
    }

    /**
     * 设置右侧文字颜色
     * @param colorInt Int Color.white
     */
    fun setRightTextColor(@ColorInt colorInt: Int) {
        binding.mTvRight.setTextColor(colorInt)
    }

    /**
     * 右侧文字粗体
     * @param enable Boolean
     */
    fun enableRightTextBold(enable: Boolean) {
        binding.mTvRight.typeface = if (enable) {
            Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    /**
     * 设置右侧文字和图标的间隔
     * @param padding Int
     */
    fun setRightDrawablePadding(padding: Int) {
        binding.mTvRight.compoundDrawablePadding = padding
    }

    fun getLeftView() = binding.mTvLeft

    fun getRightView() = binding.mTvRight

    /**
     * 设置左侧点击
     */
    fun setOnLeftClickListener(onClick: () -> Unit) {
        mLeftListener = object : LeftClickListener {
            override fun onLeftClick() {
                onClick()
            }
        }
    }

    /**
     * 设置右侧点击
     */
    fun setOnRightClickListener(onClick: () -> Unit) {
        mRightListener = object : RightClickListener {
            override fun onRightClick() {
                onClick()
            }
        }
    }

    /**
     * 设置标题点击
     */
    fun setOnTitleClickListener(onClick: () -> Unit) {
        mTitleListener = object : TitleClickListener {
            override fun onTitleClick() {
                onClick()
            }
        }
    }

    /**
     * 左侧点击
     */
    private interface LeftClickListener {
        fun onLeftClick()
    }

    /**
     * 标题点击
     */
    private interface RightClickListener {
        fun onRightClick()
    }

    /**
     * 右侧点击
     */
    private interface TitleClickListener {
        fun onTitleClick()
    }


}