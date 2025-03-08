package com.google.dearapp.util;

import java.util.Comparator;

import com.google.dearapp.dto.MatchingUser;

public class SortByAgeDifferenceAsc implements Comparator<MatchingUser> {

	@Override
	public int compare(MatchingUser o1, MatchingUser o2) {
		if (o1.getAgeDifference() < o2.getAgeDifference()) {
			return -1;
		} else if (o1.getAgeDifference() > o2.getAgeDifference()) {
			return 1;
		} else {
			if (o1.getMatchingIntrestCount() < o2.getMatchingIntrestCount()) {
				return -1;
			} else if (o1.getMatchingIntrestCount() > o2.getMatchingIntrestCount()) {
				return 1;
			}
		}
		return 0;
	}

}
