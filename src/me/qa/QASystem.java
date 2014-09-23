package me.qa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import me.common.ComUtils;
import me.common.WordNet;
import me.common.filter.FilterChain;
import me.common.filter.StopWordFilter;
import me.common.filter.TrimFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class QASystem {

	public static String STOP_LIST_FILE = "stoplist"+File.separator+"stoplist.txt";
	public static String PUNCTUATION_FILE = "stoplist"+File.separator+"punctuation.txt";

	static String BASE_DIR = "basedir";
	static String INPUT_DIR = null;
	static String OUT_DIR = null;

	public static String dict = "dict30";

	//	public static String separator = "\n";

	public static void main(String[] args) {
		try {
			init();
			Collection<File> files = FileUtils.listFiles(new File(INPUT_DIR), new String[]{"txt"}, false);

			StopWordFilter stopWordFilter = new StopWordFilter(STOP_LIST_FILE,PUNCTUATION_FILE);
			TrimFilter trimFilter = new TrimFilter(PUNCTUATION_FILE);
			FilterChain filterChain = new FilterChain(trimFilter,stopWordFilter);

			WordNet wordnet = new WordNet(dict);

			for (File file : files) {
				FileOutputStream out = new FileOutputStream(new File(OUT_DIR+File.separator+file.getName()));
				List<String> lines = IOUtils.readLines(new FileInputStream(file));
				for (String line : lines) {
					String[] arrWords = line.split(" ");
					for (String word : arrWords) {
						word = filterChain.apply(word);
						if(word==null)
							continue;
						Set<String> syms = wordnet.getSimilarWords(word);
						write(syms,out," ");
					}
					IOUtils.write("\n", out);
				}
				IOUtils.closeQuietly(out);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Write.
	 *
	 * @param syms the syms
	 * @param out the out
	 * @param separator
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void write(Set<String> syms, FileOutputStream out, String separator) throws IOException {
		if(syms==null)
			return;
		for (String sym : syms) {
			IOUtils.write(sym+separator, out);
		}
	}
	private static void init() throws IOException {
		INPUT_DIR = BASE_DIR+File.separator+"input";
		OUT_DIR = BASE_DIR+File.separator+"output";
		ComUtils.ensureCleanDir(OUT_DIR);
	}
}
