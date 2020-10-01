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
