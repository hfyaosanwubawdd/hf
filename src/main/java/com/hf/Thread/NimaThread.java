package com.hf.Thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hf.dao.IpsDao;
import com.hf.entity.IpsEntity;
import com.hf.util.ConstanceUtil;
import com.hf.util.LogUtil;
import com.hf.util.SpringContextUtil;
public class NimaThread implements Runnable{
	private IpsDao ipsDao= SpringContextUtil.getBean(IpsDao.class);
	@Override
	public void run() {
		Integer page = 1;
		try {
			while (ConstanceUtil.SPIDER_STATE != 0) {
				Connection connect = Jsoup.connect("http://www.nimadaili.com/gaoni/"+page+"/");
				if (page >= 100) {
					page = 1;
				}else {
					page++;
				}
		        Map<String, String> header = new HashMap<String, String>();
		        header.put("Host", "http://info.bet007.com");
		        header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
		        header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		        header.put("Accept-Language", "zh-cn,zh;q=0.5");
		        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
		        header.put("Connection", "keep-alive");
		        Connection data = connect.headers(header);
				Elements elementsByTag = data.get().getElementsByTag("td");
				String ip;
				Integer port;
				List<IpsEntity> ipsList = new ArrayList<IpsEntity>();
				for (Element element : elementsByTag) {
					if (element.toString().contains(":") && element.toString().contains(".")) {
						ip = element.toString().replace("<td>", "").replace("</td>", "");
	    				IpsEntity ips = new IpsEntity();
	    				ips.setIp(ip);
	    				ips.setDatafrom("nima");
	    				ipsList.add(ips);
					}
				}
				if (ipsList != null && ipsList.size() > 0) {
					LogUtil.info(ipsList.get(0).getIp()+" --- "+ipsList.size(), this.getClass());
					ipsDao.batchAddIps(ipsList);
				}else {
					LogUtil.info("null", this.getClass());
				}
				try {
					Thread.sleep(600000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
