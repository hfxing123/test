package com.example.demo.base;

import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceDo {
	
	private static ServiceDo instance;
	
	public static ServiceDo getInstance()
	{
		if(instance==null)
		{
			instance=new ServiceDo();
		}
		return instance;
	}
	
	/**
	 * 用做判断要是有函数返回为void的话做对比
	 */
	private static final String VOID_STRING ="void";
	
	//private static final String PACKAGE_STRING="com.service.";
	private static final String PACKAGE_STRING="com.example.demo.base.";

	
	/**
	 * 用做获取操作的哪个服务类的字符串key
	 */
	private static final String SERVICE_STRING="service";
	
	/**
	 * 用做获取函数的字符串key
	 */
	private static final String METHOD_STRING="method";
	
	/**
	 * 用获取参数数组的key
	 */
	private static final String PARAMS_STRING="params";
	
	/**
	 * 返回的字符串key
	 */
	private static final String RESULT_STRING="result";
	
	/**
	 * 放置Class的map,不用再重新forName
	 */
	private static final Map<String,Class> CLASS_MAP=new HashMap<String,Class>();
	
	/**
	 * 放置方法的map
	 */
	private static final Map<String,Map<String,Method>> METHOD_MAP=new HashMap<String, Map<String,Method>>();
	
	/**
	 * 接收所有传过来的事件，
	 * 然后调用相关的服务类的函数然后处理,可以改为多线程，
	 * 或者只在这里存，然后用线程一个个处理，逻辑不变
	 * @return
	 * @throws Exception 
	 */
	public Object Handle(Map<String,Object> msg,ChannelHandlerContext ctx) throws Exception
	{
		
//		throw new BusinessException(1000,"错误信息");





		
		Class<?> c = Class.forName(PACKAGE_STRING+msg.get(SERVICE_STRING));
		
		if(msg.containsKey(PARAMS_STRING))
		{
			//有参数的情况
			Object[] params=(Object[])msg.get(PARAMS_STRING);
			List<Class> typeList=new ArrayList<Class>();
			for(Object temp:params)
			{
				typeList.add(temp.getClass());
			}
			Method m=c.getMethod((String)msg.get(METHOD_STRING), typeList.toArray(new Class[typeList.size()]));
			
			//判断该函数返回值是否为void,void的话就返回null
			//否则就返回AsObject
			if(m.getReturnType().getName().equals(VOID_STRING))
			{
				//返回为void的话，返回的是null
				return m.invoke(c.newInstance(), params);
			}
			else
			{
				//减少传回去的资料大小，参数就就不传了,可以传
				//这里一般为了有函数需要返回值，就封装在一个
				//对象里面了
				msg.put(RESULT_STRING, m.invoke(c.newInstance(), params));
				msg.remove(PARAMS_STRING);
				return msg;
			}
		}
		else
		{
			//没有参数的情况
			Method m=c.getMethod((String)msg.get(METHOD_STRING));
			
			if(m.getReturnType().getName().equals(VOID_STRING))
			{
				return m.invoke(c.newInstance());
			}
			else
			{
				msg.put(RESULT_STRING, m.invoke(c.newInstance()));
				msg.remove(PARAMS_STRING);
				return msg;
			}
		}
		
	}
	
}
