package com.xuding.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.xuding.model.Movie;
import com.xuding.service.MovieService;

@Controller
@RequestMapping("/movieController")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	private final static SimpleDateFormat serialTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	@RequestMapping("toAdd")
	public String toAdd(){
		return "/add";
	}
	
	@RequestMapping("/addMovie")
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request){
		Map<String,Object> rstMap = new HashMap<String,Object>();
		rstMap.put("result", false);
		rstMap.put("msg", "添加失败！");
		InputStream is = null;
		FileOutputStream fos = null;
		Movie movie = new Movie();
	    movie.setTitle(request.getParameter("title"));
		try{
			//创建一个通用的多部分解析器  
		    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		    //判断 request 是否有文件上传,即多部分请求  
		    if(multipartResolver.isMultipart(request)){  
		      //转换成多部分request	
		      MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
		      //取得request中的所有文件名  
		      Iterator<String> iter = multiRequest.getFileNames();  
		      
		      while(iter.hasNext()){  
		        //取得上传文件  
		        MultipartFile file = multiRequest.getFile(iter.next()); 
		        String fileName = file.getOriginalFilename();
		        String suffix = fileName.substring(fileName.lastIndexOf("."));
		        is = file.getInputStream();
		        String pathStr = request.getRealPath("");
		        String staticPath = "static" + File.separator + "file" + File.separator;
		        String servicePath = pathStr + File.separator;
		        String savePath = servicePath + staticPath;
		        File fileSavePath = new File(savePath);
		        if(!fileSavePath.exists()){
		        	fileSavePath.mkdirs();
		        }
		        String newFileName = getSerialNum() + suffix;
		        fos = new FileOutputStream(savePath + newFileName);
		        
		        byte b[] = new byte[1024];
		        int len = 0;
		        while((len = is.read(b)) > 0){
		        	fos.write(b, 0, len);
		        }
		        
		        movie.setMoviePath(staticPath + newFileName);
		      }  
		    } 
		    
			int count = movieService.add(movie);
			if(count > 0){
				rstMap.put("result", true);
				rstMap.put("msg", "添加成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(is != null) is.close();
				if(fos != null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return rstMap;
	}
	
	public static String getSerialNum() {

		return serialTime.format(new Date()) + new Random().nextInt(100);
	}
	
	@RequestMapping("deleteMovie")
	@ResponseBody
	public Map<String,Object> delete(@RequestParam int id){
		Map<String,Object> rstMap = new HashMap<String,Object>();
		rstMap.put("result", false);
		rstMap.put("msg", "删除失败！");
		
		int count = movieService.delete(id);
		if(count > 0){
			rstMap.put("result", true);
			rstMap.put("msg", "删除成功！");
		}
		
		return rstMap;
	}
	
	@RequestMapping("/queryMovieList")
	@ResponseBody
	public String queryMovieList(@RequestParam String title){
		List<Map<String,Object>> list = movieService.queryLikeTitle(title);
		
		return new Gson().toJson(list);
	}
	
	@RequestMapping("/toShow")
	public ModelAndView toShow(@RequestParam int id){
		ModelAndView mv = new ModelAndView("/show");
		mv.addObject("movie",movieService.queryById(id));
		return mv;
	}
	
}
