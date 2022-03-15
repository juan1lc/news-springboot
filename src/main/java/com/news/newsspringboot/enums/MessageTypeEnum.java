package com.news.newsspringboot.enums;

/**
 * 消息类型枚举
 */
public enum MessageTypeEnum {
//     ********** 消息类型描述 ******************
//       code   description   example
//        0         通知       应用通知
//        1          @        mongo@了你
//        2       评论动态     mongo评论了你的动态
//        3       回复评论     mongo回复了你的评论
//        4       赞动态       mongo赞了你的动态
//        5       赞评论       mongo赞了你的评论
//    *****************************************

    NOTICE(0,"通知"),
    AT(1, "@"),
    COMMENT_MOVING(2,"评论动态"),
    REPLY_COMMENT(3, "回复评论"),
    LIKE_MOVING(4, "赞动态"),
    LIKE_COMMENT(5, "赞评论");



    private final int messageTypeCode;
    private final String messageTypeName;


    MessageTypeEnum(int messageTypeCode, String messageTypeName){
        this.messageTypeCode = messageTypeCode;
        this.messageTypeName = messageTypeName;
    }

    public int getMessageTypeCode() {
        return messageTypeCode;
    }

    public String getMessageTypeName() {
        return messageTypeName;
    }
}
