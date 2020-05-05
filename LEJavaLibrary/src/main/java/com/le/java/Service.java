package com.le.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Service
 *
 */
class Service {
	
	private static final Service sr = new Service();

	private Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Service getServiceObject() {
		return sr;
	}

	/**
	 * @return
	 */
	private OkHttpClient getUnsafeOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}
			} };

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			OkHttpClient okHttpClient = builder.build();
			return okHttpClient;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param requestUrl
	 * @return
	 */
	Map<String, Object> MakeGetRequest(String requestUrl) {
		Gson gson = new Gson();
		System.out.println("Get Request Url =========================================== " + requestUrl);
		try {
			OkHttpClient client = getUnsafeOkHttpClient();// new OkHttpClient.Builder().build();
			Request request = new Request.Builder().url(requestUrl).build();

			Call call = client.newCall(request);
			Response response = call.execute();

			if (!response.isSuccessful())
				throw new Exception(
						"Server error (HTTP {0}: {1}). " + response.code() + " message : " + response.message());

			String responseBody = response.body().string();
			System.out.println("String Response =========================================== " + responseBody);

			Map<String, Object> objResponse = gson.fromJson(responseBody, new TypeToken<HashMap<String, Object>>() {
			}.getType());
			response.close();
			return objResponse;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * @param requestUrl
	 * @param requestData
	 * @param contentType
	 * @return
	 */
	Map<String, Object> MakePutRequest(String requestUrl, Map<String, Object> requestData, String contentType) {
		Gson gson = new Gson();
		System.out.println("Put Request Url =========================================== " + requestUrl);
		try {
			String json = gson.toJson(requestData);

			OkHttpClient client = getUnsafeOkHttpClient();// new OkHttpClient.Builder().build();
			Request request = new Request.Builder().url(requestUrl)
					.put(RequestBody.create(json, MediaType.parse(contentType))).build();

			Call call = client.newCall(request);
			Response response = call.execute();

			if (response.code() != 200)
				throw new Exception(
						"Server error (HTTP {0}: {1}). " + response.code() + " message : " + response.message());

			String responseBody = response.body().string();
			System.out.println("String Response =========================================== " + responseBody);

			Map<String, Object> objResponse = gson.fromJson(responseBody, new TypeToken<HashMap<String, Object>>() {
			}.getType());
			response.close();
			return objResponse;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * @param requestUrl
	 * @param requestData
	 * @param contentType
	 * @return
	 */
	Map<String, Object> MakePostRequest(String requestUrl, Map<String, Object> requestData, String contentType) {
		Gson gson = new Gson();
		System.out.println("Post Request Url =========================================== " + requestUrl);
		try {
			String json = gson.toJson(requestData);

			OkHttpClient client = getUnsafeOkHttpClient();// new OkHttpClient.Builder().build();
			Request request = new Request.Builder().url(requestUrl)
					.post(RequestBody.create(json, MediaType.parse(contentType))).build();

			Call call = client.newCall(request);
			Response response = call.execute();

			if (response.code() != 200)
				throw new Exception(
						"Server error (HTTP {0}: {1}). " + response.code() + " message : " + response.message());

			String responseBody = response.body().string();
			System.out.println("String Response =========================================== " + responseBody);

			JSONObject soapDatainJsonObject = XML.toJSONObject(responseBody);
			Map<String, Object> objResponse = null;
			if (soapDatainJsonObject.length() != 0) {
				System.out.println(soapDatainJsonObject);
				objResponse = gson.fromJson(soapDatainJsonObject.toString(), new TypeToken<HashMap<String, Object>>() {
				}.getType());
			} else {
				try {
					objResponse = gson.fromJson(responseBody, new TypeToken<HashMap<String, Object>>() {
					}.getType());
				} catch (JsonSyntaxException e) {
					objResponse = new HashMap<>();
					objResponse.put("response", responseBody);
				}
			}
			
			response.close();
			return objResponse;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	/**
	 * @param requestUrl
	 * @param requestData
	 * @param contentType
	 * @return
	 */
	Map<String, Object> MakeDeleteRequest(String requestUrl, Map<String, Object> requestData, String contentType) {
		Gson gson = new Gson();
		System.out.println("Delete Request Url =========================================== " + requestUrl);
		try {
			String json = gson.toJson(requestData);

			OkHttpClient client = getUnsafeOkHttpClient();// new OkHttpClient.Builder().build();
			
			Request request = new Request.Builder().url(requestUrl)
					.delete(RequestBody.create(json, MediaType.parse(contentType))).build();

			Call call = client.newCall(request);
			Response response = call.execute();

			if (response.code() != 200)
				throw new Exception(
						"Server error (HTTP {0}: {1}). " + response.code() + " message : " + response.message());

			String responseBody = response.body().string();
			System.out.println("String Response =========================================== " + responseBody);

			Map<String, Object> objResponse = gson.fromJson(responseBody, new TypeToken<HashMap<String, Object>>() {
			}.getType());
			response.close();
			return objResponse;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * @param requestUrl
	 * @param requestData
	 * @param contentType
	 * @return
	 */
	Map<String, Object> MakeDeleteRequest(String requestUrl, String requestData, String contentType) {
		Gson gson = new Gson();
		System.out.println("Delete Request Url =========================================== " + requestUrl);
//		"application/x-www-form-urlencoded"
		try {
			OkHttpClient client = getUnsafeOkHttpClient();// new OkHttpClient.Builder().build();
			Request request = new Request.Builder().url(requestUrl)
					.delete(RequestBody.create(requestData, MediaType.parse(contentType))).build();

			Call call = client.newCall(request);
			Response response = call.execute();

			if (response.code() != 200)
				throw new Exception(
						"Server error (HTTP {0}: {1}). " + response.code() + " message : " + response.message());

			String responseBody = response.body().string();
			System.out.println("String Response =========================================== " + responseBody);

			Map<String, Object> objResponse = gson.fromJson(responseBody, new TypeToken<HashMap<String, Object>>() {
			}.getType());
			response.close();
			return objResponse;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
