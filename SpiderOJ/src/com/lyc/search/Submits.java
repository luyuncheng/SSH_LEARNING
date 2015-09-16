package com.lyc.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.params.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Submits {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CookieStore sotre = null;
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		try {

			HttpUriRequest login = RequestBuilder
					.post()
					.setUri(new URI(
							"http://acm.hdu.edu.cn/userloginex.php?action=login"))
					.addParameter("user_id1", "luyunchengvj")
					.addParameter("password1", "52048436")
					.addParameter("B1", "login")/*.addParameter("url", "/")*/
					.build();
			CloseableHttpResponse response = httpclient.execute(login);
			try {
				HttpEntity entity = response.getEntity();

				System.out.println("Login form get: "
						+ response.getStatusLine());
				EntityUtils.consume(entity);
				
				System.out.println("Post logon cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		
		BufferedReader br = new BufferedReader(new FileReader(
				"/Users/luyuncheng/Documents/workspace/SpiderOJ/src/com/lyc/search/code.cpp"));
		String line = "";
		StringBuffer buf = new StringBuffer();
		while ((line = br.readLine()) != null) {
			buf.append(line);
		}
		String codes = buf.toString();

		System.out.print("我去");
		HttpGet gets=new HttpGet("http://acm.hdu.edu.cn/submit.php?pid=1000;");
		DefaultHttpClient cli=new DefaultHttpClient();
		cli.setCookieStore(cookieStore);
		HttpResponse resp=cli.execute(gets);
		System.out.println("resp:"+resp.getStatusLine().getStatusCode());
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			String path = "http://acm.hdu.edu.cn/submit.php?pid=1000";
			HttpPost httpPost = new HttpPost(path);
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("check", "0"));
			parameters.add(new BasicNameValuePair("problemid", "1000"));
			parameters.add(new BasicNameValuePair("language", "0"));
			parameters.add(new BasicNameValuePair("usercode", codes));
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
			client.setCookieStore(cookieStore);
			HttpResponse response = client.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("Code:" + code);
			InputStream is = response.getEntity().getContent();
			String text = ips2Str(is);
			System.out.println(text);
			if (code == HttpStatus.SC_MOVED_PERMANENTLY ||
				code == HttpStatus.SC_MOVED_TEMPORARILY) {
				Header[] heads=response.getAllHeaders();
				Header locationHeader = response.getLastHeader("Location") ;
			    String location = null;
			    if (locationHeader != null) {
			     location = locationHeader.getValue();
			     System.out.println("The page was redirected to:" + location);
			    } else {
			     System.err.println("Location field value is null.");
			    }

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(codes);
		/*
		 * String pid="1001"; CloseableHttpClient hClient
		 * =HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		 * try{ HttpUriRequest submit=RequestBuilder.post() .setUri(new
		 * URI("http://acm.hdu.edu.cn/submit.php"))
		 * .addParameter("ProblemID","1000") .addParameter("Langguage","0")
		 * .addParameter("Usercode", codes) .build(); CloseableHttpResponse
		 * response = httpclient.execute(submit); try { HttpEntity entity =
		 * response.getEntity();
		 * 
		 * System.out.println("Submit form get: " + response.getStatusLine());
		 * EntityUtils.consume(entity);
		 * 
		 * System.out.println("Post Sumbited cookies:"); List<Cookie> cookies =
		 * cookieStore.getCookies(); if (cookies.isEmpty()) {
		 * System.out.println("None"); } else { for (int i = 0; i <
		 * cookies.size(); i++) { System.out.println("- " +
		 * cookies.get(i).toString()); } } } finally { response.close(); } }
		 * finally { httpclient.close(); }
		 */
		/*
		 * int test=0;
		 * 
		 * @SuppressWarnings("deprecation") HttpUriRequest query =
		 * RequestBuilder.get() .setUri(new
		 * URI("http://acm.hdu.edu.cn/viewcode.php")) .addParameter("rid",
		 * "13817159").build();
		 * 
		 * HttpClient client = new DefaultHttpClient();
		 * client.getParams().setParameter(ClientPNames.COOKIE_POLICY,
		 * CookiePolicy.BROWSER_COMPATIBILITY);
		 * System.out.println("查询："+query.toString()); if(test==1)return;
		 * client.getCookieStore().addCookie(cookieStore.getCookies().get(0));
		 * ResponseHandler<String> handler = new BasicResponseHandler(); String
		 * body = client.execute(query, handler); Document document =
		 * Jsoup.parse(body); System.out.println("文本："+document.text());
		 * FileWriter firewrite = new FileWriter("D:\\nouse\\"+document.title()
		 * + ".html"); firewrite.write(document.html());
		 */
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
