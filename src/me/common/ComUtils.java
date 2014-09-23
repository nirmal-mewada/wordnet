package me.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;

public class ComUtils {

	/**
	 * Ensure clean dir.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void ensureCleanDir(String file) throws IOException {
		ensureCleanDir(new File(file));
	}

	/**
	 * Ensure clean dir.
	 *
	 * @param output the output
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void ensureCleanDir(File output) throws IOException {
		if(output.exists())
			FileUtils.cleanDirectory(output);
		else
			output.mkdirs();
	}




	/**
	 * Find max intersection words.
	 *
	 * @param sentence the sentence
	 * @param topicWordsMap the topic words map
	 * @return the string
	 */
	public static String findMaxIntersectionWords(String sentence,	Map<String, Set<String>> topicWordsMap) {
		String res = null;
		List<String> lstSentenceWords = new ArrayList<String>(Lists.newArrayList(sentence.toLowerCase().split(" ")));
		int maxSize = 0;
		for (String topic : topicWordsMap.keySet()) {
			int matchSize = ListUtils.intersection(lstSentenceWords, new ArrayList<String>(topicWordsMap.get(topic))).size();
			if(matchSize>maxSize){
				res = topic;
				maxSize=matchSize;
			}
		}
		return res;
	}

	public static Map<String, Set<String>> mergeMap(Map<String, Set<String>> topicWordsMapTest,Map<String, Set<String>> topicWordsMapTraining) {
		Map<String, Set<String>> res = new HashMap<String, Set<String>>();
		res.putAll(topicWordsMapTraining);

		for (String testKey : topicWordsMapTest.keySet()) {
			if(!res.containsKey(testKey)){
				res.put(testKey, topicWordsMapTest.get(testKey));
			}else{
				res.get(testKey).addAll(topicWordsMapTest.get(testKey));
			}
		}
		return res;
	}

	/**
	 * Gets the avarage.
	 *
	 * @param collection the collection
	 * @return the avarage
	 */
	public static Double  getAvarage(Collection<Double> collection) {
		//		System.out.print("Trim Invalid Rating: "+collection.size());
		collection.remove(-1.0);
		collection.remove(Double.NaN);
		//		System.out.println("->"+collection.size());

		Double sum = 0.0;
		for (Double val : collection) {
			sum = sum+val;
		}
		return sum/Double.valueOf(collection.size());
	}
	public static String  getFormatedAvarage(Collection<Double> collection) {
		Double val = getAvarage(collection);
		DecimalFormat format = new DecimalFormat("#.##");
		return format.format(val);
	}

	public static void closeAll(Collection<OutputStream> values) {
		for (OutputStream os : values) {
			IOUtils.closeQuietly(os);
		}

	}
}
