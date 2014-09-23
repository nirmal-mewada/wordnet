package test;

import java.io.IOException;

import me.common.FileIOHandler;
import me.common.MappedFile;

public class Snippet {
	public static void main(String[] args) {
		try {
			String s = "D:\\WorkSpace_\\nirmal_workspace\\Wordnet\\basedir";
			FileIOHandler fileIOHandler = new FileIOHandler(s, "input","outs");
			for (MappedFile f : fileIOHandler.listFiles()) {
				for (String line : f.readLines()) {
					f.write(line);
				}
				f.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

