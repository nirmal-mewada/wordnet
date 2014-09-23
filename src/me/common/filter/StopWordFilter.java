package me.common.filter;


public class StopWordFilter extends AbstractListFilter {

	public StopWordFilter() {
		super();
	}
	public StopWordFilter(String...files) {
		super(files);
	}


	public boolean isStopWord(String word) {
		if(word==null)
			return true;
		return stopWords.contains(word.toLowerCase());
	}

	@Override
	public String apply(String word) {
		if(isStopWord(word))
			return null;
		else
			return word;
	}

}
