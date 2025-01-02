package io.renren.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;

import java.io.InputStream;
import java.util.List;

public class ExcelReaderUtil {

    /**
     * 读取Excel文件
     *
     * @param inputStream Excel文件的输入流
     * @param targetClass 目标类（Excel数据模型类）
     * @param <T>         数据模型类型
     * @return 读取到的对象列表
     */
    public static <T> List<T> readExcel(InputStream inputStream, Class<T> targetClass) {
        // 使用EasyExcel的read方法读取Excel文件，读取每一行数据并转换成目标类的对象
        return EasyExcel.read(inputStream, targetClass, new PageReadListener<T>(dataList -> {
            // 这里你可以处理读取到的数据，例如存入数据库或做其他处理
            // dataList是当前页读取到的数据，数据是自动映射到目标类的
            // 如果数据量大，可以做分页处理或者批量保存
        })).sheet().doReadSync();
    }

    /**
     * 通用导入方法
     *
     * @param inputStream Excel文件的输入流
     * @param targetClass 目标类（Excel数据模型类）
     * @param batchSize 批量插入的大小（如果数据量大，可以进行分批处理）
     * @param saveDataHandler 数据处理回调方法（保存数据到数据库等）
     * @param <T> 数据模型类型
     */
    public static <T> void importData(InputStream inputStream, Class<T> targetClass, int batchSize, SaveDataHandler<T> saveDataHandler) {
        // 使用EasyExcel读取数据，读取时每次读取batchSize条数据并进行处理
        EasyExcel.read(inputStream, targetClass, new PageReadListener<T>(dataList -> {
            // 每批次读取到的数据
            saveDataHandler.handle(dataList); // 使用回调方法保存数据
        })).sheet().doRead();
    }

    // 保存数据的处理接口
    public interface SaveDataHandler<T> {
        void handle(List<T> dataList);
    }
}