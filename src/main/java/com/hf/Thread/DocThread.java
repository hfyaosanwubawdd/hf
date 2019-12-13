package com.hf.Thread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DocThread {

	public static void main(String[] args) {
		try {
			Connection connect = Jsoup.connect("http://wenshu.court.gov.cn/website/wenshu/181107ANFZ0BXSK4/index.html?docId=08ad2a5e901349849001ab1900c1be72");
	        Map<String, String> header = new HashMap<String, String>();
	        header.put("Host", "http://info.bet007.com");
	        header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
	        header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        header.put("Accept-Language", "zh-cn,zh;q=0.5");
	        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
	        header.put("Connection", "keep-alive");
	        Document document = connect.headers(header).get();
	        System.out.println(document.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
