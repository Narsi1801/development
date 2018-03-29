package com.innominds.util;

import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class JsonFormatter {

	private static final Logger logger = Logger.getLogger(JsonFormatter.class);

	public static String readFile(File file) {
		String result = "";
		try {
			result = FileUtils.readFileToString(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getPortalAPIJsonFile(File file, String apiEulaId) {
		try {
			String fileName = file.getName();
			logger.info("uploaded file name is:\t" + fileName);

			// basePath
			// schemes
			String encodedFile = null;
			String description = null;
			if (file.isFile() && (fileName.substring(fileName.lastIndexOf('.') + 1)
					.equals(ServiceConstants.JSON_EXTENSION_TYPE))) {
				long fileSize = file.length();
				logger.info("size of the file is:\t" + fileSize);
				JSONObject jobj = new JSONObject(readFile(file));
				if (jobj.has("info")) {
					JSONObject infoJson = (JSONObject) jobj.get("info");
					if (infoJson != null) {
						if (infoJson.has("description")) {
							description = infoJson.get("description").toString();
						}

					}
				}

				String host = "";
				String basePath = "";
				String schemes = "";
				String applicationUrl = "https://swagger.io";
				if (jobj.has("host")) {
					host = jobj.get("host").toString();
				}
				if (jobj.has("basePath")) {
					basePath = jobj.get("basePath").toString();
				}
				if (jobj.has("schemes")) {
					schemes = jobj.get("schemes").toString();
					schemes = schemes.substring(1, schemes.length() - 1);
					String url = schemes.replaceAll("\"", "");
					applicationUrl = url + "://" + host + basePath;
					System.out.println("application url is:\t" + applicationUrl);
					logger.info("application url is:\t" + applicationUrl);
				}

				byte[] encoded = Base64.getEncoder().encode(FileUtils.readFileToByteArray(file));
				encodedFile = new String(encoded);
				logger.info("encoded file is:\t" + encodedFile);
				String apiJson = extractJson(fileName, apiEulaId, description, applicationUrl, fileSize, encodedFile);

				return apiJson;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String getPortalAPIJsonFile(JSONObject jobj, String apiEulaId, String fileName, 
			long fileSize, String encodedFile) {
		try {
			
			String description = null;
			if (jobj.has("info")) {
					JSONObject infoJson = (JSONObject) jobj.get("info");
					if (infoJson != null) {
						if (infoJson.has("description")) {
							description = infoJson.get("description").toString();
						}

					}
				}

				String host = "";
				String basePath = "";
				String schemes = "";
				String applicationUrl = "https://swagger.io";
				if (jobj.has("host")) {
					host = jobj.get("host").toString();
				}
				if (jobj.has("basePath")) {
					basePath = jobj.get("basePath").toString();
				}
				if (jobj.has("schemes")) {
					schemes = jobj.get("schemes").toString();
					schemes = schemes.substring(1, schemes.length() - 1);
					String url = schemes.replaceAll("\"", "");
					applicationUrl = url + "://" + host + basePath;
					System.out.println("application url is:\t" + applicationUrl);
					logger.info("application url is:\t" + applicationUrl);
				}
				String apiJson = extractJson(fileName, apiEulaId, description, applicationUrl, fileSize, encodedFile);

				return apiJson;
			//}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String extractJson(String fileName, String apiEualId, String desc, String appUrl, long fileSize,
			String encodedFile) {

		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append("	\"SsgUrl\": \"" + fileName + "\",");
		str.append("	\"Name\": \"Swagger " + fileName + "\",");
		str.append("	\"Version\": \"1.0.0\",");
		// str.append(" \"ApiEulaUuid\": \"e3ffb13d-9cfa-424a-a212-a6a1f9da0a57\",");
		str.append("	\"ApiEulaUuid\": \"" + apiEualId + "\",");
		if (desc != null) {
			// str.append(" \"PrivateDescription\": \""+desc+"\",");
			// str.append(" \"Description\": \""+desc+",");
			str.append("	\"PrivateDescription\": \"" + desc + "\",");
			str.append("	\"Description\": \"" + desc + "\",");
		}
		// str.append(" \"PrivateDescription\": \"This is a sample server "+fileName+"
		// server. For this sample, you can use the api key `special-key` to test the
		// authorization filters.\",");
		str.append("	\"AccessStatus\": \"PRIVATE\",");

		str.append("	\"AuthenticationType\": \"NONE\",");
		str.append("	\"AuthenticationParameters\": \"{}\",");
		str.append("	\"CustomFieldValues\": {");
		str.append("		\"results\": []");
		str.append("	},");
		str.append("	\"ApiLocationUrl\": \"" + appUrl + "\",");
		str.append("	\"PolicyEntities\": {");
		str.append("		\"results\": [{");
		str.append("			\"PolicyEntityUuid\": \"172594b6-18ba-4b0c-8d61-807db457e81d\",");
		str.append("			\"PolicyTemplateArguments\": {");
		str.append("				\"results\": []");
		str.append("			}");
		str.append("		}]");
		str.append("	},");
		str.append("	\"OrgUuid\": null,");
		str.append("	\"SpecContent\": \"data:;base64," + encodedFile + "\",");
		str.append("	\"SpecFilename\": \"" + fileName + "\",");
		str.append("	\"SpecFilesize\": " + fileSize + ",");
		str.append("	\"Uuid\": \"{{GENERATED_GUID}}\"");
		str.append("}");

		return str.toString();
	}
}
