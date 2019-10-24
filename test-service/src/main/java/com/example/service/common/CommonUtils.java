package com.example.service.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CommonUtils {

	/**随机静态变量**/
	private static final Random random=new Random();
	
	/**格式化日期静态变量**/
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 用做随机获取随机码的数组
	 */
	private static final String strDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 返回随机num位数
	 * @param num
	 * @return
	 */
	public static String getRandomCodeString(int num)
	{
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<num;i++)
		{
			sb.append(strDigits[getRandomInteger(0, strDigits.length)]);
		}
		return sb.toString();
	}
	

	/**
	 * 取以yyyy-MM-dd HH:mm:ss格式格式化的当前时间字符串
	 * @return
	 */
	public static String getFormatDate()
	{
		return formatter.format(new Date());
	}
	
	/**
	 * 根据Date取以yyyy-MM-dd HH:mm:ss格式格式化的 字符串
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date)
	{
		return formatter.format(date);
	}
	
	/**
	 * 取当天零点的时间戳
	 * @return
	 */
	public static long getTodayZero()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis();
	}
	
	/**
	 * 取当前时间的时间戳
	 * @return
	 */
	public static long getcurrentTimeMillis()
	{
		return System.currentTimeMillis();
	}
	
	/**
	 * 根据时间戳来取Date对象
	 * @param millis
	 * @return
	 */
	public static Date getDateByMillis(long millis)
	{
		return new Date(millis);
	}
	
	/**
	 * 根据时间戳取以yyyy-MM-dd HH:mm:ss格式格式化的 字符串
	 * @param millis
	 * @return
	 */
	public static String getFormatDateByMillis(long millis)
	{
		return getFormatDate(getDateByMillis(millis));
	}
	
	/**
	 * 在min和max中随机取count个int类型的数,
	 * 如果min和max中没有count个数或者max小于min就返回一个长度为0的数组
	 * @param min 最小值
	 * @param max 最大值
	 * @param count 个数
	 * @param isCoverMax 是否包含最大值
	 * @return
	 */
	public static int[] getRandomIntegerArray(int min,int max,int count,boolean isCoverMax)
	{	
		int [] result;
		
		//两数的差,往后作为能随机的List的个数
		//速度比list.size()这个函数速度更快
		int difference=max-min;
		
		if(difference<count)//容错
		{
			return new int[0];
		}
		
		result=new int[count];
		
		List<Integer> list=new ArrayList<Integer>();
		
		//添加到list
		for (int i = min; i < max; i++) 
		{
			list.add(i);
		}
		
		int randomInteger;
		
		if(isCoverMax)
		{
			//包含最大值
			for (int i = 0; i < count; i++) 
			{
				randomInteger=getRandomIntegerCoverMax(0, difference);
				result[i] = list.get(randomInteger);
				list.remove(randomInteger);
				difference--;
			}
		}
		else
		{
			//不包含最大值
			for (int i = 0; i < count; i++) 
			{
				randomInteger=getRandomInteger(0, difference);
				result[i] = list.get(randomInteger);
				list.remove(randomInteger);
				difference--;
			}
		}
		
		return result;
	}
	
	/**
	 * 在min和max中随机取一个数,包括min但不包括max
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInteger(int min,int max)
	{
		return random.nextInt(max-min)+min;
	}
	
	/**
	 * 在min和max中随机取一个数，包括min和max
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomIntegerCoverMax(int min,int max)
	{
		return random.nextInt(max-min+1)+min;
	}
	
	/**
	 * 判断Integer类型的key是否在Integer类型的数组a里面
	 * @param a
	 * @param key
	 * @return
	 */
	public static boolean inArray(int[] a,int key)
	{
		if(Arrays.binarySearch(a,key)>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 判断String类型的key是否在String类型的数组a里面
	 * @param a
	 * @param key
	 * @return
	 */
	public static boolean inArray(String[] a,String key)
	{
		if(Arrays.binarySearch(a,key)>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
