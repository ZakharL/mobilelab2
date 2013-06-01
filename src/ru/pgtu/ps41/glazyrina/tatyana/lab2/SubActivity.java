package ru.pgtu.ps41.glazyrina.tatyana.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;
import org.xmlpull.v1.XmlPullParser;

import ru.pgtu.ps41.glazyrina.tatyana.lab2.entity.CurrencyInfo;

/**
 * import AndroidQuery
 */
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;

public class SubActivity extends Activity {

	int year1;
	int month1;
	int day1;

	int year2;
	int month2;
	int day2;
	
	private AQuery aq;

	TextView tvData;

	String dateToString(Integer date1) {
	 	String date;
		if (date1 < 10)
		{
			//String date1 = date.toString();
			date = "0" + date1.toString();
		}
		else {
			date = date1.toString();
		}
		return date;
	}

	public HttpResponse postData(String url) {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);

	    try {
	        // Add your data
	        

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        return response;
	        
	    } catch (ClientProtocolException e) {
	    	
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	    return null;
	 } 
	
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		Bundle bandle = getIntent().getExtras();

		tvData = (TextView) findViewById(R.id.textView1);
		
		aq = new AQuery(this);
		
	

		if (bandle != null) {
			DataInfo dateAll = (DataInfo) bandle.get("dataInfo");
			Integer month1 = dateAll.getMonth() + 1;
			Integer day1 = dateAll.getDayOfMonth();
			Integer year1 = dateAll.getYear();

			Integer month2 = dateAll.getMonth2() + 1;
			Integer day2 = dateAll.getDayOfMonth2();
			Integer year2 = dateAll.getYear2();
			
			String month01 = dateToString(month1);
			String month02 = dateToString(month2);
			
			
		
			
			String urlStr = "http://www.cbr.ru/scripts/XML_daily_eng.asp?date_req=";
			String urlStr1 = urlStr + day1.toString() + "/" + month01.toString() + "/" + year1.toString() ;
			
			String urlStr2 = urlStr + day2.toString() + "/" + month02.toString() + "/" + year2.toString() ;
			
			Log.d("url", urlStr2);
			
			try
			{
				//async_xpp("https://picasaweb.google.com/data/feed/base/featured?max-results=8");
				async_xpp(urlStr2);
			}
			catch(Exception $e)
			{
				Log.d("url", $e.toString());
			}
		}

		
	}

	public void async_xpp(String url) {
	
		aq.ajax(url, XmlPullParser.class, new AjaxCallback<XmlPullParser>() {
		
			public void callback(String url, XmlPullParser xpp, AjaxStatus status) {
				
				String currentTitle = null;
				
				ArrayList <CurrencyInfo> currencyList = new ArrayList<CurrencyInfo>();
				CurrencyInfo currentInfo = new CurrencyInfo();
				
				try {				
					
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
								Log.d("curt6", currentInfo.toString());
							}						
						} else if (eventType == XmlPullParser.TEXT) {
							String text = xpp.getText();
							if ("CharCode".equals(currentTag) && text.length() >= 3){
								currentInfo.charCode = text;
							}else if ("Name".equals(currentTag) && text.length() >= 3){
								currentInfo.name = text;
							}else if ("Value".equals(currentTag) && text.length() >= 3){
								currentInfo.value = text;
							}else if ("Nominal".equals(currentTag) && text.length() >= 3){
								currentInfo.nominal = text;
							}
						 }
						
						eventType = xpp.next();
					}
				
				} catch(Exception e) {
					AQUtility.report(e);
				}
			}
		});
		
	
		
	}
}
