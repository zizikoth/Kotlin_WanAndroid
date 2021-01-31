package com.base.base.utils

import com.blankj.utilcode.util.ToastUtils
import com.kongzue.dialogx.dialogs.TipDialog

fun toast(message: Any) = ToastUtils.showShort(message.toString())

fun tip(message: Any) = TipDialog.show(message.toString())
