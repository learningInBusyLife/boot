package com.lick.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * 数字转换工具
 * 
 * @Author: lick
 * @Date: 2017年2月27日 下午5:22:01
 * @Copyright: 版权归 lick 所有
 */
public class NumberUtil {

	private static final BigDecimal TEN_THOUSAND = new BigDecimal(10000L); // 1万

	private static final BigDecimal ONE_HUNDRED_MILLION = new BigDecimal(100000000L); // 1亿

	public static String formatValue(long number) {
		BigDecimal decimal = new BigDecimal(number);
		if (decimal.compareTo(TEN_THOUSAND) < 0) {
			return String.valueOf(number);
		} else if (decimal.compareTo(ONE_HUNDRED_MILLION) < 0) {
			BigDecimal val = decimal.divide(TEN_THOUSAND);
			return formatByCeiling(val, 1) + "万";
		} else {
			BigDecimal val = decimal.divide(ONE_HUNDRED_MILLION);
			return formatByCeiling(val, 1) + "亿";
		}
	}

	/**
	 * 四舍五入法格式化数值，保留若干位小数
	 * 
	 * @param target
	 *            待格式化目标数值
	 * @param digits
	 *            保留位数
	 * @Author: lick
	 * @Date: 2017年2月27日 下午7:13:50
	 */
	public static String formatByHalfUp(BigDecimal target, int digits) {
		return format(target, digits, RoundingMode.HALF_UP);
	}

	/**
	 * 进一法格式化数值，保留若干位小数
	 * 
	 * @param target
	 *            待格式化目标数值
	 * @param digits
	 *            保留位数
	 * @Author: lick
	 * @Date: 2017年2月27日 下午7:13:50
	 */
	public static String formatByCeiling(BigDecimal target, int digits) {
		return format(target, digits, RoundingMode.CEILING);
	}

	/**
	 * 指定模式格式化数值，保留若干位小数
	 * 
	 * @param target
	 *            待格式化目标数值
	 * @param digits
	 *            保留位数
	 * @Author: lick
	 * @Date: 2017年2月27日 下午7:43:18
	 */
	public static String format(BigDecimal target, int digits, RoundingMode mode) {
		StringBuilder pattern = new StringBuilder("0.");
		for (int i = 0; i < digits; i++) {
			pattern.append("0");
		}
		DecimalFormat df = new DecimalFormat(pattern.toString());
		df.setRoundingMode(mode);
		return df.format(target);
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 *
	 * @param s 数字字符串值
	 * @return
	 * @Author: lick
	 * @Date: 2017/4/10 19:51
	 */
	public static String stripTrailingZeros(String s){
		if(s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");	//	去掉多余的0
			s = s.replaceAll("[.]$", "");	//	如最后一位是.则去掉
		}
		return s;
	}

	/**
	 * 判断是否是小数其含有几位小数
	 * @param obj 需要判断的数
	 * @param num  判断其含有的小数位数
	 * @return
	 */
	public static boolean judgeTwoDecimal(Object obj,int num){
		boolean flag = false;
		try {
			if(obj != null){
				String source = obj.toString();
				String regex = "^[+]?([0-9]+(.[0-9]{"+String.valueOf(num)+","+String.valueOf(num)+"})?)$";
				Pattern pattern = Pattern.compile(regex);
				if(pattern.matcher(source).matches()){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) {
		System.out.println(formatValue(19001L)); // 2.0万
		System.out.println(formatValue(10001L)); // 1.1万
		System.out.println(stripTrailingZeros("183.000"));
		System.out.println(judgeTwoDecimal(12.1,2));
	}

}
