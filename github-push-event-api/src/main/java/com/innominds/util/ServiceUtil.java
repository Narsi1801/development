package com.innominds.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceUtil {

	private static final Logger logger = Logger.getLogger(ServiceUtil.class);

	@Autowired
	private PropertiesConfigurer props;

	@Autowired
	private APIMProxy apimProxy;

	public List<String> createAPI(String accessToken) {
		List<String> arrList = new ArrayList<String>();
		String apiEualId = props.getValueFromKey(ServiceConstants.APIM_EULA_ID);
		logger.info("API Euala Id:\t" + apiEualId);

		File folder = new File(ServiceConstants.JSON_FILES_FOLDER);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {

			String apiJsonFile = JsonFormatter.getPortalAPIJsonFile(file, apiEualId);
			if (apiJsonFile != null) {
				try {
					// System.out.println("created json file is:\t"+apiJsonFile);
					logger.info("created json file is:\t" + apiJsonFile);
					String createdFile = apimProxy.callService(apiJsonFile, accessToken);
					// System.out.println("api created successfully:\t"+createdFile);
					logger.info("api created successfully:\t" + createdFile);
					arrList.add(createdFile);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					logger.error(e.getMessage(), e);
					continue;
				}

			} else {
				// System.out.println("not a valid json file");
				logger.warn("not a valid json file");
				continue;
			}

		}
		return arrList;
	}

	public List<String> createAPI(String actualJsonFile, String accessToken) {
		String apiEualId = props.getValueFromKey(ServiceConstants.APIM_EULA_ID);
		logger.info("API Euala Id:\t" + apiEualId);
		List<String> publishedFilesList = new ArrayList<String>();
		JSONObject json;
		try {
			json = new JSONObject(actualJsonFile);
			String commitId = json.get("after").toString();
			System.out.println("commit id is:\t" + commitId);
			RestTemplate rt = new RestTemplate();
			String commitUri = props.getValueFromKey(ServiceConstants.GIT_COMMIT_URI) + commitId;
			String commitFilesJson = rt.getForObject(commitUri, String.class);
			System.out.println("commitFilesJson is:\t" + commitFilesJson);
			json = new JSONObject(commitFilesJson);
			org.json.JSONArray array = json.getJSONArray("files");
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject file = (JSONObject) array.get(i);
				String sign = (String) file.get("sha");
				String filename = (String) file.get("filename");
				//String patch = (String) file.get("patch");
				System.out.println("file name is:\t" + filename);
				System.out.println("sha is:\t" + sign);
				//System.out.println("patch is:\t" + patch);
				String blobUrl = props.getValueFromKey(ServiceConstants.GIT_BLOB_URI) + sign;
				String blobfile = rt.getForObject(blobUrl, String.class);
				System.out.println("blob file is:\t" + blobfile);
				JSONObject jsonFile = new JSONObject(blobfile);
				String encodedContent = (String) jsonFile.get("content");
				long fileSize = jsonFile.getLong("size");
				System.out.println("encodedContent is:\t" + encodedContent);
				System.out.println("filesize is:\t" + fileSize);

				encodedContent = encodedContent.replace("\n", "").replace("\r", "");
				String decodedJsonFile = null;

				try {
					decodedJsonFile = new String(Base64.getDecoder().decode(encodedContent), "UTF-8");
					System.out.println("decoded json file is:\t" + decodedJsonFile);
					JSONObject decodedJsonFileObject = new JSONObject(decodedJsonFile);
					String portalExpectedJson = JsonFormatter.getPortalAPIJsonFile(decodedJsonFileObject, apiEualId,
							filename, fileSize, encodedContent);
					System.out.println("portal expected json file is:\t"+portalExpectedJson);
					String portalPublishedFile = apimProxy.callService(portalExpectedJson, accessToken);
					publishedFilesList.add(portalPublishedFile);
					//return publishedFilesList;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return publishedFilesList;
	}

	private static Date getCurrentTime() {
		return new Date();
	}

	public static Date getTokenTime(long expiryInSeconds) {
		Date justNow = new Date();
		Calendar cal = Calendar.getInstance();

		expiryInSeconds = expiryInSeconds / (60 * 60);

		cal.set(Calendar.HOUR_OF_DAY, justNow.getHours() + (int) expiryInSeconds); // Your hour
		cal.set(Calendar.MINUTE, justNow.getMinutes()); // Your Mintue
		cal.set(Calendar.SECOND, justNow.getSeconds());

		return cal.getTime();
	}

	public static boolean isTokenExpired(int expiryInSecnds) {
		boolean isTokenExpired = false;
		if (expiryInSecnds == 0) {
			isTokenExpired = true;
			return isTokenExpired;
		}

		Date currTime = getCurrentTime();
		System.out.println("current time is:\t" + currTime);

		Date tokenTime = getTokenTime(expiryInSecnds);
		System.out.println("token time is:\t" + tokenTime);

		if (currTime.after(tokenTime) || (currTime.compareTo(tokenTime) == 0)) {
			isTokenExpired = true;
		}

		return isTokenExpired;

	}

	public static boolean isTokenExpired(Date tokenCreatedDate) {
		boolean isTokenExpired = false;
		Date currTime = getCurrentTime();
		System.out.println("current time is:\t" + currTime);

		if (currTime.after(tokenCreatedDate) || (currTime.compareTo(tokenCreatedDate) == 0)) {
			isTokenExpired = true;
		}

		return isTokenExpired;

	}

	public static void main(String[] args) {
		System.out.println(isTokenExpired(3600));
	}
}
