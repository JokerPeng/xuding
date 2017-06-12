package com.xuding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuding.dao.MovieDao;
import com.xuding.model.Movie;
import com.xuding.service.MovieService;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	@Override
	public int add(Movie movie) {
		return movieDao.add(movie);
	}

	@Override
	public int delete(int id) {
		return movieDao.delete(id);
	}

	@Override
	public List<Map<String, Object>> queryLikeTitle(String title) {
		return movieDao.queryLikeTitle(title);
	}

	@Override
	public Map<String, Object> queryById(int id) {
		return movieDao.queryById(id);
	}

}
