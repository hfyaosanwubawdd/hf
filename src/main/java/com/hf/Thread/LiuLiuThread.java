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
public class LiuLiuThread implements Runnable{
	private IpsDao ipsDao= SpringContextUtil.getBean(IpsDao.class);
	@Override
	public void run() {
		Integer page = 2;
		try {
			while (ConstanceUtil.SPIDER_STATE != 0) {
				Connection connect = Jsoup.connect("http://www.66ip.cn/"+page+".html");
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
				Elements elementsByTag = data.get().getElementsByTag("tr");
				String ip;
				Integer port;
				List<IpsEntity> ipsList = new ArrayList<IpsEntity>();
				for (int i = 4; i < elementsByTag.size(); i++) {
					Elements elementsByTag2 = elementsByTag.get(i).getElementsByTag("td");
					if (elementsByTag2 != null && elementsByTag2.size() > 2) {
						ip = elementsByTag2.get(0).toString().replace("<td>", "").replace("</td>", "")+":"+elementsByTag2.get(1).toString().replace("<td>", "").replace("</td>", "");
						IpsEntity ipEntity = new IpsEntity();
						ipEntity.setIp(ip);
						ipEntity.setDatafrom("liuliu");
						ipsList.add(ipEntity);
					}
				}
				if (ipsList != null && ipsList.size() > 0) {
					LogUtil.info(ipsList.get(0).getIp()+" --- "+ipsList.size(), this.getClass());
					ipsDao.batchAddIps(ipsList);
				}else {
					LogUtil.info("null", this.getClass());
				}
				try {
					Thread.sleep(580000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
