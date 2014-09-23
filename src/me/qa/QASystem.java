package me.qa;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import me.common.ComUtils;
import me.common.FileIOHandler;
import me.common.MappedFile;
import me.common.WordNet;
import me.common.filter.FilterChain;
import me.common.filter.StopWordFilter;
import me.common.filter.TrimFilter;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Stopwatch;

public class QASystem {

	public static String STOP_LIST_FILE = "stoplist"+File.separator+"stoplist.txt";
	public static String PUNCTUATION_FILE = "stoplist"+File.separator+"punctuation.txt";

	static String BASE_DIR = "basedir";
	static String INPUT_DIR = null;
	static String OUT_DIR = null;

	public static String dict = "dict30";

	//	public static String separator = "\n";

	public static void main(String[] args) {
		Stopwatch stopWatch = Stopwatch.createStarted();
		try {
			init();

			StopWordFilter stopWordFilter = new StopWordFilter(STOP_LIST_FILE,PUNCTUATION_FILE);
			TrimFilter trimFilter = new TrimFilter(PUNCTUATION_FILE);
			FilterChain filterChain = new FilterChain(trimFilter,stopWordFilter);

			WordNet wordnet = new WordNet(dict);
			FileIOHandler ioHandler = new FileIOHandler(BASE_DIR, "input","output");
			for (MappedFile file : ioHandler.listFiles()) {
				List<String> lines = file.readLines();
				for (String line : lines) {
					String[] arrWords = line.split(" ");
					for (String word : arrWords) {
						word = filterChain.apply(word);
						if(word==null)
							continue;
						Set<String> syms = wordnet.getSimilarWords(word);
						file.write(StringUtils.join(syms," "));
					}
					file.write("\n");
				}
				file.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			stopWatch.stop();
			System.out.println("Completed: "+stopWatch.toString());
		}

	}


	private static void init() throws IOException {
		INPUT_DIR = BASE_DIR+File.separator+"input";
		OUT_DIR = BASE_DIR+File.separator+"output";
		ComUtils.ensureCleanDir(OUT_DIR);
	}
}
