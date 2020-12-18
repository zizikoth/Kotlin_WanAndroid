package com.frame.core.utils

import android.os.Build
import com.blankj.utilcode.util.DeviceUtils
import com.frame.core.utils.extra.md5

/**
 * title:获取应用uuid
 * describe:
 *
 * @author memo
 * @date 2020-01-09 10:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object UUIDHelper {

    private var uuid: String = ""

    /**
     * 通过刷机是可以改变数据的
     * 恢复出厂设置AndroidId变化
     * 只要不换手机就不一样
     */
    fun getUUID(): String {
        if (uuid.isEmpty()) {
            uuid = StringBuilder()
                .append(Build.BOARD).append(Build.BRAND)
                .append(Build.DEVICE).append(Build.DISPLAY)
                .append(Build.FINGERPRINT)
                .append(Build.HARDWARE).append(Build.HOST)
                .append(Build.MANUFACTURER).append(Build.MODEL)
                .append(Build.PRODUCT).append(Build.TAGS)
                .append(Build.TYPE).append(Build.USER)
                .append(DeviceUtils.getAndroidID())
                .toString()
                .md5()
        }
        return uuid
    }


    /**
     * 应用只要不卸载那么这个值是保持唯一的
     */
    fun getUDID(): String {
        if (uuid.isEmpty()) {
            uuid = DeviceUtils.getUniqueDeviceId()
        }
        return uuid
    }
}