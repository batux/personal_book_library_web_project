package com.personal.book.library.servicelayer;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.book.library.datalayer.model.LikeDegree;

@Service
public class LikeDegreeService {

	public List<LikeDegree> prepareLikeDegrees() {
		
		return Arrays.asList(LikeDegree.NOT_LIKE, LikeDegree.LIKED, LikeDegree.LIKED_TOO_MUCH);
	}
}
