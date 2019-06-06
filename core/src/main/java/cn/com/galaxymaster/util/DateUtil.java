package cn.com.galaxymaster.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Date操作工具类
 * 
 * @author yxxcrtd@gmail.com
 */
public class DateUtil extends BaseUtil {

    /**
     * 显示当前时间
     * @return 时间格式为：yyyy-mm-dd hh:mm:ss
     */
	public static final String getNow() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
		return ldt.format(dft);
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return 返回规定格式的数据，如：20140710170735665
	 */
	public static final String getCurrentTime() {
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", System.currentTimeMillis());
	}
	
	/**
	 * 获取系统当前时间
	 * 
	 * @return 返回短时间格式，如：2014-07-10
	 */
	public static final Date getCurruentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(date);
		ParsePosition pp = new ParsePosition(0);
		return sdf.parse(dateString, pp);
	}

	/**
	 * Main Method Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getCurrentTime());
		System.out.println();
		System.out.println(getCurruentDate());
	}

}
