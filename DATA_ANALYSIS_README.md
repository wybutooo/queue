# 数据分析工具使用说明

## 概述

本数据分析工具为排队叫号系统提供了全面的数据统计和分析功能,帮助管理者了解业务办理情况、人员工作效率和服务质量。

## 功能特性

### 1. 综合统计概览
- **总办理量**: 统计指定时间范围内的总业务办理数量
- **平均时长**: 计算业务办理的平均耗时(秒)
- **好评数量**: 统计获得好评的业务数量
- **好评率**: 计算好评占总评价的百分比

### 2. 业务办理量统计
按不同业务类型统计办理数量,帮助了解:
- 各业务的受欢迎程度
- 业务需求分布情况
- 资源配置优化建议

### 3. 人员工作量统计
统计各工作人员的办理数量,用于:
- 评估员工工作量
- 平衡工作分配
- 绩效考核参考

### 4. 业务办理时长统计
分析各业务的办理时长,包括:
- 平均办理时长
- 最长办理时长
- 最短办理时长
- 办理效率评估

### 5. 人员详细统计(含评价)
综合展示人员工作情况,包括:
- 办理数量
- 平均办理时长
- 好评/中评/差评数量
- 好评率

## API接口说明

### 1. 访问数据分析页面
```
GET /queue/analysis/page
```
返回数据分析展示页面

### 2. 获取业务办理量统计
```
GET /queue/analysis/bizHandleStats?timeType=0&dateStr=2025-10-30
```

**参数说明:**
- `timeType`: 时间类型 (0-日, 1-周, 2-月, 3-年)
- `dateStr`: 日期字符串 (可选,默认当前日期)

**返回示例:**
```json
{
  "restCode": 0,
  "message": "success",
  "restData": [
    {
      "bizId": 1,
      "bizName": "网上立案",
      "count": 25
    },
    {
      "bizId": 2,
      "bizName": "跨域立案",
      "count": 18
    }
  ]
}
```

### 3. 获取人员工作量统计
```
GET /queue/analysis/userWorkloadStats?timeType=0&dateStr=2025-10-30
```

**参数说明:**
- `timeType`: 时间类型
- `dateStr`: 日期字符串

**返回示例:**
```json
{
  "restCode": 0,
  "message": "success",
  "restData": [
    {
      "userId": 1,
      "userName": "张三",
      "count": 30
    },
    {
      "userId": 2,
      "userName": "李四",
      "count": 25
    }
  ]
}
```

### 4. 获取业务办理时长统计
```
GET /queue/analysis/durationStats?timeType=0&dateStr=2025-10-30&bizId=1
```

**参数说明:**
- `timeType`: 时间类型
- `dateStr`: 日期字符串
- `bizId`: 业务ID (可选,不传则查询所有业务)

**返回示例:**
```json
{
  "restCode": 0,
  "message": "success",
  "restData": [
    {
      "bizId": 1,
      "bizName": "网上立案",
      "count": 25,
      "avgDuration": 180.5,
      "maxDuration": 300.0,
      "minDuration": 120.0
    }
  ]
}
```

### 5. 获取人员详细统计
```
GET /queue/analysis/userDetailStats?timeType=0&dateStr=2025-10-30&userId=1
```

**参数说明:**
- `timeType`: 时间类型
- `dateStr`: 日期字符串
- `userId`: 人员ID (可选,不传则查询所有人员)

**返回示例:**
```json
{
  "restCode": 0,
  "message": "success",
  "restData": [
    {
      "userId": 1,
      "userName": "张三",
      "count": 30,
      "avgDuration": 175.8,
      "goodCount": 25,
      "mediumCount": 3,
      "badCount": 2,
      "goodRate": 83.33
    }
  ]
}
```

### 6. 获取综合统计报表
```
GET /queue/analysis/comprehensiveStats?timeType=0&dateStr=2025-10-30
```

**参数说明:**
- `timeType`: 时间类型
- `dateStr`: 日期字符串

**返回示例:**
```json
{
  "restCode": 0,
  "message": "success",
  "restData": {
    "count": 100,
    "avgDuration": 180.5,
    "goodCount": 85,
    "mediumCount": 10,
    "badCount": 5,
    "goodRate": 85.0
  }
}
```

## 时间类型说明

- **0 - 日**: 按天统计,dateStr格式: `2025-10-30`
- **1 - 周**: 按周统计,dateStr格式: `2025-10-30` (该日期所在周)
- **2 - 月**: 按月统计,dateStr格式: `2025-10` 或 `2025-10-30`
- **3 - 年**: 按年统计,dateStr格式: `2025` 或 `2025-10-30`

## 使用示例

### 1. 查询今日业务办理量
```bash
curl -X GET "http://localhost:8899/queue/analysis/bizHandleStats?timeType=0"
```

### 2. 查询本周人员工作量
```bash
curl -X GET "http://localhost:8899/queue/analysis/userWorkloadStats?timeType=1&dateStr=2025-10-30"
```

### 3. 查询本月综合统计
```bash
curl -X GET "http://localhost:8899/queue/analysis/comprehensiveStats?timeType=2&dateStr=2025-10"
```

### 4. 查询指定业务的办理时长
```bash
curl -X GET "http://localhost:8899/queue/analysis/durationStats?timeType=0&bizId=1"
```

## 数据库表结构说明

数据分析功能涉及以下数据库表:

1. **base_biz**: 业务基础信息表
   - base_biz_id: 业务ID
   - base_biz_name: 业务名称
   - base_biz_num: 业务编号

2. **job_user**: 工作人员信息表
   - job_user_id: 人员ID
   - job_user_name: 人员姓名

3. **handle_biz_record**: 业务办理记录表
   - handle_biz_id: 记录ID
   - job_user_id: 办理人员ID
   - base_biz_id: 业务ID
   - handle_start_date: 开始时间
   - handle_end_date: 结束时间

4. **evaluate**: 评价信息表
   - evaluate_id: 评价ID
   - handle_biz_id: 办理记录ID
   - evaluate_type: 评价类型 (1-好, 2-中, 3-差)
   - evaluate_date: 评价时间

## 技术架构

- **后端框架**: Spring Boot 1.5.12
- **持久层**: MyBatis
- **数据库**: MySQL
- **前端**: Thymeleaf + jQuery + Ace Admin Template

## 文件结构

```
src/main/java/com/xct/media/queuing/
├── controller/
│   └── DataAnalysisController.java        # 数据分析控制器
├── service/
│   ├── DataAnalysisService.java           # 数据分析服务接口
│   └── impl/
│       └── DataAnalysisServiceImpl.java   # 数据分析服务实现
├── mapper/
│   └── DataAnalysisMapper.java            # 数据分析Mapper接口
└── pojo/
    └── DataAnalysisResult.java            # 数据分析结果POJO

src/main/resources/
├── static/
│   └── mybatis/
│       └── mapper/
│           └── data_analysis_mapper.xml   # MyBatis SQL映射文件
└── templates/
    └── data_analysis.html                 # 数据分析展示页面
```

## 注意事项

1. 确保数据库表中有足够的测试数据
2. 时间格式需要符合指定的格式要求
3. 所有接口均支持跨域访问(如配置了CORS)
4. 建议在生产环境中对查询结果进行缓存以提高性能
5. 大数据量查询时建议使用分页功能(后续可扩展)

## 扩展建议

1. **图表可视化**: 集成ECharts或Chart.js进行数据可视化
2. **导出功能**: 支持将统计结果导出为Excel或PDF
3. **定时报表**: 实现定时生成并发送统计报表
4. **对比分析**: 支持不同时间段的数据对比
5. **预警功能**: 设置阈值,当指标异常时发送预警通知

## 常见问题

**Q: 如何查看某个特定人员的统计数据?**
A: 在调用API时传入userId参数即可,例如: `/queue/analysis/userDetailStats?userId=1`

**Q: 数据统计的时间范围如何确定?**
A: 根据timeType和dateStr参数自动计算,例如按周统计时,系统会自动计算该日期所在周的起止日期。

**Q: 为什么某些数据显示为0?**
A: 可能是该时间范围内没有相关数据,或者数据库表中的数据不完整(如缺少评价数据)。

## 更新日志

### v1.0.0 (2025-10-30)
- 初始版本发布
- 支持业务办理量统计
- 支持人员工作量统计
- 支持办理时长分析
- 支持人员详细统计(含评价)
- 提供综合统计概览

## 联系方式

如有问题或建议,请联系开发团队。
