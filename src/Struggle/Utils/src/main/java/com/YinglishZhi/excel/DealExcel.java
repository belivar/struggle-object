package com.YinglishZhi.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author LDZ
 * @date 2019-12-26 16:32
 */
public class DealExcel {


    private static List<ExcelPropertyIndexModel> list = new ArrayList<>();

    private static void read() throws Exception {
        //text.xlsx
        try (InputStream in = new FileInputStream("/Users/zhiyinglish/OneDrive/doc/MEITUAN_DOC/所有工单-2019-12-25.xls");) {
            AnalysisEventListener<ExcelPropertyIndexModel> listener = new AnalysisEventListener<ExcelPropertyIndexModel>() {

                @Override
                public void invoke(ExcelPropertyIndexModel object, AnalysisContext context) {
                    list.add(object);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.err.println("doAfterAllAnalysed...");
                    System.out.println(list.size());
                    Map<String, Result> re11 = new HashMap<>();
                    Map<String, Result> re12 = new HashMap<>();
                    for (ExcelPropertyIndexModel e : list) {
                        if (e.getProperty0() == null) {
                            continue;
                        }
                        LocalDateTime cti = LocalDateTime.parse(e.getProperty0(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        if (cti.getMonth() == Month.NOVEMBER) {
                            if (re11.containsKey(e.getProperty1())) {
                                Result temR = re11.get(e.getProperty1());
                                Result r = new Result(e.getProperty1(), temR.time + 1, e.getProperty1() == null ? temR.getSolveTime() : temR.getSolveTime().add(new BigDecimal(e.getProperty2())), e.getProperty0());
                                re11.put(e.getProperty1(), r);
                            } else {
                                re11.put(e.getProperty1(), new Result(e.getProperty1() == null ? "null" : e.getProperty1(), 1, e.getProperty2() == null ? BigDecimal.ZERO : new BigDecimal(e.getProperty2()), e.getProperty0()));
                            }
                        } else if (cti.getMonth() == Month.DECEMBER) {
                            if (re12.containsKey(e.getProperty1())) {
                                Result temR = re12.get(e.getProperty1());
                                Result r = new Result(e.getProperty1(), temR.time + 1, e.getProperty2() == null ? temR.getSolveTime() : temR.getSolveTime().add(new BigDecimal(e.getProperty2())), e.getProperty0());
                                re12.put(e.getProperty1(), r);
                            } else {
                                re12.put(e.getProperty1(), new Result(e.getProperty1() == null ? "null" : e.getProperty1(), 1, e.getProperty2() == null ? BigDecimal.ZERO : new BigDecimal(e.getProperty2()), e.getProperty0()));
                            }
                        }

                    }

                    try {
                        writeWithoutHead(Arrays.asList(re11, re12));
//                        writeWithoutHead11(re11);
//                        writeWithoutHead12(re12);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            ExcelReader excelReader = ExcelReaderFactory.getExcelReader(in, null, listener);
            // 第二个参数为表头行数，按照实际设置
            excelReader.read(new Sheet(3, 1, ExcelPropertyIndexModel.class));
        }
    }


    private static void writeWithoutHead(List<Map<String, Result>> re) throws IOException {
        try (OutputStream out = new FileOutputStream("/Users/zhiyinglish/OneDrive/doc/MEITUAN_DOC/withoutHead.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            int i = 1;
            for (Map<String, Result> e : re) {
                Sheet sheet1 = new Sheet(i++, 0);
                sheet1.setSheetName(String.valueOf(i++));
                List<List<String>> data = new ArrayList<>();
                for (Map.Entry<String, Result> entry : e.entrySet()) {
                    List<String> item = new ArrayList<>();
                    item.add(entry.getValue().getName());
                    item.add(entry.getValue().getTime().toString());
                    item.add(entry.getValue().getSolveTime().toString());
                    item.add(entry.getValue().getCtime());

                    data.add(item);
                }
                writer.write0(data, sheet1);
            }
            writer.finish();

        }
    }

    private static void writeWithoutHead12(Map<String, Result> re) throws IOException {
        try (OutputStream out = new FileOutputStream("/Users/zhiyinglish/OneDrive/doc/MEITUAN_DOC/withoutHead12.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("12");
            List<List<String>> data = new ArrayList<>();
            for (Map.Entry<String, Result> entry : re.entrySet()) {
                List<String> item = new ArrayList<>();
                item.add(entry.getValue().getName());
                item.add(entry.getValue().getTime().toString());
                item.add(entry.getValue().getSolveTime().toString());
                data.add(item);
            }
            writer.write0(data, sheet1);
            writer.finish();

        }
    }

    private static void writeWithoutHead11(Map<String, Result> re) throws IOException {
        try (OutputStream out = new FileOutputStream("/Users/zhiyinglish/OneDrive/doc/MEITUAN_DOC/withoutHead11.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("12");
            List<List<String>> data = new ArrayList<>();
            for (Map.Entry<String, Result> entry : re.entrySet()) {
                List<String> item = new ArrayList<>();
                item.add(entry.getValue().getName());
                item.add(entry.getValue().getTime().toString());
                item.add(entry.getValue().getSolveTime().toString());
                data.add(item);
            }
            writer.write0(data, sheet1);
            writer.finish();

        }
    }

    public static void main(String[] args) {
        try {
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ==============================

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ExcelPropertyIndexModel extends BaseRowModel {
        @ExcelProperty(value = "创建时间", index = 0)
        String property0;

        @ExcelProperty(value = "解决方案", index = 1)
        String property1;

        @ExcelProperty(value = "解决时长", index = 2)
        String property2;
    }


    @Data
    @AllArgsConstructor
    static class Result {

        /**
         * name
         */
        String name;

        /**
         * 次数
         */
        Integer time;

        /**
         * 解决时间
         */
        BigDecimal solveTime;

        /**
         * 创建时间
         */
        String ctime;

    }


}
