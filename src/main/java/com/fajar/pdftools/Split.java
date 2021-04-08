package com.fajar.pdftools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class Split {

	static final String DIRECTORIES = "E:\\Activity\\PDPU\\Surat\\surat izin fix\\";
	static List<String> fileNames = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		setFileNames();
		split();
	}

	private static void split() throws IOException {
		for (String string : fileNames) {
			splitFile(new File(string));
		}
	}

	private static void splitFile(File file) throws IOException {
		// TODO Auto-generated method stub
		PDDocument doc = PDDocument.load(file);

		// Instantiating Splitter class
		Splitter splitter = new Splitter();

		// splitting the pages of a PDF document
		List<PDDocument> Pages = splitter.split(doc);

		// Creating an iterator
		Iterator<PDDocument> iterator = Pages.listIterator();

		// Saving each page as an individual document
		int i = 1;

		while (iterator.hasNext()) {
			PDDocument pd = iterator.next();
			String path = DIRECTORIES +"SPLIT "+file.getName()+" - "+ i++ + ".pdf";
			pd.save(path);
			System.out.println("Saved: "+path);
		}
	}

	private static void setFileNames() throws IOException {
		fileNames.clear();
		File diratory = new File(DIRECTORIES);
		File[] files = diratory.listFiles();
		for (File file : files) {
			if (file.getName().endsWith(".pdf") == false) {
				continue;
			}
			fileNames.add(file.getCanonicalPath());
			System.out.println(file.getCanonicalPath());
		}
	}
}
