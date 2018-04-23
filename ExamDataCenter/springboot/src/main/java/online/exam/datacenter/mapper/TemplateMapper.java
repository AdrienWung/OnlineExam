package online.exam.datacenter.mapper;

import online.exam.datacenter.model.Question;
import online.exam.datacenter.sqlprovide.TemplateSqlProvide;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TemplateMapper {

    @InsertProvider(type = TemplateSqlProvide.class, method = "createQuestion")
    void createQuestion(@Param("question") Question question);
}
