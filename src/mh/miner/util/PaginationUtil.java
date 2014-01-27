package mh.miner.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

	public static List<Integer> createPages(int pageIndex, int navSize, int totalPage) {
		List<Integer> pages = new ArrayList<Integer>();

		// set current page to the center
		int beginCnt = pageIndex - (navSize / 2);
		int endCnt = pageIndex + (navSize / 2) + 1;

		// adjust begin/end page
		if(beginCnt < 0) {
			beginCnt = 0;
			endCnt = navSize;
		}
		if(totalPage < endCnt) {
			beginCnt = totalPage < navSize ? 0 : totalPage - navSize;
			endCnt = totalPage;
		}

		// create page numbers
		for(int i = beginCnt; i < endCnt; i++) {
			pages.add(i);				
		}

		return pages;
	}
}
