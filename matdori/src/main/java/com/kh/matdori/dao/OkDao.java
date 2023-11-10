package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.OkDto;

public interface OkDao {

	void insert(OkDto okDto);

	void edit(int okNo, OkDto okDto);

	void delete(int okNo);

	List<OkDto> selectList();

}
