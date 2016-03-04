package imgutil.test;
//package test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import net.sf.json.JSONException;
//import net.sf.json.JSONObject;
//
//public class WeChatApiUtil {
//    // token �ӿ�(GET)
//    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
//    // �ز��ϴ�(POST)
//    private static final String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
//    // �ز�����:��֧����Ƶ�ļ�������(GET)
//    private static final String DOWNLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
//
//    public static String getTokenUrl(String appId, String appSecret) {
//        return String.format(ACCESS_TOKEN, appId, appSecret);
//    }
//
//    public static String getDownloadUrl(String token, String mediaId) {
//        return String.format(DOWNLOAD_MEDIA, token, mediaId);
//    }
//
//    /**
//     * ͨ�ýӿڻ�ȡTokenƾ֤
//     * @param appId
//     * @param appSecret
//     * @return
//     */
//    public static String getToken(String appId, String appSecret) {
//        if(appId==null||appSecret==null){
//            return null;
//        }
//
//        String token = null;
//        String tockenUrl = WeChatApiUtil.getTokenUrl(appId, appSecret);
//        String response = httpsRequestToString(tockenUrl, "GET", null);
//        JSONObject jsonObject = JSONObject.fromObject(response);
//        if (null != jsonObject) {
//            try {
//                token =jsonObject.getString("access_token");
//            } catch (JSONException e) {
//                token = null;// ��ȡtokenʧ��
//                logger.error(e);
//            }
//        }
//        return token;
//    }
//
//    /**
//     * ΢�ŷ������ز��ϴ�
//     * @param file  ������media
//     * @param token access_token
//     * @param type  typeֻ֧�����������ز�(video/image/voice/thumb)
//     */
//    public static JSONObject uploadMedia(File file, String token, String type) {
//   if(file==null||token==null||type==null){
//            return null;
//        }
//
//        if(!file.exists()){
//            logger.info("�ϴ��ļ�������,����!");
//            return null;
//        }
//
//        String url = UPLOAD_MEDIA;
//        JSONObject jsonObject = null;
//        PostMethod post = new PostMethod(url);
//        post.setRequestHeader("Connection", "Keep-Alive");
//        post.setRequestHeader("Cache-Control", "no-cache");
//        FilePart media = null;
//        HttpClient httpClient = new HttpClient();
//        //�����κ����͵�֤��
//        Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443); 
//        Protocol.registerProtocol("https", myhttps);
//
//        try {
//            media = new FilePart("media", file);
//            Part[] parts = new Part[] { new StringPart("access_token", token),
//                    new StringPart("type", type), media };
//            MultipartRequestEntity entity = new MultipartRequestEntity(parts,
//                    post.getParams());
//            post.setRequestEntity(entity);
//            int status = httpClient.executeMethod(post);
//            if (status == HttpStatus.SC_OK) {
//                String text = post.getResponseBodyAsString();
//                jsonObject = JSONObject.fromObject(text);
//            } else {
//                logger.info("upload Media failure status is:" + status);
//            }
//        } catch (FileNotFoundException execption) {
//            logger.error(execption);
//        } catch (HttpException execption) {
//            logger.error(execption);
//        } catch (IOException execption) {
//            logger.error(execption);
//        }
//        return jsonObject;
//    }
//
//    /**
//     * ��ý�����ؽӿ�
//     * @comment ��֧����Ƶ�ļ�������
//     * @param fileName  �زĴ洢�ļ�·��
//     * @param token     ��֤token
//     * @param mediaId   �ز�ID����Ӧ�ϴ����ȡ����ID��
//     * @return �ز��ļ�
//     */
//    public static File downloadMedia(String fileName, String token,
//            String mediaId) {
//        String url = getDownloadUrl(token, mediaId);
//        return httpRequestToFile(fileName, url, "GET", null);
//    }
//
//    /**
//     * ��http��ʽ��������,����������Ӧ����������ļ�
//     * @param path    ����·��
//     * @param method  ���󷽷�
//     * @param body    ��������
//     * @return ������Ӧ�Ĵ洢���ļ�
//     */
//    public static File httpRequestToFile(String fileName,String path, String method, String body) {
//        if(fileName==null||path==null||method==null){
//            return null;
//        }
//
//        File file = null;
//        HttpURLConnection conn = null;
//        InputStream inputStream = null;
//        FileOutputStream fileOut = null;
//        try {
//            URL url = new URL(path);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            conn.setRequestMethod(method);
//            if (null != body) {
//                OutputStream outputStream = conn.getOutputStream();
//                outputStream.write(body.getBytes("UTF-8"));
//                outputStream.close();
//            }
//
//            inputStream = conn.getInputStream();
//            if(inputStream!=null){
//                file = new File(fileName);
//            }else{
//                return file;
//            }
//
//            //д�뵽�ļ�
//            fileOut = new FileOutputStream(file);
//            if(fileOut!=null){
//                int c = inputStream.read();
//                while(c!=-1){
//                    fileOut.write(c);
//                    c = inputStream.read();
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e);
//        }finally{
//            if(conn!=null){
//                conn.disconnect();
//            }
//
//            /*
//             * ����ر��ļ���
//             * ����JDK����ʱ���ļ���ռ�����������޷�����
//             */
//            try {
//                inputStream.close();
//                fileOut.close();
//            } catch (IOException execption) {
//                logger.error(execption);
//            }
//        }
//        return file;
//    }
//
//    public static void main(String[] args) {
//        File f = new File("D:/test.png");
//        String appId = "";
//        String appSecret = "";
//        String token = WeChatApiUtil.getToken(appId, appSecret);
//        JSONObject o = WeChatApiUtil.uploadMedia(f, token.getAccessToken(), "image");
//        System.out.println(o.toString());
//
//        //���ظո��ϴ���ͼƬ��id����
//        String media_id = o.getString("media_id");
//        File t = WeChatApiUtil.downloadMedia("D:/"+media_id+".png", token.getAccessToken(), media_id);
//
//    }
//��
