package com.mg.userManagement.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class OldUserAuthenticator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	static class Request
	{
		String action;
		String username;
		String password;
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
	
	static class Response
	{
		String id;
		String name;
		String error;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		@Override
		public String toString() {
			return "Response [id=" + id + ", name=" + name + ", error=" + error + "]";
		}
		
		
	}
	private String sendPOST(String url,String data) throws IOException {
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection)obj.openConnection();
		//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(data.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println("Response is : "+response.toString());
			return response.toString();
		} else {
			System.out.println("POST request not worked");
			return null;
		}
	}

	public boolean isOldUser(String username, String password) throws IOException {
	System.out.println("start11");
		String url = "https://metagauss.com/external-checks.php";
		Request request = new Request();
		request.setAction("get_gauss_ai_token");
		request.setUsername(username);
		request.setPassword(password);
		String req = new Gson().toJson(request);
		System.out.println("start11");
		RestTemplate restTemplate = new RestTemplate();
		//Response response = restTemplate.postForObject("https://metagauss.com/external-checks.php", request, Response.class);
		String res = sendPOST(url, req);
		Response response = new Gson().fromJson(res, Response.class);
		String error = response.getError();
		System.out.println("URL ["+url+"] username ["+username+"] Password ["+password+"] Response ["+response+"]");
		logger.info("Response Error from the old site : "+error);
		if (error == null || error.isEmpty())
		{
			return true;
		}
		else  
		{
			return false;
		}

	}
}
