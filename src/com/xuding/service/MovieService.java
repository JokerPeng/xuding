package com.xuding.service;

import java.util.List;
import java.util.Map;

import com.xuding.model.Movie;

public interface MovieService {

int add(Movie movie);
	
	int delete(int id);
	
	List<Map<String,Object>> queryLikeTitle(String title);
	
	Map<String,Object> queryById(int id);
}
