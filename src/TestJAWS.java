import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.impl.file.PropertyNames;

/**
 * Displays word forms and definitions for synsets containing the word form
 * specified on the command line. To use this application, specify the word form
 * that you wish to view synsets for, as in the following example which displays
 * all synsets containing the word form "airplane": <br>
 * java TestJAWS airplane
 */
public class TestJAWS {
	public static String DB_PATH = "D:\\Mallet\\QA\\Wordnet\\dict30";

	/**
	 * Main entry point. The command-line arguments are concatenated together
	 * (separated by spaces) and used as the word form to look up.
	 */
	public static void main(String[] args) {

		System.setProperty(PropertyNames.DATABASE_DIRECTORY, DB_PATH);

		String wordForm = "test";

		WordNetDatabase database = WordNetDatabase.getFileInstance();
		Synset[] synsets = database.getSynsets(wordForm);


		if (synsets.length > 0) {
			System.out.println("The following synsets contain '" + wordForm	+ "' or a possible base form " + "of that text:");
			for (int i = 0; i < synsets.length; i++) {
				System.out.println("");
				String[] wordForms = synsets[i].getWordForms();
				for (int j = 0; j < wordForms.length; j++) {
					System.out.print((j > 0 ? ", " : "") + wordForms[j]);
				}
				System.out.println(": " + synsets[i].getDefinition());
			}
		} else {
			System.err.println("No synsets exist that contain "
					+ "the word form '" + wordForm + "'");
		}

	}

}