package me.common.filter;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public abstract class AbstractListFilter implements Filter{
	Set<String> stopWords;

	public AbstractListFilter(File file) {
		addFile(file);
	}
	public AbstractListFilter(String...file) {
		addFile(file);
	}

	public AbstractListFilter() {
	}
	public void addWords(String...words){
		if(stopWords==null)
			stopWords = new HashSet<String>();
		stopWords.addAll(Arrays.asList(words));
	}
	public void addFile(File file) {
		try {
			List<String> lines = IOUtils.readLines(new FileReader(file));
			if(stopWords==null)
				stopWords = new HashSet<String>();
			for (String w : lines) {
				stopWords.add(w.toLowerCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addFile(String...files) {
		for (String file : files) {
			addFile(new File(file));
		}
	}
	public void addFile(File...files) {
		for (File file : files) {
			addFile(file);
		}
	}
}
