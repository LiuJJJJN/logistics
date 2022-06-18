<center><h1>POI和easyExcel</h1></center>

<font style="font-size:13px">资料来源于：https://www.bilibili.com/video/BV1Ua4y1x7BK?spm_id_from=333.999.0.0</font>

<font style="font-size:13px">笔记记录者：@LittleHan：https://gitee.com/LittleHanQAQ</font>

## 一、常用场景

- ​	将用户信息导出为excel表格(导出数据...)
- 将Execl表中的信息录入到网站数据库

## 二、POI基本功能

- HSSF-提供读写Microsoft Execl格式档案的功能	03年版本的xls
- XSSF-提供读写Microsoft Execl OOXML格式档案的功能 07年以后版本地xlsx
- HWPF-提供读写Microsoft Word格式档案的功能
- HSLF-提供读写Microsoft PowerPoint格式档案的功能
- HDGF-提供读写Microsoft Visio格式档案的功能

## 三、JAVA解析Excel工具EasyExcel(阿里巴巴)

- easyExcel 官网网址：https://github.com/alibaba/easyexcel
- easyExcel 官方文档：https://www.yuque.com/easyexcel/doc/easyexecl
- Apache POI官网：https://poi.apache.org/

- java解析、生成Excel比较有名的框架有Apache poi、jxl，但他们都存在严重问题非常的消耗内存，EsayExcel

不会出现内存溢出，让使用者更加简单方便 。

- POI与easyExcel对比：

![](D:\1DJTU\logistics\项目笔记.assets\1.JPG)

## 四、POI写入数据

- 导入相关依赖

```xml
<!--导入依赖-->
    <dependencies>
    <!--xLs(03)-->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>3.9</version>
    </dependency>
    <!--xLsx(07)-->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId><version>3.9</version>
    </dependency>
    <!--日期格式化工具-->
    <dependency>
    <groupId>joda-time</groupId>
    	<artifactId>joda-time</artifactId>
        <version>2.10.1</version>
    </dependency>
```

- <font color="red">需要注意:2003版本和2007版本存在兼容性问题！03最多只用65535行！</font>
- Excel中的几个对象
  - 工作簿：
  - 工作表：
  - 行：
  - 列：

```java
 		//1.创建工作簿 Workbook为一个接口
        Workbook workbook=new HSSFWorkbook();
        //2.创建工作表
       Sheet sheet=workbook.createSheet("表名字");
       //3.创建一个行
        Row row1=sheet.createRow(0);
        //4.创建一个单元格
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("姓名");

        Row row2=sheet.createRow(1);
        Cell cell2=row1.createCell(0);
        cell2.setCellValue("张三");
        //生成一张表（IO流）
        String path="E:\\";
        FileOutputStream fileOutputStream=new FileOutputStream(path+"学生表.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
```

## 五、大量数据的写入

- 大文件写HSSF

  - 缺点：最多只能处理65536行，否则回抛出异常。

  - ```java
    java.lang.IllegalArugmentException:Invalid row number(65536) outside allowable range(0..65535)
    ```

  - 优点：过程中写入缓存，不操作磁盘，最后一次性写入磁盘，速度快。 

- 大文件写XSSF

  - 缺点：写数据时速度非常 慢，非常耗内存，也会发生内存溢出，如100万条
  - 优点：可以写较大的数据量，如20万条

- 大文件写SXSSF

  - 优点：可以写非常大的数据量，如100万条甚至更多条，写数据速度快，占用更少的内存

  - 注意：

    - 过程中会产生临时文件，需要清理临时文件
    - 默认由100条记录被保存在内存中，如果超过这数量，则最前面的数据被写入临时文件
    - 如果向自定义内存中数据的数量，可以使用new SXXSSFWorkbook(数量)

  - 清除临时文件方法：

    ```java
    ((SXSSFWorkbook)workbook).dispose();
    ```

## 六、POI读取数据

- ```java
    	//1.获取文件流
      FileInputStream inputStream=new FileInputStream(path+"学生.xlsx");
      //2.创建工作簿
      Workbook workbook=new HSSFWorkbook(inputStream);
      //3.获取工作表
      Sheet sheet=workbook.getSheetAt(0);
      //获取行
      Row row=sheet.getRow(0);
      //获取列(结合行就是一个单元格[0,0])
      Cell cell=row.getCell(0);
      cell.getStringCellValue();
  ```

- <font color="red">注意：获取值的类型</font>

- Row类里的getPhysicalNunberOfCells()方法获取指定行有多少列

- Sheet类里的getPhysicalNumberOfRows()方法获取表里有多少行

## 七、Easy Excel的使用

- https://github.com/alibaba/easyexcel

- 导入依赖

  ```xml
  <dependency>
  	<groupId>com.alibaba</groupId>
      <artifactId>easyexcel</artifactId>
      <version>3.0.5</version>
  </dependency>	
  	
  ```

- <font color="red">注意：该依赖已经引用了xLs(03)和xLsx(07）,同时需要lombok依赖</font>

- 三步走：

  - 实体类

  ```java
  @Data
  public class DemoData{
      @ExcepProperty("字符串标题")
      private String str;
      
      /**
       * 忽略这个字段
       */
      @ExcelIgnore
      private String ignore;
  }
  ```

  - 如果查到的数据已经时list封装好了，可以省略此步骤
    
    ```java
    private List<DownloadData> data() {
            List<DownloadData> list = ListUtils.newArrayList();
            for (int i = 0; i < 10; i++) {
                DownloadData data = new DownloadData();
                data.setString("字符串" + 0);
                data.setDate(new Date());
                data.setDoubleData(0.56);
                list.add(data);
            }
            return list;
        }
    ```
    
  - 写Excel

    ```java
    String fileName = TestFileUtil.getPath() + "write" + System.currentTimeMillis() + ".xlsx";
            // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            // 如果这里想使用03 则 传入excelType参数即可
            EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    ```

## 八、遇到的问题以及解决方案

- <font color="red">遇到的问题1：在web中，我们前端通过ajax发送导出Excel请求，当响应到浏览器是二进制码，并不会触发下载excel。</font>
  - ![](D:\1DJTU\logistics\项目笔记.assets\问题.png)
  - 解决方案：我们可以采用form表单方式/超链接方式，而不是ajax方式发起请求。
- <font color="red">遇到的问题2：根据row对象获取cell的值方法row.getCell( num)，返回值并不是String类型而是Cell类型，通过row.getCell( num).getStringCellValue()获取值</font>
- 

