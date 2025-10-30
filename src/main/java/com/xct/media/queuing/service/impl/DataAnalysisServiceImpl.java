package com.xct.media.queuing.service.impl;

import com.xct.media.queuing.mapper.DataAnalysisMapper;
import com.xct.media.queuing.pojo.DataAnalysisResult;
import com.xct.media.queuing.service.DataAnalysisService;
import com.xct.media.queuing.util.DataUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 数据分析服务实现
 * 
 * @Author: System
 * @Date: 2025-10-30
 * @Description: 数据分析服务实现类
 */
@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataAnalysisServiceImpl.class);
    
    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;
    
    @Override
    public List<DataAnalysisResult> getBizHandleStatistics(int timeType, String dateStr) {
        logger.info("getBizHandleStatistics - timeType: {}, dateStr: {}", timeType, dateStr);
        
        String beginDate = processDateByType(timeType, dateStr);
        String endDate = null;
        
        // 如果是周统计,需要计算结束日期
        if (timeType == 1) {
            String[] dates = DataUtils.getFirstAndLastOfWeek(dateStr);
            beginDate = dates[0];
            endDate = dates[1];
        }
        
        List<DataAnalysisResult> results = dataAnalysisMapper.getBizHandleStatistics(beginDate, endDate, timeType);
        logger.info("getBizHandleStatistics - found {} records", results.size());
        
        return results;
    }
    
    @Override
    public List<DataAnalysisResult> getUserWorkloadStatistics(int timeType, String dateStr) {
        logger.info("getUserWorkloadStatistics - timeType: {}, dateStr: {}", timeType, dateStr);
        
        String beginDate = processDateByType(timeType, dateStr);
        String endDate = null;
        
        if (timeType == 1) {
            String[] dates = DataUtils.getFirstAndLastOfWeek(dateStr);
            beginDate = dates[0];
            endDate = dates[1];
        }
        
        List<DataAnalysisResult> results = dataAnalysisMapper.getUserWorkloadStatistics(beginDate, endDate, timeType);
        logger.info("getUserWorkloadStatistics - found {} records", results.size());
        
        return results;
    }
    
    @Override
    public List<DataAnalysisResult> getHandleDurationStatistics(int timeType, String dateStr, Integer bizId) {
        logger.info("getHandleDurationStatistics - timeType: {}, dateStr: {}, bizId: {}", timeType, dateStr, bizId);
        
        String beginDate = processDateByType(timeType, dateStr);
        String endDate = null;
        
        if (timeType == 1) {
            String[] dates = DataUtils.getFirstAndLastOfWeek(dateStr);
            beginDate = dates[0];
            endDate = dates[1];
        }
        
        List<DataAnalysisResult> results = dataAnalysisMapper.getHandleDurationStatistics(beginDate, endDate, timeType, bizId);
        logger.info("getHandleDurationStatistics - found {} records", results.size());
        
        return results;
    }
    
    @Override
    public List<DataAnalysisResult> getUserDetailStatistics(int timeType, String dateStr, Integer userId) {
        logger.info("getUserDetailStatistics - timeType: {}, dateStr: {}, userId: {}", timeType, dateStr, userId);
        
        String beginDate = processDateByType(timeType, dateStr);
        String endDate = null;
        
        if (timeType == 1) {
            String[] dates = DataUtils.getFirstAndLastOfWeek(dateStr);
            beginDate = dates[0];
            endDate = dates[1];
        }
        
        List<DataAnalysisResult> results = dataAnalysisMapper.getUserDetailStatistics(beginDate, endDate, timeType, userId);
        logger.info("getUserDetailStatistics - found {} records", results.size());
        
        return results;
    }
    
    @Override
    public DataAnalysisResult getComprehensiveStatistics(int timeType, String dateStr) {
        logger.info("getComprehensiveStatistics - timeType: {}, dateStr: {}", timeType, dateStr);
        
        String beginDate = processDateByType(timeType, dateStr);
        String endDate = null;
        
        if (timeType == 1) {
            String[] dates = DataUtils.getFirstAndLastOfWeek(dateStr);
            beginDate = dates[0];
            endDate = dates[1];
        }
        
        DataAnalysisResult result = dataAnalysisMapper.getComprehensiveStatistics(beginDate, endDate, timeType);
        logger.info("getComprehensiveStatistics - result: {}", result);
        
        return result;
    }
    
    /**
     * 根据时间类型处理日期字符串
     * 
     * @param timeType 时间类型: 0-日, 1-周, 2-月, 3-年
     * @param dateStr 输入日期字符串
     * @return 处理后的日期字符串
     */
    private String processDateByType(int timeType, String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            // 如果没有提供日期,使用当前日期
            Date now = new Date();
            switch (timeType) {
                case 0: // 日
                    return DateFormatUtils.format(now, dateFormatDay);
                case 2: // 月
                    return DateFormatUtils.format(now, dateFormatMonth);
                case 3: // 年
                    return DateFormatUtils.format(now, dateFormatYear);
                default:
                    return DateFormatUtils.format(now, dateFormatDay);
            }
        }
        return dateStr;
    }
}
