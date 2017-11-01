package com.model.goodss.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.model.goodss.client.UserClient;
import com.model.goodss.dao.GoodssDao;
import com.model.goodss.domain.Goodss;
import com.model.goodss.util.MyPage;
import com.model.goodss.util.ServiceException;
import com.model.goodss.web.GoodssForm;

@Service
@Transactional
public class GoodssService {
	@Autowired
	private GoodssDao goodssDao;
	@Autowired
	private UserClient userClient;
	
	public void save(String sessuserid, GoodssForm goodssForm){
		try {
			if (!StringUtils.isNotBlank(sessuserid))
				throw new ServiceException("user.require.login", "goodss");
			Goodss goodss = new Goodss(sessuserid);
			List<MultipartFile> files = goodssForm.getImage();
			List<String> fileNames = new ArrayList<String>();
			if(null != files && files.size() > 0){
				for (MultipartFile multipartFile : files){
					String fileName =  new Date().getTime() + multipartFile.getOriginalFilename();
					String Path =Class.class.getClass().getResource("/").getPath()+ "static/images/";
					File file = new File(Path);
					if(!file.isDirectory()){
						file.mkdir();
					}
					String filePath = Path+fileName;
					if(files.isEmpty()){
						
					}else{
						File mf = new File(filePath);
						try {
							multipartFile.transferTo(mf);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					fileNames.add(fileName);
				}
				goodss.setImages(fileNames);
			}
			BeanUtils.copyProperties(goodssForm, goodss, Goodss.class);
			goodssDao.save(goodss);
		} catch (Exception e) {
		}
	}
	
	public void delete(String sessuserid, String id){
		try {
			Goodss goodss = goodssDao.findById(id);
			if (!StringUtils.equals(goodss.getUserid(), sessuserid))
				throw new ServiceException("user.permission.deny", "goodss");
			goodssDao.delete(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Goodss findById(String sessuserid, String id){
		try {
			Goodss goodss = goodssDao.findById(id);
			goodss.setUser(userClient.findById(goodss.getUserid()));
			return goodss;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void update(String sessuserid, String id, GoodssForm goodssForm){
		try {
			Goodss goodss = goodssDao.findById(id);
			if (!StringUtils.equals(goodss.getUserid(), sessuserid))
				throw new ServiceException("user.permission.deny", "goodss");
			BeanUtils.copyProperties(goodssForm, goodss, Goodss.class);
			goodssDao.merge(goodss);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public MyPage<Goodss> findAll(String userid, int page, int pagesize, String keyword){
		try {
			return goodssDao.findAll(userid, page, pagesize, keyword);
		} catch (Exception e) {
			throw e;
		}
	}
	private String userid;
	public MyPage<Goodss> findAll(int page, int pagesize, String keyword){
		try {
			return goodssDao.findAll(userid, page, pagesize, keyword);
		} catch (Exception e) {
			throw e;
		}
	}

}
