package online.exam.datacenter.jerseyservice;

public class EndpointResponse {
    private String status;
    private String path;

    public EndpointResponse(String status, String path) {
        this.status = status;
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Responnse{" +
                "status='" + status + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
