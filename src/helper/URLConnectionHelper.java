package helper;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class URLConnectionHelper {
	private static final String TAG = "HttpUtils";
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
    	StringBuffer resultData = new StringBuffer();
    	
    	Log.i(TAG,"url:"+url);
    	
    	//建立http get联机
    	HttpGet request = new HttpGet(url);
    	
    	//建立连接
    	try {
    		HttpResponse response = new DefaultHttpClient().execute(request);  
    		String strResult = EntityUtils.toString(response.getEntity()); 
			resultData.append(strResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	Log.i(TAG,"get complete");
    	return resultData.toString();
    }
    
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
    	StringBuffer resultData = new StringBuffer();
    	
    	String realUrl = url+"?"+param;
    	Log.i(TAG,"url:"+realUrl);
    	
    	//建立http get联机
    	HttpGet request = new HttpGet(realUrl);
    	
    	//UA
    	request.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
    	
    	//建立连接
    	try {
    		HttpResponse response = new DefaultHttpClient().execute(request);  
    		String strResult = EntityUtils.toString(response.getEntity()); 
			resultData.append(strResult);
		} catch (Exception e) {
			// TODO: handle exception
			Log.i(TAG,"http fail:" + e);
		}
    	
    	Log.i(TAG,"result:"+resultData.toString());
    	return resultData.toString();
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param param<key,value>格式的post参数
     *            
     * @return 所代表远程资源的响应结果字符串
     */
    public static String sendPost(String url, Map<String, String>param) {
    	StringBuffer resultData = new StringBuffer();
    	
    	Log.i(TAG,"url:"+url);
    	
    	//建立http post联机
    	HttpPost request = new HttpPost(url);
    	
    	//UA
    	request.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
    	
    	//添加post参数
    	List<NameValuePair> postParams = new ArrayList<NameValuePair>();
    	for (Entry<String, String> e : param.entrySet()) {
    		postParams.add(new BasicNameValuePair(e.getKey(), e.getValue()));
    	}
    	
    	try {
			request.setEntity(new UrlEncodedFormEntity(postParams, HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(request);
			String strResult = EntityUtils.toString(response.getEntity()); 
			resultData.append(strResult);
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    	Log.i(TAG,"post complete");
    	return resultData.toString();
    }   
}
