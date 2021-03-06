package com.efeiyi.ec.community.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.efeiyi.ec.community.user.model.Message;
import com.efeiyi.ec.community.work.model.WorkDiscuss;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/1/25.
 *
 */
public class EfeiyiPush {
    private static Logger logger = Logger.getLogger(EfeiyiPush.class);

    public static final String TITLE = "融艺投";
    public static final String ALERT = "评论了你";
    public static final String MSG_CONTENT = "融艺投祝大家新春快乐";
    public static final String REGISTRATION_ID = "18071adc030f0bd22f9";
    public static final String TAG = "tag_api";

    //public  static JPushClient jpushClient=null;

    public static void SendPush(String appKey ,String masterSecret, Message message) {

        JPushClient jpushClient = new JPushClient(masterSecret, appKey);


        PushPayload payload=buildPushObject_android_and_ios(message);
        try {
            System.out.println(payload.toString());
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result+"................................");

            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            e.printStackTrace();
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }
    }


    public static void SendPushComment(String appKey ,String masterSecret, WorkDiscuss comment) {

        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushPayload payload=buildPushObject_android_and_ios_withComment(comment);
        try {
            System.out.println(payload.toString());
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result+"................................");

            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            e.printStackTrace();
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }
    }




    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }

    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }

    public static PushPayload buildPushObject_android_and_ios(Message message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                //.setAudience(Audience.all())
                .setAudience(Audience.registrationId(REGISTRATION_ID))
                .setNotification(Notification.newBuilder()
                        .setAlert(MSG_CONTENT)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(TITLE)
                                .setAlert(ALERT+message.getContent())
                                .addExtra("extra_key", "extra_value")
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }


    public static PushPayload buildPushObject_android_and_ios_withComment(WorkDiscuss comment) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                        //.setAudience(Audience.registrationId(REGISTRATION_ID))
                .setNotification(Notification.newBuilder()
                        .setAlert(MSG_CONTENT)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(TITLE)
//                                .setAlert(comment.getFatherComment().getCreator().getName()+ALERT+comment.getContent())
                                .addExtra("extra_key", "extra_value")
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.registrationId("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
//                .setMessage(Message.content(MSG_CONTENT))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(cn.jpush.api.push.model.Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
}
