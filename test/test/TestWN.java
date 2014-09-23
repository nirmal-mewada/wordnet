package test;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.WordNetException;
import edu.smu.tspell.wordnet.impl.file.PropertyNames;


public class TestWN {

	public static String DB_PATH = "D:\\WorkSpace_\\nirmal_workspace\\Wordnet\\dict30";

	public static void main(String[] args) {
		try {
			System.setProperty(PropertyNames.DATABASE_DIRECTORY,DB_PATH);
			NounSynset nounSynset;
			NounSynset[] hyponyms;

			WordNetDatabase database = WordNetDatabase.getFileInstance();
			Synset[] synsets = database.getSynsets("fly", SynsetType.NOUN);
			for (int i = 0; i < synsets.length; i++) {
				nounSynset = (NounSynset)(synsets[i]);
				hyponyms = nounSynset.getHyponyms();
				System.out.println(nounSynset.getWordForms()[0] +
						": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms");
			}
		} catch (WordNetException e) {
			e.printStackTrace();
		}
	}
}
