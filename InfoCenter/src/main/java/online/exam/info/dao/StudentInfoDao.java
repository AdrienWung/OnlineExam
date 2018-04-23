package online.exam.info.dao;

import online.exam.info.entity.StudentInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentInfoDao {

    @Insert("INSERT INTO studentmail(email,password,name,className,grade) VALUES(#{email}, #{password}, #{name},#{className}, #{grade})")
    void insert(StudentInfo studentInfo);

    @Select("SELECT * FROM studentmail WHERE email = #{email} and password = #{password}")
    StudentInfo getFromEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
