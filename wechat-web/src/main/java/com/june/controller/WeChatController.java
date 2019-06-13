package com.june.controller;

import com.alibaba.fastjson.JSON;
import com.june.common.NsipWeChatBot;
import com.june.common.ResultData;
import com.june.common.ReturnCodeEnum;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/wechat/message")
public class WeChatController {

    private static final String MODULE_NAME = "[wechat-message]";

    @RequestMapping("/send")
    public ResultData<Boolean> sendTextMessage(HttpServletRequest request) {
        log.info("{} [sendTextMessage] 收到请求:{}", MODULE_NAME, JSON.toJSONString(request.getParameterMap()));

        String toNickName = request.getParameter("nickName");
        String content = request.getParameter("content");

        if (StringUtils.isEmpty(toNickName)||StringUtils.isEmpty(content)) {
            log.info("{} [sendTextMessage] 参数错误 toNickName:{},content:{}", MODULE_NAME, toNickName, content);
            return new ResultData<>(ReturnCodeEnum.ERROR_PARAM);
        }

        try {
            boolean ret = NsipWeChatBot.getInstance().sendMsgByName(toNickName, content);
            log.info("{} [sendTextMessage] ret:{}", MODULE_NAME, ret);
            return new ResultData<>(ReturnCodeEnum.SUCCESS, ret);
        }
        catch (Exception e) {
            log.error("{} [sendTextMessage] 出现异常", MODULE_NAME, e);
            return new ResultData<>(ReturnCodeEnum.ERROR_SYSTEM);
        }
    }
}
