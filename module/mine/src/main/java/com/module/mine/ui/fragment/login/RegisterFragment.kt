package com.module.mine.ui.fragment.login

import com.base.base.ui.BaseFragment
import com.base.base.utils.toast
import com.blankj.utilcode.util.StringUtils
import com.frame.core.utils.extra.*
import com.module.mine.R
import com.module.mine.databinding.FragmentRegisterBinding
import com.module.mine.ui.activity.login.LoginActivity

/**
 * title: 注册
 * describe:
 *
 * @author memo
 * @date 2021-02-06 5:54 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_register

    /*** 初始化 ***/
    override fun initialize() {
        mBinding.run {

            // 注册
            mTvSignUp.onClick {
                val userName = mEtAccount.value
                val pwd = mEtPwd.value
                val rePwd = mEtRePwd.value
                if (userName.isEmpty()) {
                    toast("请输入账号")
                } else if (pwd.isEmpty()) {
                    toast("请输入密码")
                } else if (rePwd.isEmpty()) {
                    toast("请再次输入密码")
                } else if (!StringUtils.equals(pwd, rePwd)) {
                    toast("两次输入的密码不一致")
                } else {
                    (mContext as LoginActivity).register(userName, pwd)
                }
            }
        }
    }
}