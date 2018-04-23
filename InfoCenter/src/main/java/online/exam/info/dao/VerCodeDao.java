package online.exam.info.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VerCodeDao {

    @Insert("INSERT INTO verCode(email,vercode)values(#{email},#{vercode})")
    void insert(@Param("email") String email, @Param("vercode") String vercode);

    @Update("update verCode set vercode=#{vercode} where email=#{email}")
    void update(@Param("email") String email, @Param("vercode") String vercode);

    @Select("select count(*) from verCode where email=#{email}")
    int hasVercode(@Param("email") String email);

    @Select("select verCode from verCode where email=#{email}")
    String getVercode(@Param("email") String email);
}
