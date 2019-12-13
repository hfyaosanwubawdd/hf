//package com.hf.Thread;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//import org.jsoup.internal.StringUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.hf.dao.IpsDao;
//import com.hf.entity.IpsEntity;
//import com.hf.util.SpringContextUtil;
//public class CrossinThread implements Runnable{
//	private IpsDao ipsDao= SpringContextUtil.getBean(IpsDao.class);
//	@Override
//	public void run() {
//        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).followRedirects(HttpClient.Redirect.NORMAL).build();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://lab.crossincode.com/proxy/get/?num=50")).timeout(Duration.ofMillis(5009)).build();
//        HttpResponse<String> response = null;
//        for (int i = 0; i < 10; i++) {
//        	try {
//    			response = client.send(request, HttpResponse.BodyHandlers.ofString());
//    		} catch (IOException e) {
//    			e.printStackTrace();
//    		} catch (InterruptedException e) {
//    			e.printStackTrace();
//    		}
//    		String responseStr = response.body();
//    		if (!StringUtil.isBlank(responseStr)) {
//    			JSONObject responseJson = JSON.parseObject(responseStr);
//    			JSONArray jsonArray = responseJson.getJSONArray("proxies");
//    			JSONObject jsonObj;
//    			String ip;
//    			Integer port;
//    			List<IpsEntity> ipsList = new ArrayList<IpsEntity>();
//    			for (Object object : jsonArray) {
//    				jsonObj = (JSONObject) object;
//    				ip = jsonObj.getString("http");
//    				port = Integer.valueOf(ip.substring(ip.indexOf(":")+1));
//    				ip = ip.substring(0,ip.indexOf(":"));
//    				IpsEntity ips = new IpsEntity();
//    				ips.setIp(ip);
//    				ips.setUseport(port);
//    				ips.setDatafrom("Crossin");
//    				ipsList.add(ips);
//    			}
//    			ipsDao.batchAddIps(ipsList);
//    		}
//    		try {
//				Thread.sleep(60000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
