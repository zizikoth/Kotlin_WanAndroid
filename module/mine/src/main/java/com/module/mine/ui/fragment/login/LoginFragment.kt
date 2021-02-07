package com.module.mine.ui.fragment.login

import com.base.base.ui.BaseFragment
import com.base.base.utils.toast
import com.frame.core.utils.extra.*
import com.module.mine.R
import com.module.mine.databinding.FragmentLoginBinding
import com.module.mine.ui.activity.login.LoginActivity

/**
 * title:登陆
 * describe:
 *
 * @author memo
 * @date 2021-02-06 5:54 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_login

    /*** 初始化 ***/
    override fun initialize() {
        mBinding.run {
            mEtAccount.setText("Mr.Memo")
            mEtPwd.setText("zhx931024")
            mTvSignIn.round(color(R.color.color_shape), 25.dp2px)
            // 登陆
            mTvSignIn.onClick {
                if (mEtAccount.value.isEmpty()) {
                    toast("请输入账号")
                } else if (mEtPwd.value.isEmpty()) {
                    toast("请输入密码")
                } else {
                    (mContext as LoginActivity).login(mEtAccount.value, mEtPwd.value)
                }
            }
        }
    }
}