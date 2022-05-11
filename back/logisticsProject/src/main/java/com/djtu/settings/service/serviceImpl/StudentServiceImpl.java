package com.djtu.settings.service.serviceImpl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.MapUtils;

import com.djtu.dorm.dao.DormDao;
import com.djtu.exception.RegisterException;
import com.djtu.exception.UploadException;
import com.djtu.exception.UserManagerException;
import com.djtu.permission.pojo.vo.StudentDormTutorVo;
import com.djtu.permission.pojo.vo.StudentDormVo;
import com.djtu.response.Result;
import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.service.StudentService;
import com.djtu.utils.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DormDao dormDao;
    private static final Integer SUCCESS_INTO=1;
    @Override
    public void registerStudentUserNameVerify(String username) throws RegisterException {
        Student student = studentDao.getStudentByUsername(username);
        if (student != null) {
            throw new RegisterException("用户名已被注册");
        }
    }

    @Override
    public Integer getStudentTotal() {
        return studentDao.getStudentTotal();
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentDao.getStudentByUsername(username);
    }

    @Override
    public synchronized int editStudent(Student student) {
        Student oldStudent = studentDao.getStudentById(student.getId());

        int usernameCount = studentDao.getUsernameCountByUsername(student.getUsername());
        int snoCount = studentDao.getSnoCountBySno(student.getSno());

        if (oldStudent.getUsername().equals(student.getUsername()) || usernameCount == 0 &&
                oldStudent.getSno().equals(student.getSno()) || snoCount == 0) {

            return studentDao.editStudent(student);
        } else {
            return 0;
        }
    }

    @Override
    public String getStudentSaltById(String id) {
        return studentDao.getStudentSaltById(id);
    }

    @Override
    public void editStudentRemarkById(String id, String remark) throws UserManagerException {
        int res = studentDao.editStudentRemarkById(id, remark);
        if (res != 1) {
            throw new UserManagerException("学生备注修改失败");
        }
    }

    @Override
    public void setAvatarPath(String username, String avatarPath) throws UserManagerException {
        int res = studentDao.setStudentAvatarPathByUsername(username, avatarPath);
        if (res != 1) {
            throw new UserManagerException("学生头像设置失败");
        }
    }

    @Override
    public void downloadMyStudent(String id, HttpServletResponse response) throws IOException {
        //根据用户id获取导员id
        String tutorId=userDao.getTutorIdByUserId(id);
        //根据导员id获取学生
        List<StudentDormVo> list=studentDao.getStudentAndDormInfByTutorId(tutorId);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("学生", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //EasyExcel.write(response.getOutputStream()).sheet("学生表").doWrite(FileUtils.data());
        EasyExcel.write(response.getOutputStream(), StudentDormVo.class).sheet("模板").doWrite(list);
    }

    @Override
    @Transactional(rollbackFor = {UploadException.class})
    public void uploadMyStudent(String userId, MultipartFile file, HttpServletRequest request) throws IOException, UploadException {
        //获取前端传递过来的文件对象，存储在“inputStream”中
        InputStream inputStream =file.getInputStream();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //用于存储解析后的Excel文件
        Workbook workbook =null;
        //判断文件扩展名为“.xls还是xlsx的Excel文件”,因为不同扩展名的Excel所用到的解析方法不同
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(".xls".equals(fileType)){
            workbook= new HSSFWorkbook(inputStream);//HSSFWorkbook专门解析.xls文件
        }else if(".xlsx".equals(fileType)){
            workbook = new XSSFWorkbook(inputStream);//XSSFWorkbook专门解析.xlsx文件
        }
        /**
         * 以 上 摘 自 C S D N
         */
        List<StudentDormVo> list=new ArrayList<>();
        //获取工作表
        Sheet sheet=workbook.getSheetAt(0);
        Row headRow=sheet.getRow(0);
        //验证表头是否符合格式
        if("姓名".equals(headRow.getCell(0).getStringCellValue())
                && "性别".equals(headRow.getCell(1).getStringCellValue()) && "入学时间".equals(headRow.getCell(2).getStringCellValue())
                && "学制".equals(headRow.getCell(3).getStringCellValue()) && "学院".equals(headRow.getCell(4).getStringCellValue())
                && "班级".equals(headRow.getCell(5).getStringCellValue()) && "备注".equals(headRow.getCell(6).getStringCellValue())
                && "学号".equals(headRow.getCell(7).getStringCellValue()) && "寝室号".equals(headRow.getCell(8).getStringCellValue())){
            for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
                Row row=sheet.getRow(i);
                if(row!=null || row.toString().isEmpty()){
                    String name=row.getCell(0).getStringCellValue();
                    String sex=row.getCell(1).getStringCellValue();
                    String enterDate=row.getCell(2).getStringCellValue();
                    String schoolSys=row.getCell(3).getStringCellValue();
                    String college=row.getCell(4).getStringCellValue();
                    String stuClass=row.getCell(5).getStringCellValue();;
                    String remark=row.getCell(6).getStringCellValue();
                    String sno=row.getCell(7).getStringCellValue();
                    String doorNo=row.getCell(8).getStringCellValue();
                    String salt = StringUtil.rand4Str();
                    String password=StringUtil.md5("000000",salt);
                    String username=sno;

                    StudentDormVo studentDormVo=new StudentDormVo();
                    studentDormVo.setName(name);
                    studentDormVo.setSex(sex);
                    studentDormVo.setEnterDate(enterDate);
                    studentDormVo.setSchoolSys(schoolSys);
                    studentDormVo.setCollege(college);
                    studentDormVo.setStuClass(stuClass);
                    studentDormVo.setRemark(remark);
                    studentDormVo.setSno(sno);
                    studentDormVo.setDoorNo(doorNo);
                    studentDormVo.setId(StringUtil.generateUUID());
                    studentDormVo.setSalt(salt);
                    studentDormVo.setPassword(password);
                    studentDormVo.setUsername(username);
                    list.add(studentDormVo);
                }
            }
            //根据用户id获取导员id
            String tutorId=userDao.getTutorIdByUserId(userId);
            //查寝室号对应的寝室id
            for(int i=0;i<list.size();i++){
                String doorNo=list.get(i).getDoorNo();
                //设置导员id
                list.get(i).setTutorId(tutorId);
                if(doorNo!=null && doorNo!=""){//如果doorNo不为空那么就进行查
                    //查寝室表-寝室号对应的寝室id
                    String doorId=dormDao.getIdbyDoorNo(doorNo);
                    list.get(i).setDoorNo(doorId);
                }
                else{
                    continue;
                }
            }
            //学生插入记录
            for(StudentDormVo sv:list){
                Integer i=studentDao.setStudentBringDoorId(sv);
                if(i<SUCCESS_INTO){
                    throw new UploadException("未知异常，导入失败");

                }
            }
        }
        else{
            throw new UploadException("上传的【文件内容】格式不符合，请检测表头");
        }
    }

    @Override
    public void downloadModel( HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("学生", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //EasyExcel.write(response.getOutputStream()).sheet("学生表").doWrite(FileUtils.data());
        List<StudentDormVo> list = ListUtils.newArrayList();
        for (int i = 0; i < 1; i++) {
            StudentDormVo data = new StudentDormVo();
            data.setName("张三");
            data.setSex("男");
            data.setEnterDate("yyy-m-d");
            data.setSchoolSys("4");
            data.setCollege("xx学院");
            data.setStuClass("191");
            data.setRemark("所有单元格格式均要设置为文本");
            data.setSno("xxxxxx");
            data.setDoorNo("***");
            list.add(data);
        }
        EasyExcel.write(response.getOutputStream(), StudentDormVo.class).sheet("模板").doWrite(list);

    }

    @Override
    public void adminDownLoadStudent(HttpServletResponse response) throws IOException {
        //查询学生所有信息包括寝室号以及导员名字
        List<StudentDormTutorVo> list=studentDao.adminDownLoadStudent();
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("学生", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //EasyExcel.write(response.getOutputStream()).sheet("学生表").doWrite(FileUtils.data());
        EasyExcel.write(response.getOutputStream(), StudentDormTutorVo.class).sheet("所有学生").doWrite(list);
    }

    @Override
    @Transactional(rollbackFor = {UploadException.class})
    public void adminUpLoadStudent(MultipartFile file) throws IOException,UploadException{
        //获取前端传递过来的文件对象，存储在“inputStream”中
        InputStream inputStream =file.getInputStream();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //用于存储解析后的Excel文件
        Workbook workbook =null;
        //判断文件扩展名为“.xls还是xlsx的Excel文件”,因为不同扩展名的Excel所用到的解析方法不同
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(".xls".equals(fileType)){
            workbook= new HSSFWorkbook(inputStream);//HSSFWorkbook专门解析.xls文件
        }else if(".xlsx".equals(fileType)){
            workbook = new XSSFWorkbook(inputStream);//XSSFWorkbook专门解析.xlsx文件
        }
        /**
         * 以 上 摘 自 C S D N
         */
        List<StudentDormVo> list=new ArrayList<>();
        //获取工作表
        Sheet sheet=workbook.getSheetAt(0);
        Row headRow=sheet.getRow(0);
        String stuName=headRow.getCell(0).getStringCellValue();
        String sex=headRow.getCell(1).getStringCellValue();
        String enterDate=headRow.getCell(2).getStringCellValue();
        String schoolSys=headRow.getCell(3).getStringCellValue();
        String college=headRow.getCell(4).getStringCellValue();
        String stuClass=headRow.getCell(5).getStringCellValue();
        String remark=headRow.getCell(6).getStringCellValue();
        String sno=headRow.getCell(7).getStringCellValue();
        //验证表头是否符合格式
        if("学生姓名".equals(stuName) && "性别".equals(sex) && "入学时间".equals(enterDate)
                && "学制".equals(schoolSys) && "学院".equals(college) && "学生班级".equals(stuClass)
                && "学生备注".equals(remark) && "学号".equals(sno)){
            for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
                Row row=sheet.getRow(i);
                if(row!=null || row.toString().isEmpty()){
                    //查找学号，如果查到了说明有，就不能重复插入
                    Student stu=studentDao.getStudentBySno(row.getCell(7).getStringCellValue());
                    if(stu!=null){
                        continue;
                    }
                    //封装学生
                    Student student=new Student();
                    student.setId(StringUtil.generateUUID());
                    String salt = StringUtil.rand4Str();
                    student.setPassword(StringUtil.md5("000000",salt));
                    student.setSalt(salt);
                    student.setName(row.getCell(0).getStringCellValue());
                    student.setSex(row.getCell(1).getStringCellValue());
                    student.setEnterDate(row.getCell(2).getStringCellValue());
                    student.setSchoolSys(row.getCell(3).getStringCellValue());
                    student.setCollege(row.getCell(4).getStringCellValue());
                    student.setStuClass(row.getCell(5).getStringCellValue());
                    student.setRemark(row.getCell(6).getStringCellValue());
                    student.setSno(row.getCell(7).getStringCellValue());
                    System.out.println("!!!!!!+"+student);
                    //插入数据库
                    Integer num=studentDao.setStudent(student);
                    if(num<SUCCESS_INTO){
                        throw new UploadException("导入学生失败");
                    }
                }

            }
        }

    }

    @Override
    public void adminDownLoadModel(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("样例", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //EasyExcel.write(response.getOutputStream()).sheet("学生表").doWrite(FileUtils.data());
        List<Student> list = ListUtils.newArrayList();
            Student data = new Student();
            data.setName("张三");
            data.setSex("男");
            data.setEnterDate("yyy-m-d");
            data.setSchoolSys("4");
            data.setCollege("xx学院");
            data.setStuClass("191");
            data.setRemark("所有单元格格式均要设置为文本");
            data.setSno("xxxxxx");
            list.add(data);

        EasyExcel.write(response.getOutputStream(), Student.class).sheet("样例表").doWrite(list);
    }

    public synchronized void editStudentDormById(String id, String dormId) throws UserManagerException {
        int size = dormDao.getDormSizeByDormId(dormId);
        int count = studentDao.countDormByDormId(dormId);
        if (count == size) {
            throw new UserManagerException("寝室人满!");
        }
        int res = studentDao.editStudentDormById(id, dormId);
        if (res != 1) {
            throw new UserManagerException("修改学生寝室失败");
        }
    }

}
