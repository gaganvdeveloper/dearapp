package com.google.dearapp.util;

import java.util.Comparator;

import com.google.dearapp.dto.MatchedUser;

public class SortByAgeDesc implements Comparator<MatchedUser> {

	@Override
	public int compare(MatchedUser o1, MatchedUser o2) {
		if (o1.getAgeDifference() < o2.getAgeDifference())
			return -1;
		else if (o1.getAgeDifference() > o2.getAgeDifference())
			return 1;
		else {
			if (o1.getMatchingIntrests() < o2.getMatchingIntrests()) {
				return 1;
			} else if (o1.getMatchingIntrests() > o2.getMatchingIntrests())
				return -1;
		}
		return 0;
	}

}
