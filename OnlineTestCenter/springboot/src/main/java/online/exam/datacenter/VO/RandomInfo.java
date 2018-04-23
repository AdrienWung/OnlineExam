package online.exam.datacenter.VO;

public class RandomInfo {

    private long count;

    private String course;

    public RandomInfo(long count, String course) {
        this.count = count;
        this.course = course;
    }

    public RandomInfo() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RandomInfo{" +
                "count=" + count +
                ", course='" + course + '\'' +
                '}';
    }
}
