package com.innominds.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

@Component
public class APIMProxy {

	private static final Logger logger = Logger.getLogger(APIMProxy.class); 
	
	@Autowired
	private PropertiesConfigurer props;
	
	/**
	 * Call Portal API  to create and publish an API 
	 * @param json
	 * @param token
	 * @return
	 */
	public String callService(String json, String token) {
		CloseableHttpClient httpClient = SSLUtil.disableSSLForCloseAbleHttpClient();
		String uri = props.getValueFromKey(ServiceConstants.APIM_AUTH_URI);
		logger.info("auth uri is:\t"+uri);
		HttpPost request = new HttpPost(uri);

		/* ADD HEADER INFO */
		request.addHeader(ServiceConstants.AUTH_HEADER_KEY, ServiceConstants.AUTH_BEARER_HEADER + token);
		request.addHeader(ServiceConstants.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_UTF8_VALUE);

		/* JSON AS STRINGENTITY */
		StringEntity input = null;
		try {
			input = new StringEntity(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setEntity(input);
		
		/* SEND AND RETRIEVE RESPONSE */
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* RESPONSE AS JSON STRING */
		String result = null;
		try {
			result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			logger.info("result is:\t"+result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String callServiceFromMain(String json, String token) {

		CloseableHttpClient httpClient = SSLUtil.disableSSLForCloseAbleHttpClient();
		//String uri = props.getValueFromKey(ServiceConstants.APIM_AUTH_URI);
		String uri = "https://apim.dev.ca.com:38443/apim/2.0/Apis";
		HttpPost request = new HttpPost(uri);

		// ADD HEADER INFO 
		request.addHeader(ServiceConstants.AUTH_HEADER_KEY, ServiceConstants.AUTH_BEARER_HEADER + token);
		request.addHeader(ServiceConstants.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		request.addHeader("authorization", "bearer "+token);
		request.addHeader("content-type", "application/json");

		// JSON AS STRINGENTITY 
		StringEntity input = null;
		try {
			input = new StringEntity(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setEntity(input);
		
        

		// SEND AND RETRIEVE RESPONSE 
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// RESPONSE AS JSON STRING 
		String result = null;
		try {
			result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			System.out.println("result is:\t"+result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public static void main(String[] args) {
		String ad = null;
		String svFrame= "{mimeType"+":"+"${request.mainPart.contentType}"+","+"text"+":"+ad+" },"+"persistImmediate"+":1,"+"queryParams"+":["+ ad+"}],"+"response"+":{ " + "headers"+":[ "+ ad + " ],"+ "status"+":"+ad+"},"+"mimeType"+":"+ad+"},"+"text"+":"+"}"+" },"+ "time"+":"+ad+"},"+"thinkTime"+":"+0+","+ "id"+":"+"b95d6b72-a658-9053-0478-b7fc563e235d"+","+ "transactionId"+":"+"b95d6b72-a658-9053-0478-b7fc563e235d"+","+"sessionId"+":"+"7629D75CB8E1BE9D24E55F7A070E1982"+","+ "userInitialized"+":"+true+","+ "cookies"+":["+""+"}]"+" }}";
		svFrame = svFrame.replaceAll(" (\\[{)\\s\\w+\\s*(\"\\w+\":\\s*\"\\w+\",)\\s*\\w+\\s(\"\\w+\":\\s*\"\\w+\"}])", "\\[\\{$1,$2\\}\\]");
		System.out.println(svFrame);
	}
	
}
