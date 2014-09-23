package me.common.filter;

import java.util.ArrayList;
import java.util.List;

import me.qa.QASystem;

public class FilterChain implements Filter{
	List<Filter> lstFilters = null;

	public FilterChain(List<Filter> lstFilters) {
		this.lstFilters = lstFilters;
	}
	public FilterChain(Filter...filters) {
		addFilter(filters);
	}
	public void addFilter(Filter...filters) {
		if(lstFilters==null)
			this.lstFilters = new ArrayList<Filter>();
		for (Filter filter : filters) {
			lstFilters.add(filter);
		}

	}

	@Override
	public String apply(String word) {
		for (Filter filter : lstFilters) {
			word = filter.apply(word);
			if(word==null)
				break;
		}
		return word;
	};

	public static void main(String[] args) {
		//		TrimFilter trimFilter = new TrimFilter();
		//		trimFilter.addWords("(",")");
		//		StopWordFilter stopWordFilter = new StopWordFilter();
		//		stopWordFilter.addWords("is","a");
		//
		//		FilterChain filterChain = new FilterChain(trimFilter,stopWordFilter);
		//		System.out.println(filterChain.apply("(is)"));
		StopWordFilter stopWordFilter = new StopWordFilter(QASystem.STOP_LIST_FILE,QASystem.PUNCTUATION_FILE);
		TrimFilter trimFilter = new TrimFilter(QASystem.STOP_LIST_FILE,QASystem.PUNCTUATION_FILE);
		FilterChain filterChain = new FilterChain(trimFilter,stopWordFilter);
	}

}
