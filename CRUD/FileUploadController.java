package com.estraight.controller.backstage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.estraight.controller.base.BaseController;
import com.estraight.entity.Page;
import com.estraight.service.backstage.FileUploadService;
import com.estraight.util.Const;
import com.estraight.util.DateUtil;
import com.estraight.util.FileDownload;
import com.estraight.util.FileUpload;
import com.estraight.util.Jurisdiction;
import com.estraight.util.PageData;
import com.estraight.util.PathUtil;
@Controller
@RequestMapping("/fileupload")
public class FileUploadController extends BaseController{
	String menuUrl = "fileupload/list"; //菜单地址(权限用)
	
	@Resource(name="fileUploadService")
	private FileUploadService fileUploadService;
	
	
	
	/**
	 * 
	 * @Description : 文件list
	 * @param : @param mv
	 * @param : @param page
	 * @param : @return  
	 * @return : ModelAndView 
	 * @throws : 
	 * @author : 351019187@qq.com（liusheng）
	 */
	@RequestMapping("/list")
	public ModelAndView listFile(ModelAndView mv,Page page){
		PageData pd = this.getPageData();
		page.setPd(pd);
		List<PageData> dataList = null;
		try {
			dataList = this.fileUploadService.getDataList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("dataList", dataList);
		mv.addObject("pd", pd);
		Map<String, String> temp = getHC();
		mv.addObject(Const.SESSION_QX, temp);
		mv.setViewName("/backstage/fileupload/file_list");
		return mv;
	}
	
	@RequestMapping(value = "/goUpdate")
	public ModelAndView goUpdate() {
		logBefore(logger, "去修改文件上传");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = fileUploadService.getCurrentFileData(pd);
			mv.setViewName("backstage/fileupload/file_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(ModelAndView mv,@RequestParam(required=true) String filename,
			@RequestParam(required=true) MultipartFile file_url,
			@RequestParam(required=true) String before_file_url,
			@RequestParam(required=true) Integer id
			)  {
		logBefore(logger, "修改文件页面");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		try {
			PageData pd = new PageData();
			pd = this.getPageData();
			if(file_url.getSize()!=0){
			
			//String  showffile = DateUtil.getDays(), showfileName = "";
			String showfilePath = PathUtil.getClasspath() + Const.FILEPATHFILE ;		//文件上传路径
			String showfileName = FileUpload.fileUp(file_url, showfilePath, filename+this.get32UUID());				//执行上传
			pd.put("file_url", Const.FILEPATHFILE + showfileName);
			}else{
				pd.put("file_url", before_file_url);
			}
		    pd.put("id",id);
			pd.put("filename",filename);
		    fileUploadService.updateCurrentFileData(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("backstage/fileupload/file_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			e.printStackTrace();
		}						
		return mv;
	}
	
	@RequestMapping(value = "/save")
	public ModelAndView save(ModelAndView mv,@RequestParam(required=true) String filename,
			@RequestParam(required=true) MultipartFile file_url
			) {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		}
		//多加一个文件夹
		/*String  showffile = DateUtil.getDays(), showfileName = "";
		String showfilePath = PathUtil.getClasspath() + Const.FILEPATHFILE + showffile;		//文件上传路径
		showfileName = FileUpload.fileUp(file_url, showfilePath, filename);	*/			//执行上传
		
		String showfilePath = PathUtil.getClasspath() + Const.FILEPATHFILE ;		//文件上传路径
		String showfileName = FileUpload.fileUp(file_url, showfilePath, filename+this.get32UUID());
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("file_url",Const.FILEPATHFILE + showfileName);
		pd.put("filename",filename);
        pd.put("deleteid", 0);  //是否删除，0否 1是  默认0
		try {
			fileUploadService.addCurrentFileData(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/del")
	public Object delete(){
		PageData pd = this.getPageData();
		pd.put("deleteid", 1);
		String result = "00";
		try {
			fileUploadService.deleteCurrentFileData(pd);
			result ="01";
		} catch (Exception e) {
			result ="00";
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/downDoc")
	public void downFile(HttpServletResponse response,String file_url,String filename)throws Exception{
		String file_url_alone[] =  file_url.split("/");
		String file_type = file_url_alone[file_url_alone.length-1];
		//后缀名
		String type = file_type.substring(file_type.indexOf("."));
		System.out.println(file_type);
		System.out.println(filename);
		FileDownload.fileDownload(response, PathUtil.getClasspathFile() + Const.FILEPATHFILE + file_type, filename+type);
		
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
}
