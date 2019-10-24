package com.capgemini.jtp.common.poi;

import com.capgemini.jtp.entity.*;
import com.capgemini.jtp.vo.employee.request.StudyDeclarationVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogVo;
import com.capgemini.jtp.vo.employee.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Description   :  员工信息导入导出Excel工具类
 * @ Author        :  HASI
 * @ CreateDate    :  2019/10/16 0016 19:33
 * @ UpdateUser    :  HASI
 * @ UpdateDate    :  2019/10/16 0016 19:33
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
@Slf4j
public class PoiUtils {

    public static ResponseEntity<byte[]> exportEmp2Excel(List<EmployeeVo> emps) {
        HttpHeaders headers = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("员工信息");
            //3.2设置文档管理员
            dsi.setManager("JTP Operation Team");
            //3.3设置组织机构
            dsi.setCompany("Capgemini");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("员工信息表");
            //4.2.设置文档标题
            si.setTitle("员工信息");
            //4.3 设置文档作者
            si.setAuthor("Capgemini");
            //4.4设置文档备注
            si.setComments("");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("JTP员工信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 12 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 5 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 12 * 256);
            sheet.setColumnWidth(5, 20 * 256);
            sheet.setColumnWidth(6, 20 * 256);
            sheet.setColumnWidth(7, 20 * 256);
            sheet.setColumnWidth(8, 5 * 256);
            sheet.setColumnWidth(9, 20 * 256);
            sheet.setColumnWidth(10, 15 * 256);
            sheet.setColumnWidth(11, 10 * 256);
            sheet.setColumnWidth(12, 20 * 256);
            sheet.setColumnWidth(13, 20 * 256);
            sheet.setColumnWidth(14, 20 * 256);
            sheet.setColumnWidth(15, 20 * 256);
            sheet.setColumnWidth(16, 20 * 256);
            sheet.setColumnWidth(17, 20 * 256);
            sheet.setColumnWidth(18, 20 * 256);
            sheet.setColumnWidth(19, 20 * 256);
            sheet.setColumnWidth(20, 20 * 256);
            sheet.setColumnWidth(21, 20 * 256);
            sheet.setColumnWidth(22, 20 * 256);
            sheet.setColumnWidth(23, 20 * 256);
            sheet.setColumnWidth(24, 20 * 256);
            sheet.setColumnWidth(25, 20 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell_0 = headerRow.createCell(0);
            cell_0.setCellValue("Empl ID");
            cell_0.setCellStyle(headerStyle);
            HSSFCell cell_1 = headerRow.createCell(1);
            cell_1.setCellValue("Chinese Name");
            cell_1.setCellStyle(headerStyle);
            HSSFCell cell_2 = headerRow.createCell(2);
            cell_2.setCellValue("Gender");
            cell_2.setCellStyle(headerStyle);
            HSSFCell cell_3 = headerRow.createCell(3);
            cell_3.setCellValue("Grade");
            cell_3.setCellStyle(headerStyle);
            HSSFCell cell_4 = headerRow.createCell(4);
            cell_4.setCellValue("Hire Date");
            cell_4.setCellStyle(headerStyle);
            HSSFCell cell_5 = headerRow.createCell(5);
            cell_5.setCellValue("Department");
            cell_5.setCellStyle(headerStyle);
            HSSFCell cell_6 = headerRow.createCell(6);
            cell_6.setCellValue("Legal Entity");
            cell_6.setCellStyle(headerStyle);
            HSSFCell cell_7 = headerRow.createCell(7);
            cell_7.setCellValue("Working Location");
            cell_7.setCellStyle(headerStyle);
            HSSFCell cell_8 = headerRow.createCell(8);
            cell_8.setCellValue("Mobile Phone");
            cell_8.setCellStyle(headerStyle);
            HSSFCell cell_9 = headerRow.createCell(9);
            cell_9.setCellValue("Email");
            cell_9.setCellStyle(headerStyle);
            HSSFCell cell_10 = headerRow.createCell(10);
            cell_10.setCellValue("Training Batch Starting Date");
            cell_10.setCellStyle(headerStyle);
            HSSFCell cell_11 = headerRow.createCell(11);
            cell_11.setCellValue("School");
            cell_11.setCellStyle(headerStyle);
            HSSFCell cell_12 = headerRow.createCell(12);
            cell_12.setCellValue("Major");
            cell_12.setCellStyle(headerStyle);
            HSSFCell cell_13 = headerRow.createCell(13);
            cell_13.setCellValue("Degree");
            cell_13.setCellStyle(headerStyle);
            HSSFCell cell_14 = headerRow.createCell(14);
            cell_14.setCellValue("Graduation Year");
            cell_14.setCellStyle(headerStyle);
            HSSFCell cell_15 = headerRow.createCell(15);
            cell_15.setCellValue("CST");
            cell_15.setCellStyle(headerStyle);
            HSSFCell cell_16 = headerRow.createCell(16);
            cell_16.setCellValue("SUB_CST");
            cell_16.setCellStyle(headerStyle);
            HSSFCell cell_17 = headerRow.createCell(17);
            cell_17.setCellValue("Skill Group");
            cell_17.setCellStyle(headerStyle);
            HSSFCell cell_18 = headerRow.createCell(18);
            cell_18.setCellValue("Skill Sets");
            cell_18.setCellStyle(headerStyle);
            HSSFCell cell_19 = headerRow.createCell(19);
            cell_19.setCellValue("Leave Date");
            cell_19.setCellStyle(headerStyle);
            HSSFCell cell_20 = headerRow.createCell(20);
            cell_20.setCellValue("Termination Type");
            cell_20.setCellStyle(headerStyle);
            HSSFCell cell_21 = headerRow.createCell(21);
            cell_21.setCellValue("Termination Reason");
            cell_21.setCellStyle(headerStyle);
            HSSFCell cell_22 = headerRow.createCell(22);
            cell_22.setCellValue("Exit interview comments");
            cell_22.setCellStyle(headerStyle);
            HSSFCell cell_23 = headerRow.createCell(23);
            cell_23.setCellValue("Leaving Account");
            cell_23.setCellStyle(headerStyle);
            HSSFCell cell_24 = headerRow.createCell(24);
            cell_24.setCellValue("曾用Employee ID");
            cell_24.setCellStyle(headerStyle);
            HSSFCell cell_25 = headerRow.createCell(25);
            cell_25.setCellValue("Birthday");
            cell_25.setCellStyle(headerStyle);

            //6.插入数据
            for (int i = 0; i < emps.size(); i++) {
                try {
                    HSSFRow row = sheet.createRow(i + 1);
                    EmployeeVo emp = emps.get(i);
                    if (emp.getEmpId() != null) {
                        row.createCell(0).setCellValue(emp.getEmpId());
                    }
                    if (emp.getChineseName() != null) {
                        row.createCell(1).setCellValue(emp.getChineseName());
                    }
                    if (emp.getGender() != null) {
                        row.createCell(2).setCellValue(emp.getGender());
                    }
                    if (emp.getGrade() != null) {
                        row.createCell(3).setCellValue(emp.getGrade());
                    }
                    if (emp.getHireDate() != null) {
                        row.createCell(4).setCellValue(sdf.format(emp.getHireDate()));
                    }
                    if (emp.getDepartment() != null) {
                        row.createCell(5).setCellValue(emp.getDepartment());
                    }
                    if (emp.getLegalEntity() != null) {
                        row.createCell(6).setCellValue(emp.getLegalEntity());
                    }
                    if (emp.getWorkingLocation() != null) {
                        row.createCell(7).setCellValue(emp.getWorkingLocation());
                    }
                    if (emp.getPhone() != null) {
                        row.createCell(8).setCellValue(emp.getPhone());
                    }
                    if (emp.getEmail() != null) {
                        row.createCell(9).setCellValue(emp.getEmail());
                    }
                    if (emp.getTeachingStartDate() != null) {
                        row.createCell(10).setCellValue(sdf.format(emp.getTeachingStartDate()));
                    }
                    if (emp.getSchool() != null) {
                        row.createCell(11).setCellValue(emp.getSchool());
                    }
                    if (emp.getMajor() != null) {
                        row.createCell(12).setCellValue(emp.getMajor());
                    }
                    if (emp.getDegree() != null) {
                        row.createCell(13).setCellValue(emp.getDegree());
                    }
                    if (emp.getGraduationYear() != null) {
                        row.createCell(14).setCellValue(emp.getGraduationYear());
                    }
                    if (emp.getCst() != null) {
                        row.createCell(15).setCellValue(emp.getCst());
                    }
                    if (emp.getSubCst() != null) {
                        row.createCell(16).setCellValue(emp.getSubCst());
                    }
                    if (emp.getSkillGroup() != null) {
                        row.createCell(17).setCellValue(emp.getSkillGroup());
                    }
                    if (emp.getSkillSets() != null) {
                        row.createCell(18).setCellValue(emp.getSkillSets());
                    }
                    if (emp.getLeaveDate() != null) {
                        row.createCell(19).setCellValue(sdf.format(emp.getLeaveDate()));
                    }
                    if (emp.getTerminationType() != null) {
                        row.createCell(20).setCellValue(emp.getTerminationType());
                    }
                    if (emp.getTerminationReason() != null) {
                        row.createCell(21).setCellValue(emp.getTerminationReason());
                    }
                    if (emp.getExitComments() != null) {
                        row.createCell(22).setCellValue(emp.getExitComments());
                    }
                    if (emp.getLeavingAccount() != null) {
                        row.createCell(23).setCellValue(emp.getLeavingAccount());
                    }
                    if (emp.getFormerId() != null) {
                        row.createCell(24).setCellValue(emp.getFormerId());
                    }
                    if (emp.getBirthday() != null) {
                        row.createCell(25).setCellValue(sdf.format(emp.getBirthday()));
                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("JTP Employee.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static ResponseEntity<byte[]> exportTalk2Excel(List<HeadmasterTalkVo> talks){
        HttpHeaders headers = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("员工获奖信息");
            //3.2设置文档管理员
            dsi.setManager("JTP Operation Team");
            //3.3设置组织机构
            dsi.setCompany("Capgemini");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("员工获奖信息表");
            //4.2.设置文档标题
            si.setTitle("员工获奖信息");
            //4.3 设置文档作者
            si.setAuthor("Capgemini");
            //4.4设置文档备注
            si.setComments("");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("JTP员工获奖信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 12 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 10 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell_0 = headerRow.createCell(0);
            cell_0.setCellValue("Empl ID");
            cell_0.setCellStyle(headerStyle);
            HSSFCell cell_1 = headerRow.createCell(1);
            cell_1.setCellValue("TalkType");
            cell_1.setCellStyle(headerStyle);
            HSSFCell cell_2 = headerRow.createCell(2);
            cell_2.setCellValue("TalkContent");
            cell_2.setCellStyle(headerStyle);
            HSSFCell cell_3 = headerRow.createCell(3);
            cell_3.setCellValue("Headmaster");
            cell_3.setCellStyle(headerStyle);



            //6.插入数据
            for (int i = 0; i < talks.size(); i++) {
                try {
                    HSSFRow row = sheet.createRow(i + 1);
                    HeadmasterTalkVo talk = talks.get(i);
                    if (talk.getEmpId() != null) {
                        row.createCell(0).setCellValue(talk.getEmpId());
                    }
                    if (talk.getTalkType() != null) {
                        row.createCell(1).setCellValue(talk.getTalkType());
                    }
                    if (talk.getTalkContent() != null) {
                        row.createCell(2).setCellValue(talk.getTalkContent());
                    }
                    if (talk.getHeadMaster() != null) {
                        row.createCell(3).setCellValue(talk.getHeadMaster());
                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("JTP HeadmasterTalk.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static ResponseEntity<byte[]> exportPro2Excel(List<ProjectExperienceVo> pros){
        HttpHeaders headers = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("员工项目经历信息");
            //3.2设置文档管理员
            dsi.setManager("JTP Operation Team");
            //3.3设置组织机构
            dsi.setCompany("Capgemini");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("员工项目经历信息表");
            //4.2.设置文档标题
            si.setTitle("员工项目经历信息");
            //4.3 设置文档作者
            si.setAuthor("Capgemini");
            //4.4设置文档备注
            si.setComments("");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("JTP员工项目经历信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 12 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 10 * 256);
            sheet.setColumnWidth(5, 10 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell_0 = headerRow.createCell(0);
            cell_0.setCellValue("Empl ID");
            cell_0.setCellStyle(headerStyle);
            HSSFCell cell_1 = headerRow.createCell(1);
            cell_1.setCellValue("Account");
            cell_1.setCellStyle(headerStyle);
            HSSFCell cell_2 = headerRow.createCell(2);
            cell_2.setCellValue("Project Name");
            cell_2.setCellStyle(headerStyle);
            HSSFCell cell_3 = headerRow.createCell(3);
            cell_3.setCellValue("Project Manager");
            cell_3.setCellStyle(headerStyle);
            HSSFCell cell_4 = headerRow.createCell(4);
            cell_4.setCellValue("Join Time");
            cell_4.setCellStyle(headerStyle);
            HSSFCell cell_5 = headerRow.createCell(5);
            cell_5.setCellValue("End Time");
            cell_5.setCellStyle(headerStyle);

            //6.插入数据
            for (int i = 0; i < pros.size(); i++) {
                try {
                    HSSFRow row = sheet.createRow(i + 1);
                    ProjectExperienceVo pro = pros.get(i);
                    if (pro.getEmpId() != null) {
                        row.createCell(0).setCellValue(pro.getEmpId());
                    }
                    if (pro.getAccount() != null) {
                        row.createCell(1).setCellValue(pro.getAccount());
                    }
                    if (pro.getProjectName() != null) {
                        row.createCell(2).setCellValue(pro.getProjectName());
                    }
                    if (pro.getProjectManager() != null) {
                        row.createCell(3).setCellValue(pro.getProjectManager());
                    }
                    if (pro.getJoinTime() != null) {
                        row.createCell(4).setCellValue(sdf.format(pro.getJoinTime()));
                    }
                    if (pro.getEndTime() != null) {
                        row.createCell(5).setCellValue(sdf.format(pro.getEndTime()));
                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("JTP Project.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static ResponseEntity<byte[]> exportPrize2Excel(List<EmpPrizeVo> prizes){
        HttpHeaders headers = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("员工获奖信息信息");
            //3.2设置文档管理员
            dsi.setManager("JTP Operation Team");
            //3.3设置组织机构
            dsi.setCompany("Capgemini");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("员工获奖信息表");
            //4.2.设置文档标题
            si.setTitle("员工获奖信息");
            //4.3 设置文档作者
            si.setAuthor("Capgemini");
            //4.4设置文档备注
            si.setComments("");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("JTP员工获奖信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 12 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 10 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell_0 = headerRow.createCell(0);
            cell_0.setCellValue("Empl ID");
            cell_0.setCellStyle(headerStyle);
            HSSFCell cell_1 = headerRow.createCell(1);
            cell_1.setCellValue("PrizeType");
            cell_1.setCellStyle(headerStyle);
            HSSFCell cell_2 = headerRow.createCell(2);
            cell_2.setCellValue("PrizeDetail");
            cell_2.setCellStyle(headerStyle);

            //6.插入数据
            for (int i = 0; i < prizes.size(); i++) {
                try {
                    HSSFRow row = sheet.createRow(i + 1);
                   EmpPrizeVo prize = prizes.get(i);
                    if (prize.getEmpId() != null) {
                        row.createCell(0).setCellValue(prize.getEmpId());
                    }
                    if (prize.getPrizeType() != null) {
                        row.createCell(1).setCellValue(prize.getPrizeType());
                    }
                    if (prize.getPrizeDetail() != null) {
                        row.createCell(2).setCellValue(prize.getPrizeDetail());
                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("JTP Prize.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static ResponseEntity<byte[]> exportGrow2Excel(List<GrowingExperienceVo> grows){
        HttpHeaders headers = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("员工培训经历信息");
            //3.2设置文档管理员
            dsi.setManager("JTP Operation Team");
            //3.3设置组织机构
            dsi.setCompany("Capgemini");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("员工培训经历信息表");
            //4.2.设置文档标题
            si.setTitle("员工培训经历信息");
            //4.3 设置文档作者
            si.setAuthor("Capgemini");
            //4.4设置文档备注
            si.setComments("");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("JTP培训经历信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 12 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 10 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell_0 = headerRow.createCell(0);
            cell_0.setCellValue("Empl ID");
            cell_0.setCellStyle(headerStyle);
            HSSFCell cell_2 = headerRow.createCell(1);
            cell_2.setCellValue("GrowingType");
            cell_2.setCellStyle(headerStyle);
            HSSFCell cell_3 = headerRow.createCell(2);
            cell_3.setCellValue("GrowingDetail");
            cell_3.setCellStyle(headerStyle);


            //6.插入数据
            for (int i = 0; i < grows.size(); i++) {
                try {
                    HSSFRow row = sheet.createRow(i + 1);
                    GrowingExperienceVo grow = grows.get(i);
                    if (grow.getEmpId() != null) {
                        row.createCell(0).setCellValue(grow.getEmpId());
                    }
                    if (grow.getGrowingType() != null) {
                        row.createCell(1).setCellValue(grow.getGrowingType());
                    }
                    if (grow.getGrowingDetail() != null) {
                        row.createCell(2).setCellValue(grow.getGrowingDetail());
                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("JTP Training.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }




    public static List<Employee> importEmp2List(MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Employee> emps = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                Employee employee;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int LastCellNum = row.getLastCellNum();
                    employee = new Employee();
                    System.out.println(LastCellNum);
                    for (int k = 0; k < LastCellNum; k++) {
                        System.out.println("++++++++++++++++++++++++++++++++++++"+k);
                        HSSFCell cell = row.getCell(k);
                        if (cell == null) {
                            continue;
                        }
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        switch (k) {
                            case 0:
                                employee.setEmpId(Long.parseLong(cellValue));
                                break;
                            case 1:
                                employee.setChineseName(cellValue);
                                break;
                            case 2:
                                employee.setGender(cellValue);
                                break;
                            case 3:
                                employee.setGrade(cellValue);
                                break;
                            case 4:
                                employee.setHireDate(sdf.parse(cellValue));
                                break;
                            case 5:
                                employee.setDepartment(cellValue);
                                break;
                            case 6:
                                employee.setLegalEntity(cellValue);
                                break;
                            case 7:
                                employee.setWorkingLocation(cellValue);
                                break;
                            case 8:
                                employee.setPhone(cellValue);
                                break;
                            case 9:
                                employee.setEmail(cellValue);
                                break;
                            case 10:
                                employee.setTeachingStartDate(sdf.parse(cellValue));
                                break;
                            case 11:
                                employee.setSchool(cellValue);
                                break;
                            case 12:
                                employee.setMajor(cellValue);
                                break;
                            case 13:
                                employee.setDegree(cellValue);
                                break;
                            case 14:
                                employee.setGraduationYear(cellValue);
                                break;
                            case 15:
                                employee.setCst(cellValue);
                                break;
                            case 16:
                                employee.setSubCst(cellValue);
                                break;
                            case 17:
                                employee.setSkillGroup(cellValue);
                                break;
                            case 18:
                                employee.setSkillSets(cellValue);
                                break;
                            case 19:
                                employee.setLeaveDate(sdf.parse(cellValue));
                                break;
                            case 20:
                                employee.setTerminationType(cellValue);
                                break;
                            case 21:
                                employee.setTerminationReason(cellValue);
                                break;
                            case 22:
                                employee.setExitComments(cellValue);
                                break;
                            case 23:
                                employee.setLeavingAccount(cellValue);
                                break;
                            case 24:
                                employee.setFormerId(cellValue);
                                break;
                            case 25:
                                employee.setBirthday(sdf.parse(cellValue));
                                System.out.println(employee.getBirthday());
                        }
                    }
                    emps.add(employee);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return emps;
    }

    public static List<HeadmasterTalk> importTalk2List(MultipartFile file) {
        List<HeadmasterTalk> talks= new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                HeadmasterTalk headmasterTalk;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int LastCellNum = row.getLastCellNum();
                    headmasterTalk = new HeadmasterTalk();
                    for (int k = 0; k < LastCellNum; k++) {
                        HSSFCell cell = row.getCell(k);
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        switch (k) {
                            case 0:
                                headmasterTalk.setEmpId(Long.parseLong(cellValue));
                                break;
                            case 1:
                                headmasterTalk.setTalkType(cellValue);
                                break;
                            case 2:
                                headmasterTalk.setTalkContent(cellValue);
                                break;
                            case 3:
                                headmasterTalk.setHeadMaster(cellValue);
                                break;
                        }
                    }
                   talks.add(headmasterTalk);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return talks;
    }

    public static List<EmployeePrize> importPrize2List(MultipartFile file) {
        List<EmployeePrize> prizes = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                EmployeePrize employeePrize;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int LastCellNum = row.getLastCellNum();
                    employeePrize = new EmployeePrize();
                    for (int k = 0; k < LastCellNum; k++) {
                        HSSFCell cell = row.getCell(k);
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        switch (k) {
                            case 0:
                                employeePrize.setEmpId(Long.parseLong(cellValue));
                                break;
                            case 1:
                                employeePrize.setPrizeType(cellValue);
                                break;
                            case 2:
                                employeePrize.setPrizeDetail(cellValue);
                                break;
                        }
                    }
                    prizes.add(employeePrize);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return prizes;
    }

    public static List<GrowingExperience> importGrow2List(MultipartFile file) {
        List<GrowingExperience> grows = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                GrowingExperience growingExperience;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int LastCellNum = row.getLastCellNum();
                    growingExperience = new GrowingExperience();
                    for (int k = 0; k < LastCellNum; k++) {
                        HSSFCell cell = row.getCell(k);
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        switch (k) {
                            case 0:
                                growingExperience.setEmpId(Long.parseLong(cellValue));
                                break;
                            case 1:
                                growingExperience.setGrowingType(cellValue);
                                break;
                            case 2:
                                growingExperience.setGrowingDetail(cellValue);
                                break;
                        }
                    }
                    grows.add(growingExperience);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return grows;
    }

        public static List<ProjectExperience> importPro2List(MultipartFile file) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<ProjectExperience> pros= new ArrayList<>();
            try {
                HSSFWorkbook workbook =
                        new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
                int numberOfSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numberOfSheets; i++) {
                    HSSFSheet sheet = workbook.getSheetAt(i);
                    int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                    ProjectExperience projectExperience;
                    for (int j = 0; j < physicalNumberOfRows; j++) {
                        if (j == 0) {
                            continue;//标题行
                        }
                        HSSFRow row = sheet.getRow(j);
                        if (row == null) {
                            continue;//没数据
                        }
                        int LastCellNum = row.getLastCellNum();
                        projectExperience = new ProjectExperience();
                        for (int k = 0; k < LastCellNum; k++) {
                            HSSFCell cell = row.getCell(k);
                            cell.setCellType(CellType.STRING);
                            String cellValue = cell.getStringCellValue();
                            switch (k) {
                                case 0:
                                    projectExperience.setEmpId(Long.parseLong(cellValue));
                                    break;
                                case 1:
                                    projectExperience.setAccount(cellValue);
                                    break;
                                case 2:
                                    projectExperience.setProjectName(cellValue);
                                    break;
                                case 3:
                                    projectExperience.setProjectManager(cellValue);
                                    break;
                                case 4:
                                    projectExperience.setJoinTime(sdf.parse(cellValue));
                                    break;
                                case 5:
                                    projectExperience.setEndTime(sdf.parse(cellValue));
                                    break;
                            }
                        }
                        pros.add(projectExperience);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return pros;
    }


    public static ResponseEntity<byte[]> exportStudyDeclaration2Excel(List<StudyDeclarationVo> studyDeclarationVos) {
        HttpHeaders headers = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("员工自主申报信息");
            //3.2设置文档管理员
            dsi.setManager("JTP Operation Team");
            //3.3设置组织机构
            dsi.setCompany("Capgemini");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("员工自主申报信息表");
            //4.2.设置文档标题
            si.setTitle("员工自主申报信息");
            //4.3 设置文档作者
            si.setAuthor("Capgemini");
            //4.4设置文档备注
            si.setComments("");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("JTP员工自主申报信息");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度

            for (int i = 0; i < 13; i++) {
                sheet.setColumnWidth(i, 10 * 256);
            }
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);

            HSSFCell cell_0 = headerRow.createCell(0);
            cell_0.setCellValue("员工ID");
            cell_0.setCellStyle(headerStyle);

            HSSFCell cell_1 = headerRow.createCell(1);
            cell_1.setCellValue("员工姓名");
            cell_1.setCellStyle(headerStyle);

            HSSFCell cell_2 = headerRow.createCell(2);
            cell_2.setCellValue("EF初始级别");
            cell_2.setCellStyle(headerStyle);

            HSSFCell cell_3 = headerRow.createCell(3);
            cell_3.setCellValue("EF应达级别");
            cell_3.setCellStyle(headerStyle);

            HSSFCell cell_4 = headerRow.createCell(4);
            cell_4.setCellValue("EFYTD级别");
            cell_4.setCellStyle(headerStyle);

            HSSFCell cell_5 = headerRow.createCell(5);
            cell_5.setCellValue("EF小时数");
            cell_5.setCellStyle(headerStyle);

            HSSFCell cell_6 = headerRow.createCell(6);
            cell_6.setCellValue("百词斩打卡数");
            cell_6.setCellStyle(headerStyle);

            HSSFCell cell_7 = headerRow.createCell(7);
            cell_7.setCellValue("视频打卡数");
            cell_7.setCellStyle(headerStyle);

            HSSFCell cell_8 = headerRow.createCell(8);
            cell_8.setCellValue("Ted参赛数");
            cell_8.setCellStyle(headerStyle);

            HSSFCell cell_9 = headerRow.createCell(9);
            cell_9.setCellValue("学习分享会次数");
            cell_9.setCellStyle(headerStyle);

            HSSFCell cell_10 = headerRow.createCell(10);
            cell_10.setCellValue("不合格项");
            cell_10.setCellStyle(headerStyle);

            HSSFCell cell_11 = headerRow.createCell(11);
            cell_11.setCellValue("打卡日期");
            cell_11.setCellStyle(headerStyle);
            HSSFCell cell_12 = headerRow.createCell(12);
            cell_12.setCellValue("备注");
            cell_12.setCellStyle(headerStyle);
            //6.插入数据
            for (int i = 0; i < studyDeclarationVos.size(); i++) {
                try {
                    HSSFRow row = sheet.createRow(i + 1);
                    StudyDeclarationVo studyDeclareVo = studyDeclarationVos.get(i);


                    if (studyDeclareVo.getEmpId() != null) {
                        row.createCell(0).setCellValue(studyDeclareVo.getEmpId());
                    }
                    if (studyDeclareVo.getChineseName() != null) {
                        row.createCell(1).setCellValue(studyDeclareVo.getChineseName());
                    }
                    if (studyDeclareVo.getEFcslevel() != null) {
                        row.createCell(2).setCellValue(studyDeclareVo.getEFcslevel());
                    }
                    if (studyDeclareVo.getEFydlevel() != null) {
                        row.createCell(3).setCellValue(studyDeclareVo.getEFydlevel());
                    }
                    if (studyDeclareVo.getEFYTDlevel() != null) {
                        row.createCell(4).setCellValue(studyDeclareVo.getEFYTDlevel());
                    }

                    if (studyDeclareVo.getEFhours() != null) {
                        row.createCell(5).setCellValue(studyDeclareVo.getEFhours());
                    }
                    if (studyDeclareVo.getBCZhours() != null) {
                        row.createCell(6).setCellValue(studyDeclareVo.getBCZhours());
                    }
                    if (studyDeclareVo.getVideohours() != null) {
                        row.createCell(7).setCellValue(studyDeclareVo.getVideohours());
                    }
                    if (studyDeclareVo.getTEDtimes() != null) {
                        row.createCell(8).setCellValue(studyDeclareVo.getTEDtimes());
                    }
                    if (studyDeclareVo.getSharetimes() != null) {
                        row.createCell(9).setCellValue(studyDeclareVo.getSharetimes());
                    }
                    if (studyDeclareVo.getUnqualified() != null) {
                        row.createCell(10).setCellValue(studyDeclareVo.getUnqualified());
                    }

                    if (studyDeclareVo.getCreateTime() != null) {
                        row.createCell(11).setCellValue(sdf.format(studyDeclareVo.getCreateTime()));
                    }

                    if (studyDeclareVo.getNote() != null) {
                        row.createCell(12).setCellValue(studyDeclareVo.getNote());
                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
            }

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("JTP StudyDeclare.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static ResponseEntity<byte[]> exportTeamChangeLogExcel(List<TeamChangeLogVo> teamChangeLogVos){
        HttpHeaders headers = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("团队信息变更记录");
            //3.2设置文档管理员
            dsi.setManager("JTP Operation Team");
            //3.3设置组织机构
            dsi.setCompany("Capgemini");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("团队信息变更记录表");
            //4.2.设置文档标题
            si.setTitle("团队信息变更记录");
            //4.3 设置文档作者
            si.setAuthor("Capgemini");
            //4.4设置文档备注
            si.setComments("");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("团队信息变更记录表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 12 * 256);
            sheet.setColumnWidth(1, 10 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 10 * 256);
            sheet.setColumnWidth(5, 10 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell_0 = headerRow.createCell(0);
            cell_0.setCellValue("员工ID");
            cell_0.setCellStyle(headerStyle);
            HSSFCell cell_1 = headerRow.createCell(1);
            cell_1.setCellValue("员工姓名");
            cell_1.setCellStyle(headerStyle);

            HSSFCell cell_2 = headerRow.createCell(2);
            cell_2.setCellValue("原团队");
            cell_2.setCellStyle(headerStyle);

            HSSFCell cell_3 = headerRow.createCell(3);
            cell_3.setCellValue("原子团队");
            cell_3.setCellStyle(headerStyle);

            HSSFCell cell_4 = headerRow.createCell(4);
            cell_4.setCellValue("现团队");
            cell_4.setCellStyle(headerStyle);

            HSSFCell cell_5 = headerRow.createCell(5);
            cell_5.setCellValue("现子团队");
            cell_5.setCellStyle(headerStyle);




            //6.插入数据
            for (int i = 0; i < teamChangeLogVos.size(); i++) {
                try {
                    HSSFRow row = sheet.createRow(i + 1);
                    TeamChangeLogVo teamChangeLogVo = teamChangeLogVos.get(i);
                    if (teamChangeLogVo.getEmpId() != null) {
                        row.createCell(0).setCellValue(teamChangeLogVo.getEmpId());
                    }

                    if (teamChangeLogVo.getChineseName() != null) {
                        row.createCell(1).setCellValue(teamChangeLogVo.getChineseName());
                    }

                    if (teamChangeLogVo.getNowCst() != null) {
                        row.createCell(2).setCellValue(teamChangeLogVo.getNowCst());
                    }
                    if (teamChangeLogVo.getNowCst() != null) {
                        row.createCell(3).setCellValue(teamChangeLogVo.getNowSubCst());
                    }

                    if (teamChangeLogVo.getNewCst() != null) {
                        row.createCell(4).setCellValue(teamChangeLogVo.getNewCst());
                    }

                    if (teamChangeLogVo.getNewSubCst() != null) {
                        row.createCell(5).setCellValue(teamChangeLogVo.getNewSubCst());
                    }




                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("JTP TeamChangeRecord.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }



    public static List<StudyDeclaration> importStudyDeclarationList(MultipartFile file) {
        List<StudyDeclaration> grows = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                StudyDeclaration studyDeclaration;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int LastCellNum = row.getLastCellNum();
                    studyDeclaration = new StudyDeclaration();
                    for (int k = 0; k < LastCellNum; k++) {
                        HSSFCell cell = row.getCell(k);
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        switch (k) {
                            case 0:
                                studyDeclaration.setEmpId(Long.parseLong(cellValue));
                                break;
                            case 2:
                                studyDeclaration.setEFcslevel(cellValue);
                                break;

                            case 3:
                                studyDeclaration.setEFydlevel(cellValue);
                                break;
                            case 4:
                                studyDeclaration.setEFYTDlevel(cellValue);
                                break;
                            case 5:
                                studyDeclaration.setEFhours(Long.parseLong(cellValue));
                                break;
                            case 6:
                                studyDeclaration.setBCZhours(Long.parseLong(cellValue));
                                break;
                            case 7:
                                studyDeclaration.setVideohours(Long.parseLong(cellValue));
                                break;

                            case 8:
                                studyDeclaration.setTEDtimes(Long.parseLong(cellValue));
                                break;
                            case 9:
                                studyDeclaration.setSharetimes(Long.parseLong(cellValue));
                                break;
                            case 10:
                                studyDeclaration.setUnqualified(Long.parseLong(cellValue));
                                break;
                            case 11:
                                studyDeclaration.setNote(cellValue);
                                break;

                        }
                    }
                    grows.add(studyDeclaration);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return grows;
    }

    public static List<TeamChangeLog> importChangeTeamlist(MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<TeamChangeLog> pros= new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                TeamChangeLog teamChangeLog;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int LastCellNum = row.getLastCellNum();
                    teamChangeLog = new TeamChangeLog();
                    for (int k = 0; k < LastCellNum; k++) {
                        HSSFCell cell = row.getCell(k);
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        switch (k) {
                            case 0:
                                teamChangeLog.setEmpId(Long.parseLong(cellValue));
                                break;
                            case 2:
                                teamChangeLog.setNowCst(cellValue);
                                break;
                            case 3:
                                teamChangeLog.setNowSubCst(cellValue);
                                break;
                            case 4:
                                teamChangeLog.setNewCst(cellValue);
                                break;
                            case 5:
                                teamChangeLog.setNewSubCst(cellValue);
                                break;
                        }
                    }
                    pros.add(teamChangeLog);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return pros;
    }
}