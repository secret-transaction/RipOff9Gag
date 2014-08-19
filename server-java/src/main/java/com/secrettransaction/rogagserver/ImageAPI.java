package com.secrettransaction.rogagserver;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.secrettransaction.rogagserver.api.dto.GetUrlRequest;
import com.secrettransaction.rogagserver.api.dto.GetUrlResponse;

@Api(name="image", version="v1", description="Rogag API for Images")
public class ImageAPI {

	@ApiMethod(name="get.url", path="url", httpMethod=HttpMethod.GET)
	public GetUrlResponse getUrl(GetUrlRequest request) {
		
		//TODO: do timey wimey stuffs for a signed url here
		
		GetUrlResponse response = new GetUrlResponse();
		response.setDownloadUrl("http://storage.googleapis.com/rogag-server.appspot.com/test_image.jpg");
		return response;
	}
}
