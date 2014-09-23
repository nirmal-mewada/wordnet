package me.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.impl.file.PropertyNames;

public class WordNet {
	String dictionaty = null;
	WordNetDatabase database;

	public WordNet(String path) {
		this.dictionaty = path;
		System.setProperty(PropertyNames.DATABASE_DIRECTORY, path);
		database = WordNetDatabase.getFileInstance();
	}

	public WordNetDatabase getDatabase() {
		return database;
	}
	public Set<String> getSimilarWords(String word) {
		Set<String> res = null;
		Synset[] synsets = database.getSynsets(word);

		if (synsets.length > 0) {
			res = new HashSet<String>();

			for (int i = 0; i < synsets.length; i++) {
				String[] wordForms = synsets[i].getWordForms();
				res.addAll(Arrays.asList(wordForms));
				//				System.out.println((i+1)+". "+StringUtils.join(wordForms,","));
				//				System.out.println("   -" + synsets[i].getDefinition());
				//				System.out.println();
			}
		}
		return res;
	}

}
