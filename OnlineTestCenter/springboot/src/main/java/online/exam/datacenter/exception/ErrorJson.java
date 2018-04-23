package online.exam.datacenter.exception;

public class ErrorJson {

    private String status;
    private String msg;

    public ErrorJson(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorJson{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

}
