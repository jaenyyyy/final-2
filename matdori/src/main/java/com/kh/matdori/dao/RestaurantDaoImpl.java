package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.ResImagesDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.RestaurantDetailVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RestaurantDaoImpl implements RestaurantDao {

	@Autowired
	private SqlSession sqlSession;

	// 등록 다시
	@Override
	public void insert(RestaurantDto restaurantDto) {
		sqlSession.insert("restaurant.save", restaurantDto);
	}

	@Override
	public int sequence() {
		return sqlSession.selectOne("restaurant.sequence");
	}

	// 삭제
	@Override
	public void delete(int resNo) {
		int result = sqlSession.delete("restaurant.deleteByResNo", resNo);
		if (result == 0)
			throw new NoTargetException();
	}

	// 기본정보수정
	@Override
	public void edit(int resNo, RestaurantDto restaurantDto) {
		restaurantDto.setResNo(resNo);
		int result = sqlSession.update("restaurant.editResInfo", restaurantDto);
		if (result == 0)
			throw new NoTargetException();
	}

	// 상세조회
	@Override
	public RestaurantDto selectOne(int resNo) {
		RestaurantDto restaurantDto = sqlSession.selectOne("restaurant.find", resNo);
		if (restaurantDto == null)
			throw new NoTargetException();
		return restaurantDto;
	}

	@Override
	public RestaurantDetailVO selectDetail(int resNo) {
		return sqlSession.selectOne("restaurant.resDetail", resNo);
	}


    @Override
    public int sequenceAttach() {
        return sqlSession.selectOne("restaurant.sequenceAttach");
    }
    @Override
    public void insertResImage(int resNo, List<MultipartFile> resImages) {
        for (MultipartFile file : resImages) {
            if (!file.isEmpty()) {
                // attach_no 시퀀스를 가져옵니다.
                int attachNo = sqlSession.selectOne("restaurant.sequenceAttach");
                // 파일을 서버에 저장하는 로직을 추가해야 합니다.
                // 예: file.transferTo(new File(dir, file.getOriginalFilename()));

                // attach 테이블에 이미지 메타데이터를 저장합니다.
                AttachDto attachDto = new AttachDto();
                attachDto.setAttachNo(attachNo);
                attachDto.setAttachName(file.getOriginalFilename());
                attachDto.setAttachSize(file.getSize());
                attachDto.setAttachType(file.getContentType());
                sqlSession.insert("restaurant.insertAttach", attachDto);

                // restaurant_images 테이블에 이미지 삽입
                ResImagesDto resImage = new ResImagesDto();
                resImage.setResNo(resNo);
                resImage.setAttachNo(attachNo);
                sqlSession.insert("restaurant.insertResImage", resImage);
            }
        }
    }
	@Override
	public List<ResImagesDto> selectResImagesByResNo(int resNo) {
		return sqlSession.selectList("restaurant.selectResImagesList", resNo);
	}

	@Override
	public boolean deleteResImage(int attachNo) {
		return sqlSession.delete("restaurant.deleteResImage", attachNo) > 0;
	}

}
