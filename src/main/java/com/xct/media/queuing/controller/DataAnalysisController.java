package com.xct.media.queuing.controller;

import com.xct.media.queuing.pojo.DataAnalysisResult;
import com.xct.media.queuing.service.DataAnalysisService;
import com.xct.media.queuing.util.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据分析控制器
 * 
 * @Author: System
 * @Date: 2025-10-30
 * @Description: 提供数据分析相关的REST API接口
 */
@Controller
@RequestMapping("/queue/analysis")
public class DataAnalysisController {
    
    private static final Logger logger = LoggerFactory.getLogger(DataAnalysisController.class);
    
    @Autowired
    private DataAnalysisService dataAnalysisService;
    
    /**
     * 数据分析页面
     * 
     * @return 数据分析页面
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String dataAnalysisPage() {
        return "data_analysis";
    }
    
    /**
     * 获取业务办理量统计
     * 
     * @param timeType 时间类型: 0-日, 1-周, 2-月, 3-年
     * @param dateStr 日期字符串 (可选,默认当前日期)
     * @return 业务办理量统计结果
     */
    @ResponseBody
    @RequestMapping(value = "/bizHandleStats", method = RequestMethod.GET)
    public ApiResult<List<DataAnalysisResult>> getBizHandleStatistics(
            @RequestParam(defaultValue = "0") int timeType,
            @RequestParam(required = false) String dateStr) {
        try {
            logger.info("getBizHandleStatistics - timeType: {}, dateStr: {}", timeType, dateStr);
            List<DataAnalysisResult> results = dataAnalysisService.getBizHandleStatistics(timeType, dateStr);
            return ApiResult.success(results);
        } catch (Exception e) {
            logger.error("getBizHandleStatistics error", e);
            return ApiResult.error("获取业务办理量统计失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取人员工作量统计
     * 
     * @param timeType 时间类型: 0-日, 1-周, 2-月, 3-年
     * @param dateStr 日期字符串 (可选)
     * @return 人员工作量统计结果
     */
    @ResponseBody
    @RequestMapping(value = "/userWorkloadStats", method = RequestMethod.GET)
    public ApiResult<List<DataAnalysisResult>> getUserWorkloadStatistics(
            @RequestParam(defaultValue = "0") int timeType,
            @RequestParam(required = false) String dateStr) {
        try {
            logger.info("getUserWorkloadStatistics - timeType: {}, dateStr: {}", timeType, dateStr);
            List<DataAnalysisResult> results = dataAnalysisService.getUserWorkloadStatistics(timeType, dateStr);
            return ApiResult.success(results);
        } catch (Exception e) {
            logger.error("getUserWorkloadStatistics error", e);
            return ApiResult.error("获取人员工作量统计失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取业务办理时长统计
     * 
     * @param timeType 时间类型: 0-日, 1-周, 2-月, 3-年
     * @param dateStr 日期字符串 (可选)
     * @param bizId 业务ID (可选,null表示所有业务)
     * @return 办理时长统计结果
     */
    @ResponseBody
    @RequestMapping(value = "/durationStats", method = RequestMethod.GET)
    public ApiResult<List<DataAnalysisResult>> getHandleDurationStatistics(
            @RequestParam(defaultValue = "0") int timeType,
            @RequestParam(required = false) String dateStr,
            @RequestParam(required = false) Integer bizId) {
        try {
            logger.info("getHandleDurationStatistics - timeType: {}, dateStr: {}, bizId: {}", timeType, dateStr, bizId);
            List<DataAnalysisResult> results = dataAnalysisService.getHandleDurationStatistics(timeType, dateStr, bizId);
            return ApiResult.success(results);
        } catch (Exception e) {
            logger.error("getHandleDurationStatistics error", e);
            return ApiResult.error("获取办理时长统计失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取人员详细统计(包含评价)
     * 
     * @param timeType 时间类型: 0-日, 1-周, 2-月, 3-年
     * @param dateStr 日期字符串 (可选)
     * @param userId 人员ID (可选,null表示所有人员)
     * @return 人员详细统计结果
     */
    @ResponseBody
    @RequestMapping(value = "/userDetailStats", method = RequestMethod.GET)
    public ApiResult<List<DataAnalysisResult>> getUserDetailStatistics(
            @RequestParam(defaultValue = "0") int timeType,
            @RequestParam(required = false) String dateStr,
            @RequestParam(required = false) Integer userId) {
        try {
            logger.info("getUserDetailStatistics - timeType: {}, dateStr: {}, userId: {}", timeType, dateStr, userId);
            List<DataAnalysisResult> results = dataAnalysisService.getUserDetailStatistics(timeType, dateStr, userId);
            return ApiResult.success(results);
        } catch (Exception e) {
            logger.error("getUserDetailStatistics error", e);
            return ApiResult.error("获取人员详细统计失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取综合统计报表
     * 
     * @param timeType 时间类型: 0-日, 1-周, 2-月, 3-年
     * @param dateStr 日期字符串 (可选)
     * @return 综合统计结果
     */
    @ResponseBody
    @RequestMapping(value = "/comprehensiveStats", method = RequestMethod.GET)
    public ApiResult<DataAnalysisResult> getComprehensiveStatistics(
            @RequestParam(defaultValue = "0") int timeType,
            @RequestParam(required = false) String dateStr) {
        try {
            logger.info("getComprehensiveStatistics - timeType: {}, dateStr: {}", timeType, dateStr);
            DataAnalysisResult result = dataAnalysisService.getComprehensiveStatistics(timeType, dateStr);
            return ApiResult.success(result);
        } catch (Exception e) {
            logger.error("getComprehensiveStatistics error", e);
            return ApiResult.error("获取综合统计失败: " + e.getMessage());
        }
    }
}
