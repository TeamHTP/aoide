package tech.aoide.webserver.api;

public class ProcessRequest {

    private String data;

    public ProcessRequest(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
