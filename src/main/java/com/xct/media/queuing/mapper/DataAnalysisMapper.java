package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.DataAnalysisResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据分析Mapper
 * 
 * @Author: System
 * @Date: 2025-10-30
 * @Description: 数据分析相关的数据库查询接口
 */
public interface DataAnalysisMapper {
    
    /**
     * 业务办理量统计
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param timeType 时间类型 0-日, 1-周, 2-月, 3-年
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getBizHandleStatistics(@Param("beginDate") String beginDate, 
                                                      @Param("endDate") String endDate,
                                                      @Param("timeType") int timeType);
    
    /**
     * 人员工作量统计
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param timeType 时间类型
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getUserWorkloadStatistics(@Param("beginDate") String beginDate,
                                                         @Param("endDate") String endDate,
                                                         @Param("timeType") int timeType);
    
    /**
     * 业务办理时长统计
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param timeType 时间类型
     * @param bizId 业务ID (可选, null表示所有业务)
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getHandleDurationStatistics(@Param("beginDate") String beginDate,
                                                           @Param("endDate") String endDate,
                                                           @Param("timeType") int timeType,
                                                           @Param("bizId") Integer bizId);
    
    /**
     * 人员业务办理详细统计(包含评价)
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param timeType 时间类型
     * @param userId 人员ID (可选)
     * @return 统计结果列表
     */
    List<DataAnalysisResult> getUserDetailStatistics(@Param("beginDate") String beginDate,
                                                       @Param("endDate") String endDate,
                                                       @Param("timeType") int timeType,
                                                       @Param("userId") Integer userId);
    
    /**
     * 综合统计报表
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param timeType 时间类型
     * @return 综合统计结果
     */
    DataAnalysisResult getComprehensiveStatistics(@Param("beginDate") String beginDate,
                                                    @Param("endDate") String endDate,
                                                    @Param("timeType") int timeType);
}
