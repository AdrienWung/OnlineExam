package online.exam.datacenter.model;

public class RandomInfo {

    private Long count;

    private String course;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
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
