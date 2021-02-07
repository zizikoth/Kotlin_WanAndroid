package com.module.mine.ui.activity.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.base.base.manager.BusViewModel
import com.base.base.manager.RouterManager
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.indicator.IndicatorType
import com.base.base.utils.indicator.init
import com.base.base.utils.indicator.setTitles
import com.frame.core.utils.extra.enableOverScrollMode
import com.module.mine.R
import com.module.mine.databinding.ActivityLoginBinding
import com.module.mine.ui.adapter.LoginFragmentAdapter
import com.module.mine.viewmodel.LoginViewModel

/**
 * title:登录注册
 * describe:
 *
 * @author memo
 * @date 2021-02-06 16:03
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterManager.Mine.LOGIN_ACTIVITY)
class LoginActivity : BaseVmActivity<LoginViewModel, ActivityLoginBinding>() {

    private val titles = listOf("SIGN IN", "SIGN UP")

    private val mAdapter by lazy { LoginFragmentAdapter(this) }

    override fun transparentStatusBar(): Boolean = true

    override fun bindLayoutRes(): Int = R.layout.activity_login

    override fun initData() {}

    override fun initView() {
        showContent()
        mBinding.run {
            mIndicator.init(mViewPager, IndicatorType.RoundCover)
            mIndicator.setTitles(titles, IndicatorType.RoundCover)
            mViewPager.run {
                isUserInputEnabled = false
                enableOverScrollMode = false
                offscreenPageLimit = 2
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        // 登陆成功
        BusViewModel.get().loginLiveData.observeInActivity(this) { finish() }
    }

    override fun start() {}

    /**
     * 登陆
     * @param userName String 用户名
     * @param password String 密码
     */
    fun login(userName: String, password: String) {
        mViewModel.login(userName, password)
    }

    /**
     * 注册
     * @param userName String 用户名
     * @param password String 密码
     */
    fun register(userName: String, password: String) {
        mViewModel.register(userName, password)
    }

}