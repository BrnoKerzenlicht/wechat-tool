package com.june.common;

import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.AccountType;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.QRCodeUtils;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NsipWeChatBot extends WeChatBot implements ApplicationListener<ContextRefreshedEvent> {

    private static NsipWeChatBot instance;
    private static Config config = Config.me().autoLogin(true).showTerminal(true);

    public NsipWeChatBot() {
        super(config);
    }

    /**
     * 绑定所有消息
     * @param message 接收到的消息
     */
    @Bind(msgType = MsgType.ALL, accountType = { AccountType.TYPE_GROUP, AccountType.TYPE_MP,
            AccountType.TYPE_SPECIAL })
    public void handleMsg(WeChatMessage message) {
        switch (message.getMsgType()) {
            case TEXT:
                log.info("接收到 [{}] 的文本消息: {}", message.getName(), message.getText());
                break;
            case IMAGE:
                log.info("接收到 [{}] 的图片消息: {}", message.getName(), message.getImagePath());
                log.info("图片是否为二维码判断结果:{}", QRCodeUtils.isQRCode(new File(message.getImagePath())));
                break;
            default:
                log.info("接收到 [{}] 的 {} 消息: {}", message.getName(), message.getMsgType(), message.getText());
                break;
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //防止两次加载
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            initBot();
        }
    }

    @Override
    protected void other() {
        log.info("{} [initBot] Bot 启动成功");
    }

    public static NsipWeChatBot getInstance() {
        if (instance == null) {
            log.info("{} [getInstance] instance为空,开始初始化Bot");
            initBot();
            log.info("{} [getInstance] instance为空,初始化Bot完成");
        }
        if (!instance.isRunning()) {
            log.info("{} [getInstance] instance已经停止,开始启动Bot");
            instance.start();
            log.info("{} [getInstance] instance已经停止,启动Bot完成");
        }
        return instance;
    }

    private static void initBot() {
        log.info("{} [initBot] 初始化Bot开始");
        if (instance == null) {
            synchronized (NsipWeChatBot.class) {
                if (instance == null) {
                    instance = new NsipWeChatBot();
                }
            }
        }
        log.info("{} [initBot] 初始化Bot结束");

        if (instance != null && !instance.isRunning()) {
            instance.start();
            log.info("{} [initBot] Bot 启动结束");
        }
    }
}
