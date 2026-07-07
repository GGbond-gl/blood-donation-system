package com.sdut.blood.config;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class PortCheckerConfig {

    private static final Logger logger = LoggerFactory.getLogger(PortCheckerConfig.class);

    @Value("${server.port:8080}")
    private int serverPort;

    @PostConstruct
    public void checkPort() {
        try {
            int pid = findProcessByPort(serverPort);
            if (pid > 0) {
                logger.warn("端口 {} 已被进程 {} 占用，正在尝试终止...", serverPort, pid);
                if (killProcess(pid)) {
                    logger.info("进程 {} 已成功终止", pid);
                    Thread.sleep(2000);
                } else {
                    logger.error("终止进程 {} 失败，请手动处理", pid);
                }
            }
        } catch (Exception e) {
            logger.debug("端口检查过程中发生异常: {}", e.getMessage());
        }
    }

    private int findProcessByPort(int port) {
        try {
            String command = "netstat -ano | findstr \":" + port + " \" | findstr LISTENING";
            Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 5) {
                    return Integer.parseInt(parts[parts.length - 1]);
                }
            }
            reader.close();
            process.waitFor();
        } catch (Exception e) {
            logger.debug("查找端口占用进程失败: {}", e.getMessage());
        }
        return -1;
    }

    private boolean killProcess(int pid) {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"taskkill", "/F", "/PID", String.valueOf(pid)});
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            logger.debug("终止进程失败: {}", e.getMessage());
            return false;
        }
    }
}