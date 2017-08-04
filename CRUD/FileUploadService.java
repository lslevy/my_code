package com.estraight.service.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.estraight.dao.DaoSupport;
import com.estraight.entity.Page;
import com.estraight.util.PageData;

@Service("fileUploadService")
public class FileUploadService {
	@Resource(name="daoSupport")
	private DaoSupport dao;

	public List<PageData> getDataList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("FileUploadMapper.listPage", page);
	}

	public PageData getCurrentFileData(PageData pd) throws Exception  {
		return (PageData) dao.findForObject("FileUploadMapper.getCurrentFileData", pd);
	}
	
	
	public void addCurrentFileData(PageData pd)throws Exception {
	  dao.save("FileUploadMapper.addCurrentFileData", pd);
		
	}
	
	public void updateCurrentFileData(PageData pd) throws Exception {
		dao.update("FileUploadMapper.updateCurrentFileData", pd);
		
	}

	public void deleteCurrentFileData(PageData pd)throws Exception {
		dao.update("FileUploadMapper.deleteCurrentFileData", pd);
		
	}
}
