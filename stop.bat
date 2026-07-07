@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul
title 献血管理系统停止脚本

echo ============================================
echo        🩸 献血管理系统 - 停止脚本
echo ============================================

echo.
echo 查找占用8080端口的进程...
set "PID="
for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080 " ^| findstr "LISTENING"') do set "PID=%%a"

if defined PID (
    echo 找到占用进程，PID: !PID!
    echo 正在终止进程...
    taskkill /F /PID !PID! >nul 2>&1
    if !errorlevel! equ 0 (
        echo ✅ 进程已成功终止
    ) else (
        echo ❌ 终止失败，请尝试手动结束进程
    )
) else (
    echo ✅ 8080端口未被占用，无需停止
)

pause