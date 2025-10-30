package com.xct.media.queuing.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据分析结果POJO
 * 
 * @Author: System
 * @Date: 2025-10-30
 * @Description: 用于存储各类数据分析结果
 */
public class DataAnalysisResult {
    
    // 统计类型: 1-业务量统计, 2-人员工作量统计, 3-时长统计, 4-评价统计
    private int analysisType;
    
    // 时间范围类型: 0-日, 1-周, 2-月, 3-年
    private int timeType;
    
    // 开始日期
    private String beginDate;
    
    // 结束日期
    private String endDate;
    
    // 业务ID (用于业务相关统计)
    private Integer bizId;
    
    // 业务名称
    private String bizName;
    
    // 人员ID (用于人员相关统计)
    private Integer userId;
    
    // 人员姓名
    private String userName;
    
    // 统计数量
    private int count;
    
    // 平均时长(秒)
    private Double avgDuration;
    
    // 最大时长(秒)
    private Double maxDuration;
    
    // 最小时长(秒)
    private Double minDuration;
    
    // 好评数量
    private Integer goodCount;
    
    // 中评数量
    private Integer mediumCount;
    
    // 差评数量
    private Integer badCount;
    
    // 好评率
    private Double goodRate;

    public int getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(int analysisType) {
        this.analysisType = analysisType;
    }

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getAvgDuration() {
        return avgDuration;
    }

    public void setAvgDuration(Double avgDuration) {
        this.avgDuration = avgDuration;
    }

    public Double getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(Double maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Double getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(Double minDuration) {
        this.minDuration = minDuration;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getMediumCount() {
        return mediumCount;
    }

    public void setMediumCount(Integer mediumCount) {
        this.mediumCount = mediumCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }

    public Double getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(Double goodRate) {
        this.goodRate = goodRate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
