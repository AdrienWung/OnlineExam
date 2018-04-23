package online.exam.datacenter.service.impl;

import online.exam.datacenter.mapper.TemplateMapper;
import online.exam.datacenter.model.Question;
import online.exam.datacenter.service.TemplateService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SK on 2017/11/30.
 */

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateMapper templateMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Override
    @Transactional(readOnly = false)
    public void importFile(InputStream file, String course) throws IOException {
        XSSFSheet xssfSheet = new XSSFWorkbook(file).getSheetAt(0);
        //第0行为标题
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            saveQuestions(xssfRow, course);
        }
    }

    private void saveQuestions(XSSFRow xssfRow, String course_id) {
        Question question = new Question();
        question.setCourse(course_id);
        question.setDescription(getCellValue(xssfRow.getCell(0)));
        question.setCategory(getCellValue(xssfRow.getCell(5)));
        question.setAnswer(getCellValue(xssfRow.getCell(6)));
        List<String> selections = new ArrayList<>();
        String[] choices = new String[]{"A","B","C","D"};
        for (int i=1;i<5;i++){
            selections.add((choices[i-1]+"."+getCellValue(xssfRow.getCell(i)).replace(" ","")));
        }
        question.setSelections(String.join(";", selections));
        templateMapper.createQuestion(question);
    }

    private String getCellValue(XSSFCell cell) {
        if (cell != null) {
            if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                return new BigDecimal(cell.getNumericCellValue()).toPlainString();
            } else {
                return String.valueOf(cell.getStringCellValue());
            }
        } else {
            return "";
        }
    }


}
