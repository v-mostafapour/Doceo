package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import model.News;

import org.apache.log4j.Logger;

public class FileOperator {

	final static Logger LOG = Logger.getLogger(FileOperator.class);

	// reads the entire content of the given file
	public String readEntireFile(String filePath) {
		String content = "";
		FileInputStream fis;
		File file = new File(filePath);
		byte[] data = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(data);
			fis.close();
			content = new String(data, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.trim();
	}

	// reads file names from the evaluation dataset folder
	public ArrayList<News> readFromFolder(String folderPath) {
		ArrayList<News> temp = new ArrayList<News>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().endsWith(".txt"))
				temp.add(readNewsFile(folderPath + file.getName()));
		}
//		System.out.println("yor news list is: ");
//		for (News anews:temp){
//			System.out.print(",  "+anews.getId());
//		}
		return temp;
	}
	
	

	public News readNewsFile(String filePath) {
		News news = null;
		BufferedReader br = null;
		try {
			String sCurrentLine = new Utils().EMPTY_STR;
			FileInputStream fr = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fr,
					Charset.forName("UTF-8"));
			br = new BufferedReader(isr);
			news = convertContentToNews(filePath, br, sCurrentLine);
			LOG.info("Parsed News: " + news.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return news;
	}

	public News convertContentToNews(String filePath, BufferedReader br,
			String sCurrentLine) {
		News news = null;
		try {

			String[] splitFilePath = filePath.split("/");
			String str = splitFilePath[splitFilePath.length - 1];
			int indexOfDot = str.indexOf(".");
			String id = str.substring(0, indexOfDot);

			String[] t = br.readLine().split(",");

			String date = t[t.length - 2] + "," + t[t.length - 1];
			String url = br.readLine();
			String title = "";
			for (int i = 0; i < t.length - 3; i++)
				title += t[i];
			String src = t[1].replace("\"", "");
			String content = "";
			while ((sCurrentLine = br.readLine()) != null) {
				content += sCurrentLine;
			}
			news = new News(id, title.replaceAll("\"", ""), src.trim(), date
					.trim().replace(".", ""), content, url);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return news;
	}

}
