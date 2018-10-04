package com.spyapp.server.requestresponsemodel.response;

public class TestResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TestResponse(boolean success) {
        this.success = success;
    }
}
