package com.innominds.rest;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.innominds.config.Token;
import com.innominds.util.GatewayOauthProxy;
import com.innominds.util.ServiceUtil;

@RestController
public class APIMRestService {
	private static final Logger logger = Logger.getLogger(APIMRestService.class);

	@Autowired
	GatewayOauthProxy oauthProxy;

	@Autowired
	ServiceUtil serviceUtil;

	@PostMapping(value = "/monitor", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> createApi(@RequestBody String payload) {
		System.out.println("am in create api()");
		logger.info("am in create api()");
		List<String> publishedApiList = null;
		try {
			
			if (payload != null && !payload.isEmpty()) {
				System.out.println("payload is:\t" + payload);
				logger.info("payload is:\t" + payload);
				Token oauthToken = oauthProxy.oauthToken();
				logger.info("access token is:\t" + oauthProxy.oauthToken());
				publishedApiList = serviceUtil.createAPI(payload,oauthToken.getAccess_token());
			}else {
				publishedApiList = new ArrayList<String>();
				StringBuilder str = new StringBuilder();
				str.append("{");
				str.append("	\"message\": \"payload is missing. this is because,not a git a push event\"");
				str.append("}");
				publishedApiList.add(str.toString());
			}
			
			
		} catch (Exception e) {
			publishedApiList = new ArrayList<String>();
			/*StringBuilder str = new StringBuilder();
			str.append("{");
			str.append("	\"error\": "+e.getMessage());
			str.append("}");
			publishedApiList.add(str.toString());*/
			String errorMsg = e.getMessage();
			//errorMsg = errorMsg.replace("\n", "").replace("\r", "");
			publishedApiList.add(errorMsg);
			return new ResponseEntity<List<String>>(publishedApiList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<String>>(publishedApiList,HttpStatus.OK);

	}

	@GetMapping(value = "/publish")
	public ResponseEntity<String> publishAPI() {
		System.out.println("am in publish api()");
		logger.info("am in create api()");
		try {
			logger.info("access token is:\t" + oauthProxy.oauthToken());
			serviceUtil.createAPI(oauthProxy.oauthToken().getAccess_token());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
