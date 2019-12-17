package com.hf.Thread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.hf.entity.IpsEntity;

public class CheckUrl {
	public static void main(String[] args) {
		try {
			Connection connect = Jsoup.connect("http://www.66ip.cn/"+2+".html");
	        Map<String, String> header = new HashMap<String, String>();
	        header.put("Host", "http://info.bet007.com");
	        header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
	        header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        header.put("Accept-Language", "zh-cn,zh;q=0.5");
	        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
	        header.put("Connection", "keep-alive");
	        Connection data = connect.headers(header);
			Elements elementsByTag = data.get().getElementsByTag("tr");
			for (int i = 3; i < elementsByTag.size(); i++) {
				Elements elementsByTag2 = elementsByTag.get(i).getElementsByTag("td");
//				System.out.println(elementsByTag2.toString());
				System.out.println(elementsByTag2.get(0)+":"+elementsByTag2.get(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
