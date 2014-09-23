package me.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

public class MappedFile {
	File in;
	File out;
	OutputStream fos;

	public MappedFile(File in, File out) throws IOException {
		this.in = in;
		this.out = out;
	}
	public List<String> readLines() throws FileNotFoundException, IOException {
		return IOUtils.readLines(new BufferedInputStream(new FileInputStream(in)));
	}
	public LineIterator lineIterator() throws FileNotFoundException, IOException {
		return IOUtils.lineIterator(new FileReader(in));
	}

	public void write(String data) throws IOException {
		if(fos==null){
			if(!out.exists())
				out.createNewFile();
			fos = new BufferedOutputStream(new FileOutputStream(out));
		}
		IOUtils.write(data,	fos);
	}
	String getFileName(){
		return in.getName();
	}
	public void close() {
		IOUtils.closeQuietly(fos);
		fos = null;
	}
	public File getIn() {
		return in;
	}
	public void setIn(File in) {
		this.in = in;
	}
	public File getOut() {
		return out;
	}
	public void setOut(File out) {
		this.out = out;
	}
	public OutputStream getFos() {
		return fos;
	}
	public void setFos(OutputStream fos) {
		this.fos = fos;
	}


}
