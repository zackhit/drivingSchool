package drivingSchool.drivingSchool.util;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
* 消息处理工具类
* Created by xdp on 2016/1/26.
*/
public class MessageHandlerUtil {

   /**
    * 解析微信发来的请求（XML）
    * @param request
    * @return map
    * @throws Exception
    */
   public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
       // 将解析结果存储在HashMap中
       Map<String,String> map = new HashMap<String,String>();
       // 从request中取得输入流
       InputStream inputStream = request.getInputStream();
       System.out.println("获取输入流");
       // 读取输入流
       SAXReader reader = new SAXReader();
       Document document = reader.read(inputStream);
       // 得到xml根元素
       Element root = document.getRootElement();
       // 得到根元素的所有子节点
       List<Element> elementList = root.elements();

       // 遍历所有子节点
       for (Element e : elementList) {
           System.out.println(e.getName() + "|" + e.getText());
           map.put(e.getName(), e.getText());
       }

       // 释放资源
       inputStream.close();
       inputStream = null;
       return map;
   }

   // 根据消息类型 构造返回消息
   public static String buildXml(Map<String,String> map) {
       String responseMessage = "";
       String msgType = map.get("MsgType").toString();
       System.out.println("MsgType:" + msgType);
       if(msgType.toUpperCase().equals("TEXT")){
    	   responseMessage = handleTextMessage(map,  "Nick在进行微信的学习！");
       }else{
    	   responseMessage = handleTextMessage(map,  "请回复如下关键词：你好！帅哥！");
       }
       //消息类型
       /* MessageType messageEnumType = MessageType.valueOf(MessageType.class, msgType.toUpperCase());
       switch (messageEnumType) { 
           case TEXT:
               //处理文本消息
               responseMessage = handleTextMessage(map,"Nick在进行微信的学习！");
               break;
         case IMAGE:
               //处理图片消息
               responseMessage = handleImageMessage(map);
               break;
           case VOICE:
               //处理语音消息
               responseMessage = handleVoiceMessage(map);
               break;
           case VIDEO:
               //处理视频消息
               responseMessage = handleVideoMessage(map);
               break;
           case SHORTVIDEO:
               //处理小视频消息
               responseMessage = handleSmallVideoMessage(map);
               break;
           case LOCATION:
               //处理位置消息
               responseMessage = handleLocationMessage(map);
               break;
           case LINK:
               //处理链接消息
               responseMessage = handleLinkMessage(map);
               break;
           case EVENT:
               //处理事件消息,用户在关注与取消关注公众号时，微信会向我们的公众号服务器发送事件消息,开发者接收到事件消息后就可以给用户下发欢迎消息
               responseMessage = handleEventMessage(map);
           default:
               break;
       }*/
           //返回响应消息
           return responseMessage;

   }

   /**
    * 构造文本消息
    *
    * @param map
    * @param content
    * @return
    */
   private static String handleTextMessage(Map<String,String> map, String content) {
       //发送方帐号
       String fromUserName = map.get("FromUserName");
       // 开发者微信号
       String toUserName = map.get("ToUserName");
       /**
        * 文本消息XML数据格式
        * <xml>
            <ToUserName><![CDATA[toUser]]></ToUserName>
            <FromUserName><![CDATA[fromUser]]></FromUserName>
            <CreateTime>1348831860</CreateTime>
            <MsgType><![CDATA[text]]></MsgType>
             <Content><![CDATA[this is a test]]></Content>
             <MsgId>1234567890123456</MsgId>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" + "</xml>",
                fromUserName, toUserName, getUtcTime(), content);
    }

    private static String getUtcTime() {
        Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// 设置显示格式
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {

        }
        return String.valueOf(dd);
    }
 }
