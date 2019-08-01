package com.joe.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	public static void main(String[] args) {

	}

	public static ArrayList<String> selectLine(String lineName) {
		ArrayList<String> result = new ArrayList<String>();
		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=getLineDir&selBLine=" + lineName;
		try {
			Document document = (Jsoup.connect(url).get());
			Elements es = document.select("a");
			for (Element e : es) {
				result.add(e.text() + " " + e.attr("data-uuid"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<String> getStationList(String lineName, String lineID) {
		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=getDirStation&selBLine=" + lineName
				+ "&selBDir=" + lineID;
		ArrayList<String> stationlist = new ArrayList<String>();
		try {
			Document document = (Jsoup.connect(url).get());
			Elements es = document.select("a");
			for (Element e : es) {
				stationlist.add(e.text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stationlist;
	}

	public String getArrivingTime(String lineName, String lineID, int stationIndex) {
		String text = "";
		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=busTime&selBLine=" + lineName + "&selBDir="
				+ lineID + "&selBStop=" + stationIndex;
		try {
			Document document = (Jsoup.connect(url).get());
			String s = document.toString().split("\n")[11];
			String[] result = StringEscapeUtils.unescapeHtml(decode(s)).split("<|>|\\?");
			if (result[2].contains("���һ����")) {
				text = ("���һ��������˻���" + result[2].substring(11, 12) + "վ��");
				text = text + result[4];
				text += result[6].substring(1, result[6].length() - 1);
				text += (result[8] + result[10].substring(1));
			} else {
				text = ("�������ѹ�վ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}

	public static String decode(String unicodeStr) {
		if (unicodeStr == null) {
			return null;
		}
		StringBuffer retBuf = new StringBuffer();
		int maxLoop = unicodeStr.length();
		for (int i = 0; i < maxLoop; i++) {
			if (unicodeStr.charAt(i) == '\\') {
				if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
					try {
						retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException localNumberFormatException) {
						retBuf.append(unicodeStr.charAt(i));
					}
				else
					retBuf.append(unicodeStr.charAt(i));
			} else {
				retBuf.append(unicodeStr.charAt(i));
			}
		}
		return retBuf.toString();
	}
}