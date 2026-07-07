@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul
title 献血管理系统启动脚本

echo ============================================
echo        🩸 献血管理系统 - 启动脚本
echo ============================================

echo.
echo [1/3] 检查8080端口占用情况...
set "PID="
for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080 " ^| findstr "LISTENING"') do set "PID=%%a"
if defined PID (
    echo       检测到8080端口被占用，占用进程PID: !PID!
    echo       正在终止占用进程...
    taskkill /F /PID !PID! >nul 2>&1
    if !errorlevel! equ 0 (
        echo       ✅ 占用进程已成功终止
    ) else (
        echo       ❌ 终止进程失败，请手动处理
        pause
        exit /b 1
    )
) else (
    echo       ✅ 8080端口未被占用
)

echo.
echo [2/3] 编译项目...
call mvn clean compile -q
if !errorlevel! neq 0 (
    echo       ❌ 编译失败
    pause
    exit /b 1
)
echo       ✅ 编译成功

echo.
echo [3/3] 启动Spring Boot应用...
echo       启动后访问: http://localhost:8080
echo       按 Ctrl+C 停止服务
echo.
call mvn spring-boot:run

pause