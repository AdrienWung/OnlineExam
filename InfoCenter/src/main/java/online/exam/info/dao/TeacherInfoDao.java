package online.exam.info.dao;

import online.exam.info.entity.TeacherInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherInfoDao {
    @Insert("INSERT INTO teachermail(email,password,name) VALUES(#{email}, #{password}, #{name})")
    void insert(TeacherInfo teacherInfo);

    @Select("SELECT * FROM teachermail WHERE email = #{email} and password = #{password}")
    TeacherInfo getFromEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
