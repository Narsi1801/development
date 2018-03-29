package com.innominds.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.innominds.config.CacheService;
import com.innominds.config.Token;

@Component
public class GatewayOauthProxy {

	@Autowired
	private PropertiesConfigurer props;

	@Autowired
	private CacheService cacheService;

	private static long TOKEN_EXPIRY = 0;
	private static String ACCESS_TOKEN = null;

	private static Date tokenCreationDate = null;

	public Token oauthToken() {
		Token token = cacheService.get("access_token");
		try {
			if(token == null) {
				token = new Token();
				token = createToken(token);
			}else {
				boolean isTokenExpired = ServiceUtil.isTokenExpired(token.getTokenCreatedDate());
				if(isTokenExpired) {
					token = new Token();
					token = createToken(token);
				}
			}
			
			
		}catch (Exception e) {
		e.printStackTrace();
	}
		return token;
}
	
	private Token createToken(Token token) throws JSONException, KeyManagementException, NoSuchAlgorithmException {
		SSLUtil.turnOffSslChecking();
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(props.getValueFromKey(ServiceConstants.GRANT_TYPE),
					props.getValueFromKey(ServiceConstants.GRANT_TYPE_VALUE));
		map.add(props.getValueFromKey(ServiceConstants.CLIENT_ID),
					props.getValueFromKey(ServiceConstants.CLIENT_ID_VALUE));
		map.add(props.getValueFromKey(ServiceConstants.CLIENT_SECRET),
					props.getValueFromKey(ServiceConstants.CLIENT_SECRET_VALUE));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
					headers);

		ResponseEntity<String> response = rt.exchange(
					props.getValueFromKey(ServiceConstants.OAUTH2_GATEWAY_URI), HttpMethod.POST, request,
					String.class);
			if (response != null && response.getStatusCodeValue() == 200) {
				String resp = response.getBody();
				JSONObject json = new JSONObject(resp);

				if (json.has("access_token")) {
					ACCESS_TOKEN = json.getString("access_token");
					// cacheService.putToken(ACCESS_TOKEN);
				}
				if (json.has("expires_in")) {
					TOKEN_EXPIRY = json.getInt("expires_in");
					tokenCreationDate = ServiceUtil.getTokenTime(TOKEN_EXPIRY);
				}
			} else {
				System.out.println("response is null");
			}

			token.setAccess_token(ACCESS_TOKEN);
			token.setExpires_in(String.valueOf(TOKEN_EXPIRY));
			token.setTokenCreatedDate(tokenCreationDate);

		return token;
	}
}
