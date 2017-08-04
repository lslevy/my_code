package com.estraight.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import net.sf.json.JSONObject;



public class HuanXinUtil {
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String urlNameString) {//String url, String param
        String result = "";
        BufferedReader in = null;
        try {
            //String urlNameString = url + "?" + param;
        	//String urlNameString ="https://a1.easemob.com/yshl/levy/token?grant_type=client_credentials&client_id=YXA6F6B4AACNEeaVFtfzWswHBw&client_secret=YXA65VoMlfrIkXzueaBek_2zUW_wZ-8";
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost() {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	//String url = Const.APP_URL;
        	String url = "https://a1.easemob.com/yshl/levy/token?grant_type=client_credentials&client_id=YXA6F6B4AACNEeaVFtfzWswHBw&client_secret=YXA65VoMlfrIkXzueaBek_2zUW_wZ-8";
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数(参数都写在hxconf中了)
            //out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    
    
    
    public static String appadd(String username,String password) {
    	String ADD_URL ="https://a1.easemob.com/yshl/levy/users";
    	String result = "01";
        try {
            //创建连接
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            JSONObject obj = new JSONObject();
            obj.element("username", username);
            obj.element("password", password);

            out.writeBytes(obj.toString());
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result="00";
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result="00";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result="00";
        }
		return result;

    }
    
    
    
    
    
    /**
     * 
     * @Description :Http中使用 PUT 方式
     * @param : @param username
     * @param : @param password
     * @param : @return  
     * @return : String 
     * @throws : 
     * @author : 351019187@qq.com（liusheng）
     */
    public static String ModifyPwd(String username,String password) {
    	String url = "https://a1.easemob.com/yshl/levy/users/"+username+"/password";
    	String res = "";
    	try {
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpPut put = new HttpPut(url);
    	put.setHeader("Content-type", "application/json;charset=UTF-8");
    	put.setHeader("Authorization","Bearer YWMtI3VlzgEnEea9v3msJ9JxLwAAAVVCowoqJmbfEtexVypqX5hFmYn5mOHm5Pw");
    	
    	JSONObject obj = new JSONObject();
        obj.element("newpassword", password);
        
    	StringEntity params =new StringEntity(obj.toString());
    	params.setContentType("application/json;charset=UTF-8");
    	put.setEntity(params);


    	HttpResponse response = client.execute(put);
    	int code = response.getStatusLine().getStatusCode();
    	if(code==200){
    		System.out.println("环信密码修改成功");
    		res="01";
    	}else{
    		System.out.println("环信密码修改失败");
    		res="00";
    	}
    	System.out.println("Response Code:"+code);
    	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    	result.append(line);
    	}
    	System.out.println("result:"+result);
	    } catch (Exception e) {
	    	res="00";
			e.printStackTrace();
		}
    	return res;
}

}