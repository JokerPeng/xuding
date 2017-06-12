package com.xuding.dao;

import java.util.List;
import java.util.Map;

import com.xuding.model.Movie;

public interface MovieDao {
	int add(Movie movie);
	
	int delete(int id);
	
	List<Map<String,Object>> queryLikeTitle(String title);
	
	Map<String,Object> queryById(int id);
}
