package com.lyc.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.safety.Cleaner;

public class SUB2 {
	public static void main(String[] args) throws Exception {
		solve();
	}

	public static void solve() throws Exception {
		String username = "luyunchengvj";
		String password = "52048436";
		String urllogin = "http://acm.hdu.edu.cn/userloginex.php?action=login";
		int test=0;
		if(test==1)System.out.println("start");
		DefaultHttpClient client = new DefaultHttpClient();
		
		HttpPost post = new HttpPost(urllogin);
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		CookieStore cookieStore = client.getCookieStore();
		client.setCookieStore(cookieStore);
		
		int code = response.getStatusLine().getStatusCode();
		System.out.println("Code:" + code);
		
		
		BufferedReader br = new BufferedReader(new FileReader(
				"/Users/luyuncheng/Documents/workspace/SpiderOJ/src/com/lyc/search/code.cpp"));

//		"D:\\nouse\\code.cpp"));
		String line = "";
		StringBuffer buf = new StringBuffer();
		while ((line = br.readLine()) != null) {
			buf.append(line);
		}
		String codes2 = buf.toString();		
		if(test==0){
			System.out.println(codes2);
//			return;
		}
		
		String path = "http://acm.hdu.edu.cn/submit.php?pid=1001";
		HttpPost httpPost = new HttpPost(path);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("check", "0"));
		parameters.add(new BasicNameValuePair("problemid", "1001"));
		parameters.add(new BasicNameValuePair("language", "0"));
		parameters.add(new BasicNameValuePair("usercode", codes2));
		httpPost.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));


		DefaultHttpClient client2 = new DefaultHttpClient();
		DefaultHttpClient client3 = new DefaultHttpClient();
		
		client2.setCookieStore(cookieStore);
		client3.setCookieStore(cookieStore);
		HttpResponse response2 = client2.execute(httpPost);
		int code2 = response2.getStatusLine().getStatusCode();
		System.out.println("Code:" + code2);
		InputStream is = response.getEntity().getContent();
		String text = ips2Str(is);
//		System.out.println(text);
		if (code2 == HttpStatus.SC_MOVED_PERMANENTLY
				|| code2 == HttpStatus.SC_MOVED_TEMPORARILY) {
			Header[] heads = response.getAllHeaders();
			System.out.println("headslength"+heads.length);
			for (Header ii:heads){
				System.out.println("头：：："+ii.getValue());
			}
			if(test==1)return;
			Header locationHeader = response2.getFirstHeader("Location");
			String location = null;
			if (locationHeader != null) {
				location = locationHeader.getValue();
				System.out.println("location：" + location);//重定向为status
				HttpPost post3=new HttpPost("http://acm.hdu.edu.cn/"+"status.php");
				post3.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
				HttpResponse response3=client3.execute(post3);
				int code3 = response3.getStatusLine().getStatusCode();
				System.out.println("Code3:" + code3);
				System.out.println("The page was redirected to:" + location);
			} else {
				System.err.println("Location field value is null.");
			}

		}
	}

	public static String ips2Str(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

}
