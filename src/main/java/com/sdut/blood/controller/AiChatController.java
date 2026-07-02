package com.sdut.blood.controller;

import com.sdut.blood.common.result.Result;
import com.sdut.blood.service.AiChatService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 智能问答控制器
 */
@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    @Resource
    private AiChatService aiChatService;

    /**
     * 智能咨询问答
     */
    @PostMapping("/chat")
    public Result<String> chat(@RequestParam String question) {
        String answer = aiChatService.chat(question);
        return Result.success(answer);
    }
}