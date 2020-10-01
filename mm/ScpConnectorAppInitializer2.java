package com.incture.cherrywork.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.incture.cherrywork.AppConstants;
import com.incture.cherrywork.dto.FileDetailsDto;
import com.incture.cherrywork.dto.ResponseDto;

@Service
public class GithubService {

	public ResponseDto create(FileDetailsDto fileDetailsDto) {

		ResponseDto responseDto = new ResponseDto();

		try {

			responseDto.setStatus(true);
			responseDto.setStatusCode(200);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPut httpPut = new HttpPut(AppConstants.BASE_URL + fileDetailsDto.getOwnerId() + "/"
					+ fileDetailsDto.getRepo() + "/contents/" + fileDetailsDto.getPath());

			StringEntity data = new StringEntity(formJsonBody(fileDetailsDto), "UTF-8");
			httpPut.setEntity(data);
			httpPut.addHeader(AppConstants.ACCEPT, AppConstants.CONTENT_TYPE);
			httpPut.addHeader(AppConstants.AUTHORIZATION, "bearer " + fileDetailsDto.getToken());
			HttpResponse httpResponse = httpClient.execute(httpPut);

			String resString = EntityUtils.toString(httpResponse.getEntity());
			JSONObject responseObj = new JSONObject(resString);
			responseDto.setMessage(
					"Successfully updated with id : " + responseObj.optJSONObject("content").optString("sha"));
			httpClient.close();

		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}

		return responseDto;

	}

	private String formJsonBody(FileDetailsDto fileDetailsDto) {

		JSONObject content = new JSONObject();
		if (fileDetailsDto.getFileId() == null) {
			content.put("sha", "");
		} else {
			content.put("sha", fileDetailsDto.getFileId());
		}

		content.put("message", fileDetailsDto.getCommitMessage());
		content.put("content", fileDetailsDto.getContent());

		return content.toString();
	}

}
