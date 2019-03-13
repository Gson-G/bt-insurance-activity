package com.bz.ins.activity.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/11
 * @time 10:58 AM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ExcelUtil {

    private static Logger log = LoggerFactory.getLogger(String.valueOf(ExcelUtil.class));

    private final static String excel2003L = ".xls"; // 2003- 版本的excel
    private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

    private final static String QUESTION_CONTENT = "1%$s的花名是什么";

    private final static String QUESTION_CONTENT_TWO = "1%$s是谁的花名";

    /**
     * 将流中的Excel数据转成List<Map>
     *
     * @param in       输入流
     * @param fileName 文件名（判断Excel版本）
     * @return
     * @throws Exception
     */
    public static List<ExcelPoji> parseExcel(InputStream in, String fileName) throws Exception {
        // 根据文件名来创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        // 返回数据
        List<ExcelPoji> allList = new ArrayList<>();
        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            // 取第一行标题
            row = sheet.getRow(2);
            String title[] = null;
            if (row != null) {
                title = new String[row.getLastCellNum()];
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    title[y] = (String) getCellValue(cell);
                }
            } else {
                continue;
            }
            // 遍历当前sheet中的所有行
            for (int j = 3; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                if (!getCellValue(row.getCell(0)).equals("")) {
                    ExcelPoji sjbcExcelDTO = new ExcelPoji();
                    // 遍历所有的列
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        String key = title[y];
                        if (y == 0 && !getCellValue(cell).equals("")) {
                            sjbcExcelDTO.setNickName(getCellValue(cell));
                        }
                        if (y == 1 && !getCellValue(cell).equals("")) {
                            sjbcExcelDTO.setName(getCellValue(cell));
                        }
                        if (y == 2 && !getCellValue(cell).equals("")) {
                            sjbcExcelDTO.setBoss("1".equals(getCellValue(cell)));
                        }
                    }
                    allList.add(sjbcExcelDTO);                }
            }
        }
        work.close();
        return allList;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr ,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr); // 2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr); // 2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        if (null == cell) {
            return null;
        }
        String value = null;
        DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df2 = new DecimalFormat("0"); // 格式化数字

        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    //Sun Dec 31 15:03:00 CST 1899格式化时间15:03
                    value = sdf2.format(cell.getDateCellValue()).substring(11, 16);
                }
                break;
            case BOOLEAN:
                //value = cell.getBooleanCellValue();
                value = "";
                break;
            case BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    public static void main(String[] args) throws Exception {

        ClassLoader classloader =
                org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
        URL res = classloader.getResource(
                "org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
        String path = res.getPath();
        System.out.println("POI Core came from " + path);

        classloader = org.apache.poi.POIXMLDocument.class.getClassLoader();
        res = classloader.getResource("org/apache/poi/POIXMLDocument.class");
        path = res.getPath();
        System.out.println("POI OOXML came from " + path);

//        classloader = org.apache.poi.hslf.HSLFSlideShow.class.getClassLoader();
//        res = classloader.getResource("org/apache/poi/hslf/HSLFSlideShow.class");
//        path = res.getPath();
//        System.out.println("POI Scratchpad came from " + path);
        File file = new File("/Users/kantenmei/Downloads/quest.xlsx");
        FileInputStream fis = new FileInputStream(file);
        List<ExcelPoji> ls = parseExcel(fis, file.getName());
        System.out.println(ls.size());
    }


}
