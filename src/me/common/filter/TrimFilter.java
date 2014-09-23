package me.common.filter;

public class TrimFilter extends AbstractListFilter{

	public TrimFilter(String...files) {
		super(files);
	}
	@Override
	public String apply(String word) {
		if(word==null || word.length()==0)
			return null;
		if(stopWords.contains(word.charAt(0)+""))
			word = word.substring(1);

		if(word==null || word.length()==0)
			return null;

		if(stopWords.contains(word.charAt(word.length()-1)+""))
			word = word.substring(0,word.length()-1);
		return word;
	}
	public static void main(String[] args) {
		String test = ")";
		TrimFilter trimFilter = new TrimFilter();
		trimFilter.addWords(",","(",")");
		System.out.println(trimFilter.apply(test));


	}

}
