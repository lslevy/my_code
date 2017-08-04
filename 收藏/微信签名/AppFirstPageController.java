package com.estraight.controller.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.sword.wechat4j.user.UserManager;

import com.estraight.controller.base.BaseController;
import com.estraight.entity.Page;
import com.estraight.service.app.AppDistributionService;
import com.estraight.service.app.AppMyuserService;
import com.estraight.service.app.AppProductService;
import com.estraight.service.app.AppFirstpageService;
import com.estraight.util.PageData;
import com.estraight.wechat.JsSdk;
import com.estraight.wechat.util.RandomUtil;
import com.estraight.wechat.util.WeixinAccessTokenUtil;
import com.estraight.wechat.util.WeixinUtil;

@Controller
@RequestMapping("/appFirstPage")
public class AppFirstPageController extends BaseController {

	@Resource(name = "appProductService")
	private AppProductService appProductService;
	@Resource(name = "appFirstpageService")
	private AppFirstpageService appFirstpageService;
	@Resource(name = "appMyuserService")
	private AppMyuserService appMyuserService;
	@Resource(name = "appDistributionService")
	private AppDistributionService appDistributionService;
	
//	@RequestMapping("/toIndex")
//	public ModelAndView toIndex(ModelAndView mv,Page page,HttpSession session){
//		PageData pd = this.getPageData();
//		page.setPd(pd);
//		page.setShowCount(5);
//		try{
//			String openid = WeixinUtil.getOpenId(pd.getString("code"));
//			if(openid == null || openid.trim().equals("")){//通过后退或者其他a标签进来 有session
//				openid = (String) session.getAttribute("openid");
//			}else{//第一次进来 没有session
//				PageData myuser = new PageData();
//				myuser.put("openid", openid);
//				PageData tempMyuser = this.appMyuserService.findByOpenId(myuser);
//				/*---保存没关注的用户----*/
//				if(tempMyuser == null || tempMyuser.get("id") == null){
//					this.appMyuserService.saveNoFollowMyuser(myuser);
//				}
//				/*---保存没关注的用户----*/
//				session.setAttribute("openid", openid);
//			}
//			pd.put("openid", openid);
//			List<PageData> productList = this.appProductService.getFirstPageProductList(page);
//			mv.addObject("productList", productList);
//			List<PageData> firstpageImgList = this.appFirstpageService.getFirstpageImg();
//			mv.addObject("firstpageImgList", firstpageImgList);
//			PageData myuser = this.appMyuserService.findByOpenId(pd);
//			mv.addObject("myuser", myuser);
//			/*---如果有分享人并且自己不是分销商----*/
//			if(Integer.valueOf((myuser.get("is_distribution")+"").trim()).intValue() == 0){
//				if(pd.get("share_openid") != null || session.getAttribute("share_openid") != null){
//					if(pd.get("share_openid") != null){
//						session.setAttribute("share_openid", pd.getString("share_openid"));
//					}
//					PageData share_pd = new PageData();
//					share_pd.put("openid", pd.get("share_openid"));
//					PageData share_myuser = this.appMyuserService.findByOpenId(share_pd);
//					mv.addObject("share_myuser", share_myuser);
//				}
//			}
//			/*---如果有分享人并且自己不是分销商----*/
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		mv.setViewName("/app/index");
//		mv.addObject("jsapi_ticket", new JsSdk().ticket());
//		return mv;
//	}
	@RequestMapping("/toIndex")
	public ModelAndView toIndex(ModelAndView mv,HttpSession session){
		PageData pd = this.getPageData();
		try{
			String openid = WeixinUtil.getOpenId(pd.getString("code"));
			//测试使用
			if(openid==""){
			openid = "oeh8ow1_hxGxqvrRn3A_l9HlMn8E";
			}
			if(openid != null && !openid.trim().equals("")){
				session.setAttribute("openid", openid);
			}
			if(pd.get("share_openid") != null){
				session.setAttribute("share_openid", pd.getString("share_openid"));
			}
			/*if(openid == null || openid.trim().equals("")){//通过后退或者其他a标签进来 有session
				openid = (String) session.getAttribute("openid");
			}else{//第一次进来 没有session
				PageData myuser = new PageData();
				myuser.put("openid", openid);
				PageData tempMyuser = this.appMyuserService.findByOpenId(myuser);
				---保存没关注的用户----
				if(tempMyuser == null || tempMyuser.get("id") == null){
					this.appMyuserService.saveNoFollowMyuser(myuser);
				}
				---保存没关注的用户----
				session.setAttribute("openid", openid);
			}*/
			/*pd.put("openid", openid);
			PageData myuser = this.appMyuserService.findByOpenId(pd);
			---如果有分享人并且自己不是分销商----
			if(myuser != null && Integer.valueOf((myuser.get("is_distribution")+"").trim()).intValue() == 0){
				if(pd.get("share_openid") != null || session.getAttribute("share_openid") != null){
					if(pd.get("share_openid") != null){
						session.setAttribute("share_openid", pd.getString("share_openid"));
					}
				}
			}
			---如果有分享人并且自己不是分销商----*/
		}catch(Exception e){
			e.printStackTrace();
		}
		mv.setViewName("/app/index3");
		return mv;
	}
	
	/**
	 * 
	 * @Description : 获取jsapiticket  以及组合链接
	 * @param : @param url
	 * @param : @return  
	 * @return : Map<String,Object> 
	 * @throws : 
	 * @author : 351019187@qq.com（liusheng）
	 */
	@ResponseBody
	@RequestMapping("/getJsApiTicket")
	public Map<String,Object> getJsApiTicket(String url){
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		try{
			String jsapi_ticket = new JsSdk().ticket();
			String nonceStr = RandomUtil.generateString(16);
			String timestamp = System.currentTimeMillis()/1000+"";
			String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr 
					+ "&timestamp=" + timestamp + "&url=" + url;
			String qm = SHA1(string1);
			map.put("qm", qm);
			map.put("jsapi_ticket", jsapi_ticket);
			map.put("nonceStr", nonceStr);
			map.put("timestamp", timestamp);
			result = "01";
		}catch(Exception e){
			result = "00";
			e.printStackTrace();
		}
		map.put("result", result);
		return map;
	}
	
	/**
	 * 
	 * @Description : 后台计算签名
	 * @param : @param decript
	 * @param : @return  
	 * @return : String 
	 * @throws : 
	 * @author : 351019187@qq.com（liusheng）
	 */
	public static String SHA1(String decript) {
		String back_signature ="";
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			back_signature= hexString.toString();
			//System.out.println(hexString.toString());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return back_signature;
	}
	
	public static void main(String[] args) {
		/*String str1 = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VL4a2Gy2NokBz-5aFF05oMYriL5cYJIEibEjfQnEKJ5J9yxiTzKP51ZB7_J32MJTig&noncestr=fhjt&timestamp=1461144910&url=http://14844b11o4.51mypc.cn/FHMYSQL/vote/index.html?opensid=o_52guHWFA8pq5XPw9WYgqaF1bAY";
		String signature = DigestUtils.sha1Hex(str1);
		System.out.println(signature);*/
		
		System.out.println((int) Math.floor(Math.random()*100000));
		System.out.println(System.currentTimeMillis());
		String str = "jsapi_ticket=kgt8ON7yVITDhtdwci0qeRLQ7V0ot86ZVAzAdGTxpY-Qq1lkudFQmjttx2zQRH7FJEyY6tBjeuxXz2XGxOsQtg&noncestr=NTWWGmg3W6yHmxih&timestamp=1465788086&url=http://localhost:80/estraight/appMyuser/getAppMyuserByOpenid";
//		System.out.println("SHA1:"+SHA1(str));
		
		System.out.println(System.currentTimeMillis());
	}
	
	
	/**
	 * 获取商品分区
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAreaProductList")
	public Map<String,Object> getAreaProductList(){
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = this.getPageData();
		String result = "";
		try{
			pd.put("showcount", Integer.valueOf(pd.getString("showcount")));
			List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			List<PageData> productArea = this.appProductService.getAllProductArea();
			for(int i=0; i<productArea.size(); i++){
				Map<String,Object> productMap = new HashMap<String,Object>();
				pd.put("product_area", productArea.get(i).get("id"));
				List<PageData> productList = this.appProductService.getFirstPageProductList(pd);
				productMap.put("product_area_name", productArea.get(i).get("name"));
				productMap.put("product_list", productList);
				resultList.add(productMap);
			}
			map.put("resultList", resultList);
			result = "01";
		}catch(Exception e){
			result = "00";
			e.printStackTrace();
		}
		map.put("result", result);
		return map;
	}
	
	/**
	 * 获取分区
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFirstpageProductArea")
	public Map<String,Object> getFirstpageProductArea(){
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		try{
			PageData pd = this.getPageData();
			List<PageData> productAreaList = this.appFirstpageService.getFirstpageProductArea(pd);
			map.put("productAreaList", productAreaList);
			result = "01";
		}catch(Exception e){
			result = "00";
			e.printStackTrace();
		}
		map.put("result", result);
		return map;
	}
	
	/**
	 * 获取轮播图
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFirstpageImg")
	public Map<String,Object> getFirstpageImg(){
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		try{
			List<PageData> firstpageImgList = this.appFirstpageService.getFirstpageImg();
			map.put("firstpageImgList", firstpageImgList);
			result = "01";
		}catch(Exception e){
			result = "00";
			e.printStackTrace();
		}
		map.put("result", result);
		return map;
	}
	
}
