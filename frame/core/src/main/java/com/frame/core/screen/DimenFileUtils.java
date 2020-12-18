package com.frame.core.screen;


import android.annotation.SuppressLint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;


/**
 * title:文件生成工具类
 * describe:
 *
 * @author zhou
 * @date 2019-01-05 11:10
 */
public class DimenFileUtils {

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n";
    private static final String XML_RESOURCE_START = "<resources>\r\n";
    private static final String XML_RESOURCE_END = "</resources>\r\n";
    private static final String XML_DIMEN_DP = "\t<dimen name=\"dp%2$d\">%3$.2fdp</dimen>\r\n";
    private static final String XML_DIMEN_DP_F = "\t<dimen name=\"dp%2$s\">%3$.2fdp</dimen>\r\n";
    private static final String XML_DIMEN_SP = "\t<dimen name=\"sp%2$d\">%3$.2fsp</dimen>\r\n";
    private static final String XML_DIMEN_SP_F = "\t<dimen name=\"sp%2$s\">%3$.2fsp</dimen>\r\n";


    private static final String XML_BASE_DPI = "\t<string name=\"base_dpi\">%ddp</string>\r\n";
    private static final int MAX_SIZE_DP = 750;
    private static final int MAX_SIZE_SP = 40;
    private static final float[] FLOAT_MORE = new float[]{0.5f, 2.5f, 7.5f, 12.5f};

    /**
     * 生成的文件名
     */
    private static final String XML_NAME = "dimens.xml";


    private static float px2dip(float pxValue, int sw, int designWidth) {
        float dpValue = (pxValue / (float) designWidth) * sw;
        BigDecimal bigDecimal = new BigDecimal(dpValue);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * 生成所有的尺寸数据
     */
    @SuppressLint("DefaultLocale")
    private static String makeAllDimens(DimenTypes type, int designWidth) {
        float dpValue;
        String temp;
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(XML_HEADER);
            sb.append(XML_RESOURCE_START);
            //备份生成的相关信息
            temp = String.format(XML_BASE_DPI, type.getSwWidthDp());
            sb.append(temp);
            //添加额外的一些小数
            for (float i : FLOAT_MORE) {
                sb.append(formatDPF(i, type, designWidth));
            }
            for (int i = 1; i <= MAX_SIZE_DP; i++) {
                dpValue = px2dip((float) i, type.getSwWidthDp(), designWidth);
                temp = String.format(XML_DIMEN_DP, "", i, dpValue);
                sb.append(temp);
            }
            //添加额外的一些小数
            for (float i : FLOAT_MORE) {
                sb.append(formatSPF(i, type, designWidth));
            }
            for (int i = 1; i <= MAX_SIZE_SP; i++) {
                dpValue = px2dip((float) i, type.getSwWidthDp(), designWidth);
                temp = String.format(XML_DIMEN_SP, "", i, dpValue);
                sb.append(temp);
            }
            sb.append(XML_RESOURCE_END);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @SuppressLint("DefaultLocale")
    public static String formatDPF(float dp, DimenTypes type, int designWidth) {
        float dpValue = px2dip(dp, type.getSwWidthDp(), designWidth);
        return String.format(XML_DIMEN_DP_F, "", (dp + "").replace(".", "_"), dpValue);
    }

    @SuppressLint("DefaultLocale")
    public static String formatSPF(float dp, DimenTypes type, int designWidth) {
        float dpValue = px2dip(dp, type.getSwWidthDp(), designWidth);
        return String.format(XML_DIMEN_SP_F, "", (dp + "").replace(".", "_"), dpValue);
    }


    /**
     * 生成的目标文件夹
     * 只需传宽进来就行
     *
     * @param type     枚举类型
     * @param buildDir 生成的目标文件夹
     */
    public static void makeAll(int designWidth, DimenTypes type, String buildDir) {
        try {
            //生成规则
            final String folderName;
            if (type.getSwWidthDp() > 0) {
                //适配Android 3.2+
                folderName = "values-sw" + type.getSwWidthDp() + "dp";
            } else {
                return;
            }

            //生成目标目录
            File file = new File(buildDir + File.separator + folderName);
            if (!file.exists()) {
                file.mkdirs();
            }

            //生成values文件
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + File.separator + XML_NAME);
            fos.write(makeAllDimens(type, designWidth).getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
