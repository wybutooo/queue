package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.DataAnalysisResult;

import java.util.List;

/**
 * 数据分析服务接口
 * 
 * @Author: System
 * @Date: 2025-10-30
 * @Description: 提供各类数据分析功能
 */
public interface DataAnalysisService {
    
    String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
    String dateFormatDay = "yyyy-MM-dd";
    String dateFormatYear = "yyyy";
    String dateFormatMonth = "yyyy-MM";
    
    /**
     * 获取业务办理量统计
     * 
     * @param timeType 时间类型: 0-日, 1-周, 2-月, 3-年
     * @param dateStr 日期字符串
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getBizHandleStatistics(int timeType, String dateStr);
    
    /**
     * 获取人员工作量统计
     * 
     * @param timeType 时间类型
     * @param dateStr 日期字符串
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getUserWorkloadStatistics(int timeType, String dateStr);
    
    /**
     * 获取业务办理时长统计
     * 
     * @param timeType 时间类型
     * @param dateStr 日期字符串
     * @param bizId 业务ID (可选)
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getHandleDurationStatistics(int timeType, String dateStr, Integer bizId);
    
    /**
     * 获取人员业务办理详细统计(包含评价)
     * 
     * @param timeType 时间类型
     * @param dateStr 日期字符串
     * @param userId 人员ID (可选)
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getUserDetailStatistics(int timeType, String dateStr, Integer userId);
    
    /**
     * 获取综合统计报表
     * 
     * @param timeType 时间类型
     * @param dateStr 日期字符串
     * @return 综合统计结果
     */
    DataAnalysisResult getComprehensiveStatistics(int timeType, String dateStr);
}
