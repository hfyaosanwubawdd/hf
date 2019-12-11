package com.hf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 
public class LanPing {
	private int corePoolSize;//线程池的线程个数
	private int maximumPoolSize;
	private ThreadPoolExecutor threadPool;
	private List<DeviceInfo> devices; //用于保存每次ping线程执行通过的的结果ip+hostName;
	public LanPing() {
		corePoolSize=5;
		maximumPoolSize=100;
		threadPool=new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 3, 
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
				new ThreadPoolExecutor.CallerRunsPolicy());
		devices=new ArrayList<DeviceInfo>();
	}
	public List<DeviceInfo> search() throws UnknownHostException, InterruptedException{
		InetAddress host = InetAddress.getLocalHost();
    	String hostName=host.getHostName();
    	String hostAddress = host.getHostAddress();
    	System.out.println(hostName+":"+hostAddress);
    	int k=0;
    	k=hostAddress.lastIndexOf(".");
    	String ss = hostAddress.substring(0,k+1);
    	for(int i=1;i <255;i++){  //对所有局域网Ip
    	  String iip=ss+i;
    	  threadPool.execute(new PingIP(iip));
    	  Thread.sleep(100);
    	}
    	return devices;
	}
    
	
	
    class  PingIP implements Runnable{
    	private String ip;
    	public PingIP(String ip) {
    		this.ip=ip;
		}
        synchronized public void  run(){
    		try{
	    		String ping="ping -n 1 -w 5 "+ip;
	        	System.out.println(ping);
	        	Process process=Runtime.getRuntime().exec(ping);
	        	InputStreamReader inputStream = new InputStreamReader(process.getInputStream(),"GBK");
	        	BufferedReader reader=new BufferedReader(inputStream);
	        	StringBuffer buf=new StringBuffer();
	        	String s;
	        	while((s=reader.readLine())!=null){
	        		buf.append(s+"\n");
	        	}
	            if(buf.toString().indexOf("请求超时")!=-1){
	            	
	            }else{
	            	InetAddress host = InetAddress.getByName(ip);
	            	String hostName=host.getHostName();
	            	DeviceInfo deviceInfo=new DeviceInfo(hostName, ip);
	            	devices.add(deviceInfo);
	            	System.out.println(hostName+":"+ip);
	            }
	        	//System.out.println(buf.toString());
    		}catch(IOException e){
    			e.printStackTrace();
    		}
    	}
    }
    class DeviceInfo{
    	private String name;
    	private String ip;
    	public DeviceInfo(String name,String ip) {
    		this.name=name;
    		this.ip=ip;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
    }
}
