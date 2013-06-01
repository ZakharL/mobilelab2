package ru.pgtu.ps41.glazyrina.tatyana.lab2.utils;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;

import ru.pgtu.ps41.glazyrina.tatyana.lab2.entity.CurrencyInfo;


public class CurrencyGrabber {

	public static ArrayList <CurrencyInfo> getList(XmlPullParser xpp) {
		
		String currentTitle = null;
		
		ArrayList <CurrencyInfo> currencyList = new ArrayList<CurrencyInfo>();
		CurrencyInfo currentInfo = new CurrencyInfo();
		
		try
		{
			int eventType = xpp.getEventType();
			String currentTag = new String();
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				
				if(eventType == XmlPullParser.START_TAG) {
					currentTag = xpp.getName();
					if ("Valute".equals(currentTag)) {
						currentInfo = new CurrencyInfo();
					}
				} else if (eventType == XmlPullParser.END_TAG) {
					if ("Valute".equals(xpp.getName())) {
						currencyList.add(currentInfo);
					}						
				} else if (eventType == XmlPullParser.TEXT) {
					String text = xpp.getText();
					if ("CharCode".equals(currentTag) && text.length() >= 3){
						currentInfo.charCode = text;
					}else if ("Name".equals(currentTag) && text.length() >= 3){
						currentInfo.name = text;
					}else if ("Value".equals(currentTag) && text.length() >= 3){
						currentInfo.value = text;
					}else if ("Nominal".equals(currentTag) && text.length() > 0){
						currentInfo.nominal = text;
					}
				 }
				
				eventType = xpp.next();
			}
		} catch (Exception e) {
			Log.d("curt6", e.getMessage());
		}
				
		return currencyList;
	}
}
