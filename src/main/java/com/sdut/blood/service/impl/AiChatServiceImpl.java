package com.sdut.blood.service.impl;

import com.sdut.blood.service.AiChatService;
import org.springframework.stereotype.Service;

/**
 * 智能问答服务实现
 * 如需接入Spring AI，取消注释并注入ChatModel即可
 */
@Service
public class AiChatServiceImpl implements AiChatService {

    // @Resource
    // private OpenAiChatModel chatModel;
    //
    // @Resource
    // private String systemPrompt;

    @Override
    public String chat(String question) {
        // 接入Spring AI后替换为真实调用
        // return chatModel.call(systemPrompt + "\n用户问题：" + question);
        return "您好，关于「" + question + "」的问题，建议您咨询现场医护人员获取准确解答。";
    }
}