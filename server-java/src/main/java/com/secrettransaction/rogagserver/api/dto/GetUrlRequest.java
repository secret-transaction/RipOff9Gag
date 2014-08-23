package com.secrettransaction.rogagserver.api.dto;

public class GetUrlRequest extends BaseRequest {

	private String fileName;
	private String imageType;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String toString() {
		return "GetUrlRequest [fileName=" + fileName + ", imageType=" + imageType + ", auth=" + getAuth() + "]";
	}

}
