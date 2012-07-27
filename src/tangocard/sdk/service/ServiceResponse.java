package tangocard.sdk.service;

import tangocard.sdk.response.BaseResponse;
import tangocard.sdk.response.ServiceResponseEnum;

public class ServiceResponse<T extends BaseResponse> {
    private ServiceResponseEnum _responseType;
    private BaseResponse _response;
    
    public ServiceResponseEnum getResponseType() {
    	return this._responseType;
    }
    public void setResponseType(ServiceResponseEnum responseType) {
    	this._responseType = responseType;
    }
    
    public BaseResponse getResponse() {
    	return this._response;
    }
    public void setResponse(BaseResponse response) {
    	this._response = response;
    }
}


