/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 汉字辅助类
 * @author HuHui
 * @version $Id: PinyinUtil.java, v 0.1 2018年4月16日 下午7:23:32 HuHui Exp $
 */
public class PinyinUtil {

    /**
     * 提取每个汉字的小写首字母
     * @param str 待提取字符串
     * @return
     */
    public static final String getPinyinHeadLowerChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toLowerCase();
    }

}
