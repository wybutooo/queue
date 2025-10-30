#!/bin/bash

# 数据分析工具 API 测试脚本
# 使用方法: bash API_TEST.sh

BASE_URL="http://localhost:8899/queue/analysis"
DATE=$(date +%Y-%m-%d)

echo "======================================"
echo "数据分析工具 API 测试"
echo "======================================"
echo ""

echo "1. 测试综合统计接口..."
curl -s -X GET "${BASE_URL}/comprehensiveStats?timeType=0&dateStr=${DATE}" | python3 -m json.tool 2>/dev/null || echo "请求失败或JSON格式错误"
echo ""
echo ""

echo "2. 测试业务办理量统计接口..."
curl -s -X GET "${BASE_URL}/bizHandleStats?timeType=0&dateStr=${DATE}" | python3 -m json.tool 2>/dev/null || echo "请求失败或JSON格式错误"
echo ""
echo ""

echo "3. 测试人员工作量统计接口..."
curl -s -X GET "${BASE_URL}/userWorkloadStats?timeType=0&dateStr=${DATE}" | python3 -m json.tool 2>/dev/null || echo "请求失败或JSON格式错误"
echo ""
echo ""

echo "4. 测试办理时长统计接口..."
curl -s -X GET "${BASE_URL}/durationStats?timeType=0&dateStr=${DATE}" | python3 -m json.tool 2>/dev/null || echo "请求失败或JSON格式错误"
echo ""
echo ""

echo "5. 测试人员详细统计接口..."
curl -s -X GET "${BASE_URL}/userDetailStats?timeType=0&dateStr=${DATE}" | python3 -m json.tool 2>/dev/null || echo "请求失败或JSON格式错误"
echo ""
echo ""

echo "======================================"
echo "测试完成!"
echo "======================================"
