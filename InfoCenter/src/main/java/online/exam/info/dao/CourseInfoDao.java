package online.exam.info.dao;

import online.exam.info.entity.CourseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseInfoDao {

    @Insert("INSERT INTO courseinfo(email,name)values(#{email},#{name})")
    void insert(CourseInfo info);

    @Select("select * from courseinfo where email=#{email}")
    List<CourseInfo> getCourseInfo(String email);
}
