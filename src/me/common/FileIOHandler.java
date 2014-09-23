package me.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.common.collect.Lists;

public class FileIOHandler {
	String inputFolder;
	String outFolder;
	List<String> ext = Lists.newArrayList("txt");
	boolean recursive = false;

	String appendName = null;

	List<MappedFile> lstMappedFiles;
	public FileIOHandler(String baseDir,String inputFolder, String outFolder) throws IOException {
		this(baseDir+File.separator+inputFolder, baseDir+File.separator+outFolder);
	}
	public FileIOHandler(String inputFolder, String outFolder) throws IOException {
		this.inputFolder = inputFolder;
		this.outFolder = outFolder;
		ComUtils.ensureCleanDir(outFolder);
	}

	public FileIOHandler addExtension(String...exts) {
		if(ext==null)
			ext = new ArrayList<String>();
		for (String e : exts) {
			ext.add(e);
		}
		return this;
	}
	public FileIOHandler removeExtension(String exts) {
		if(ext!=null){
			ext.remove(exts);
		}
		return this;
	}

	public List<MappedFile> listFiles() throws IOException {
		if(lstMappedFiles!=null)
			return lstMappedFiles;
		List<MappedFile> res = new ArrayList<MappedFile>();
		Collection<File> infiles = 	FileUtils.listFiles(new File(inputFolder), ext.toArray(new String[]{}) , recursive);
		for (File inFile : infiles) {
			String strOutFile = inFile.getName();
			if(appendName!=null){
				String base = FilenameUtils.getBaseName(strOutFile);
				String extention = FilenameUtils.getExtension(strOutFile);
				strOutFile = base+appendName+"."+extention;
			}
			File outFile = new File(outFolder+File.separator+strOutFile);
			MappedFile mappedFile = new MappedFile(inFile, outFile);
			res.add(mappedFile);
		}
		lstMappedFiles = res;
		return res;
	}

	public String getInputFolder() {
		return inputFolder;
	}

	public FileIOHandler setInputFolder(String inputFolder) {
		this.inputFolder = inputFolder;
		return this;
	}

	public String getOutFolder() {
		return outFolder;
	}

	public FileIOHandler setOutFolder(String outFolder) {
		this.outFolder = outFolder;
		return this;
	}


	public List<String> getExt() {
		return ext;
	}

	public void setExt(List<String> ext) {
		this.ext = ext;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public FileIOHandler setRecursive(boolean recursive) {
		this.recursive = recursive;
		return this;
	}
	public void closeAll() {
		for (MappedFile mf : lstMappedFiles) {
			mf.close();
		}
	}
	public void closeClean() {
		closeAll();
		lstMappedFiles = null;
	}

	public String getAppendName() {
		return appendName;
	}
	public FileIOHandler setAppendName(String appendName) {
		this.appendName = appendName;
		return this;
	}
	public static void main(String[] args) {

	}
}
