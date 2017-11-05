package tech.aoide.webserver.api;

public class ProcessRequest {

    private String data;
    private String lang;

    public ProcessRequest(String data, String lang) {
        this.data = data;
        this.lang = lang;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
