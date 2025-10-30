# 数据分析工具 - 快速开始指南

## 1. 快速启动

### 启动应用
```bash
# 进入项目目录
cd /workspace

# 如果有Maven,使用以下命令启动
mvn spring-boot:run

# 或者如果已打包,使用jar包启动
java -jar target/queue.jar
```

### 访问应用
应用启动后,访问数据分析页面:
```
http://localhost:8899/queue/analysis/page
```

## 2. API测试示例

### 使用curl测试API

#### 测试1: 获取今日业务办理量统计
```bash
curl -X GET "http://localhost:8899/queue/analysis/bizHandleStats?timeType=0" | jq .
```

#### 测试2: 获取本周人员工作量统计
```bash
curl -X GET "http://localhost:8899/queue/analysis/userWorkloadStats?timeType=1&dateStr=$(date +%Y-%m-%d)" | jq .
```

#### 测试3: 获取本月办理时长统计
```bash
curl -X GET "http://localhost:8899/queue/analysis/durationStats?timeType=2&dateStr=$(date +%Y-%m)" | jq .
```

#### 测试4: 获取人员详细统计
```bash
curl -X GET "http://localhost:8899/queue/analysis/userDetailStats?timeType=0" | jq .
```

#### 测试5: 获取综合统计
```bash
curl -X GET "http://localhost:8899/queue/analysis/comprehensiveStats?timeType=0" | jq .
```

### 使用Postman测试

1. 创建新的GET请求
2. 输入URL: `http://localhost:8899/queue/analysis/bizHandleStats`
3. 添加查询参数:
   - `timeType`: 0
   - `dateStr`: 2025-10-30
4. 发送请求

## 3. 前端页面功能

访问 `http://localhost:8899/queue/analysis/page` 后,您可以:

1. **选择时间类型**: 日/周/月/年
2. **选择日期**: 使用日期选择器
3. **查询数据**: 点击"查询"按钮
4. **查看统计**:
   - 综合统计概览(卡片形式)
   - 业务办理量统计表
   - 人员工作量统计表
   - 业务办理时长统计表
   - 人员详细统计表(含评价)

## 4. 数据准备

在测试之前,确保数据库中有测试数据:

### 插入测试业务数据
```sql
INSERT INTO evaluate_sys.base_biz (base_biz_name, base_biz_num) VALUES
('网上立案', 'A001'),
('跨域立案', 'A002'),
('民事立案', 'A003');
```

### 插入测试人员数据
```sql
INSERT INTO evaluate_sys.job_user (job_user_name, job_user_num, job_user_sex) VALUES
('张三', 'U001', '0'),
('李四', 'U002', '0'),
('王五', 'U003', '1');
```

### 插入测试办理记录
```sql
INSERT INTO evaluate_sys.handle_biz_record 
(job_user_id, base_biz_id, transact_biz_id, handle_start_date, handle_end_date) VALUES
(1, 1, 1, '2025-10-30 09:00:00', '2025-10-30 09:05:00'),
(1, 2, 2, '2025-10-30 09:10:00', '2025-10-30 09:15:00'),
(2, 1, 3, '2025-10-30 09:20:00', '2025-10-30 09:28:00'),
(2, 3, 4, '2025-10-30 10:00:00', '2025-10-30 10:10:00'),
(3, 2, 5, '2025-10-30 10:30:00', '2025-10-30 10:40:00');
```

### 插入测试评价数据
```sql
INSERT INTO evaluate_sys.evaluate 
(handle_biz_id, evaluate_type, evaluate_status, evaluate_date) VALUES
(1, '1', '1', '2025-10-30 09:05:30'),
(2, '1', '1', '2025-10-30 09:15:30'),
(3, '2', '1', '2025-10-30 09:28:30'),
(4, '1', '1', '2025-10-30 10:10:30'),
(5, '3', '1', '2025-10-30 10:40:30');
```

## 5. 常用查询场景

### 场景1: 查看今日工作情况
```bash
# 查看今日综合统计
curl "http://localhost:8899/queue/analysis/comprehensiveStats?timeType=0"

# 查看今日各业务办理量
curl "http://localhost:8899/queue/analysis/bizHandleStats?timeType=0"

# 查看今日各人员工作量
curl "http://localhost:8899/queue/analysis/userWorkloadStats?timeType=0"
```

### 场景2: 分析本周业务情况
```bash
# 本周综合统计
curl "http://localhost:8899/queue/analysis/comprehensiveStats?timeType=1&dateStr=2025-10-30"

# 本周办理时长分析
curl "http://localhost:8899/queue/analysis/durationStats?timeType=1&dateStr=2025-10-30"
```

### 场景3: 查看特定人员表现
```bash
# 查看1号员工的详细统计
curl "http://localhost:8899/queue/analysis/userDetailStats?timeType=2&userId=1"
```

### 场景4: 分析特定业务
```bash
# 查看1号业务的办理时长统计
curl "http://localhost:8899/queue/analysis/durationStats?timeType=2&bizId=1"
```

## 6. 前端集成示例

### jQuery Ajax调用示例
```javascript
// 获取业务办理量统计
$.ajax({
    url: '/queue/analysis/bizHandleStats',
    type: 'GET',
    data: {
        timeType: 0,
        dateStr: '2025-10-30'
    },
    success: function(response) {
        if (response.restCode === 0) {
            console.log('数据:', response.restData);
            // 处理数据...
        }
    },
    error: function(xhr, status, error) {
        console.error('请求失败:', error);
    }
});
```

### Fetch API调用示例
```javascript
// 获取综合统计
fetch('/queue/analysis/comprehensiveStats?timeType=0')
    .then(response => response.json())
    .then(data => {
        if (data.restCode === 0) {
            console.log('综合统计:', data.restData);
            // 更新UI...
        }
    })
    .catch(error => console.error('Error:', error));
```

## 7. 性能优化建议

1. **数据库索引**: 为查询字段创建索引
```sql
CREATE INDEX idx_handle_start_date ON handle_biz_record(handle_start_date);
CREATE INDEX idx_job_user_id ON handle_biz_record(job_user_id);
CREATE INDEX idx_base_biz_id ON handle_biz_record(base_biz_id);
```

2. **缓存配置**: 使用Redis缓存高频查询结果
```java
@Cacheable(value = "analysisCache", key = "#timeType + '_' + #dateStr")
public List<DataAnalysisResult> getBizHandleStatistics(int timeType, String dateStr) {
    // ...
}
```

3. **异步查询**: 对于大数据量查询使用异步处理
```java
@Async
public CompletableFuture<List<DataAnalysisResult>> getBizHandleStatisticsAsync(...) {
    // ...
}
```

## 8. 故障排查

### 问题1: 返回数据为空
**原因**: 数据库中没有该时间范围的数据
**解决**: 检查数据库数据,或调整查询时间范围

### 问题2: 页面无法访问
**原因**: 应用未启动或端口被占用
**解决**: 
```bash
# 检查应用是否运行
ps aux | grep java

# 检查端口占用
lsof -i :8899
```

### 问题3: SQL执行错误
**原因**: 表结构不匹配或数据库连接问题
**解决**: 
- 检查application.properties中的数据库配置
- 验证表结构是否正确
- 查看应用日志: `tail -f queuing.log`

## 9. 下一步

1. **数据可视化**: 集成图表库展示统计数据
2. **报表导出**: 实现Excel/PDF导出功能
3. **移动适配**: 优化移动端显示
4. **实时更新**: 使用WebSocket实现数据实时刷新
5. **权限控制**: 添加访问权限管理

## 10. 相关文档

- [详细API文档](DATA_ANALYSIS_README.md)
- [数据库设计文档](src/main/resources/static/db/EvaluateSystem.sql)
- [项目README](README.md)

## 技术支持

如遇问题,请查看:
1. 应用日志: `queuing.log`
2. 控制台输出
3. 浏览器开发者工具(F12)

祝使用愉快! 🎉
