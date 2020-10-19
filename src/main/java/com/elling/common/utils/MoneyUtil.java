package com.elling.common.utils;

import java.text.DecimalFormat;

public class MoneyUtil {
	
	private final static String[] STR_NUMBER = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	
	private final static String[] STR_UNIT = {"","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟"};//整数部分
	
	private final static String[] STR_UNIT2 = {"角","分","厘"};//小数部分
	
	/**
	 * get the Integer part
	 * @param num
	 * @return
	 */
	public static String getInteger(String num) {
		if(num.indexOf(".")!=-1) {
			num = num.substring(0,num.indexOf("."));
		}
		if(num.indexOf("-")!=-1) {
			num = num.substring(num.indexOf("-")+1);
		}
		num = new StringBuffer(num).reverse().toString();
		StringBuffer temp = new StringBuffer();
		for(int i=0;i<num.length();i++) {
			temp.append(STR_UNIT[i]);
			temp.append(STR_NUMBER[num.charAt(i)-48]);
		}
		
		num = temp.reverse().toString();
		num = numReplace(num,"零拾","零");
		num = numReplace(num,"零佰","零");
		num = numReplace(num,"零仟","零");
		num = numReplace(num,"零万","万");
		num = numReplace(num,"零亿","亿");
		num = numReplace(num,"零零","零");
		num = numReplace(num,"亿万","亿");
		
		if(num.lastIndexOf("零") == num.length() -1) {
			num = num.substring(0,num.length() -1);
		}
		
		return num;
	}
	
	/**
	 * get the Decimal part
	 * @param num
	 * @return
	 */
	public static String getDecimal(String num) {
		if(num.indexOf(".")==-1) {
			return "";
		}
		num = num.substring(num.indexOf(".")+1);
	
		num = new StringBuffer(num).reverse().toString();
		StringBuffer temp = new StringBuffer();
		
		for(int i=0;i<num.length();i++) {
			temp.append(STR_UNIT2[i]);
			temp.append(STR_NUMBER[num.charAt(i)-48]);
		}
		
		num = temp.reverse().toString();
		num = numReplace(num,"零角","零");
		num = numReplace(num,"零分","零");
		num = numReplace(num,"零厘","零");
		num = numReplace(num,"零零","零");
		
		
		if(num.lastIndexOf("零") == num.length() -1) {
			num = num.substring(0,num.length() -1);
		}
		
		return num;
	}
	/**
	 * replace the num in a string
	 * @param num
	 * @param oldNum
	 * @param newNum
	 * @return
	 */
	public static String numReplace(String num,String oldNum,String newNum) {
		while(true) {
			if(num.indexOf(oldNum) == -1) {
				break;
			}
			num = num.replaceAll(oldNum, newNum);
		}
		
		return num;
	}
	
	/**
	 * money change
	 * @param d
	 * @return
	 */
	public static String convert(double d) {
		DecimalFormat df = new DecimalFormat("#0.##");
		String strNum = df.format(d);
		if(strNum.indexOf(".")!=-1) {
			String num = strNum.substring(0,strNum.indexOf("."));
			if(num.length()>12) {
				System.out.println("数字太大，不能完成转换");
				return "";
			}
		}
		
		String point = "";
		if(strNum.indexOf(".")!=-1) {
			point = "元";
		}else {
			point = "元整";
		}
		
		
		String result = "";
		if(strNum.indexOf("-")!=-1) {
			result = "负";
		}
		result += getInteger(strNum) + point + getDecimal(strNum);
		if(result.startsWith("元")) {
			result = result.substring(1,result.length());
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		
		Double d = -7231.23d;
		String convert = convert(d);
		System.out.println("value:"+convert);
	}
	
}
