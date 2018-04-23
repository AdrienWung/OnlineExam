package online.exam.datacenter.exception;

public class OperationException extends Exception {
    private String msg;

    public OperationException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
