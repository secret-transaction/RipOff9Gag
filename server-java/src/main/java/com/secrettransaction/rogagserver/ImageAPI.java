package com.secrettransaction.rogagserver;

import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.secrettransaction.rogagserver.api.dto.GetUrlRequest;
import com.secrettransaction.rogagserver.api.dto.GetUrlResponse;
import com.secrettransaction.rogagserver.util.GCSSupportUtil;

@Api(name="image", version="v1", description="Rogag API for Images")
public class ImageAPI {
	
	private static final Logger log = Logger.getLogger(ImageAPI.class.getSimpleName());

	/**
	 * note: using POST instead of GET 'coz GET request sucks
	 * @param request
	 * @return
	 */
	@ApiMethod(name="get.url", path="url", httpMethod=HttpMethod.POST)
	public GetUrlResponse getUrl(GetUrlRequest request) {
		log.info(request.toString());
		
		String userId = request.getAuth() != null ? request.getAuth().getUserId() : "unknown";
		long expiration = System.currentTimeMillis() + 5 * 60 * 1000;
		
		String fileName = String.format("uploads/%s/%s_%s", userId, expiration, request.getFileName());
		
		//TODO: do timey wimey stuffs for a signed url here
		GCSSupportUtil.SignedUrlParams params = new GCSSupportUtil.SignedUrlParams();
		params.setContentType(request.getImageType());
		params.setFilename(fileName);
		params.setExpiration(expiration);
		
		GCSSupportUtil util = GCSSupportUtil.getInstance();
		GetUrlResponse response = new GetUrlResponse();
		
		try {
			String upload = util.generateSignedUrl(params);
			String download = util.generateServingUrl(fileName);
			response.setDownloadUrl(download);
			response.setUploadUrl(upload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
