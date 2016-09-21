package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import model.Mention;
import model.News;
import model.Sentence;
import nlp.StanfordNLPHandler;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import edu.stanford.nlp.util.CoreMap;

public class JSONOperator {

	final static Logger LOG = Logger.getLogger(JSONOperator.class);

	public void readAllJsonFiles(ArrayList<News> readFileList) {
		for (News news : readFileList) {
			File jsonOutputFile = new File(new Utils().FOLDER_JSON
					+ news.getId() + ".json");
			new JSONOperator().readFromJson(jsonOutputFile);
		}
	}

	public ArrayList<News> readFromJsonFolder(String folderPath) {
		ArrayList<News> temp = new ArrayList<News>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().endsWith(".json"))
				System.out.println(file.getName() + " File is reading...");
			temp.add(readFromJson(file));
		}
		return temp;
	}

	public News readFromJson(File jsonFile) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonReader reader = null;
		News newsFromJson = new News();
		try {
			reader = new JsonReader(new FileReader(jsonFile));
			newsFromJson = gson.fromJson(reader, News.class);
			System.err.println("readFromJson--> this json is OK ...."
					+ jsonFile.getName());
			// newsFromJson.getSentences().get(0).getEntities().get(0).getPos());
			ArrayList<Sentence> sentenceList = newsFromJson.getSentences();
			for (Sentence sentence : sentenceList) {
				LOG.info("Sentence:" + sentence.getSentence());// method
				ArrayList<Mention> mentions = sentence.getMentions();
				for (Mention mention : mentions) {
					LOG.info(mention.getName() + "--" + mention.getPos());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsFromJson;
	}

	public void writeFileListToJson(ArrayList<News> readFileList) {
		int size = readFileList.size();
		for (News news : readFileList) {
			new JSONOperator().writeNLPContentToJson(news,
					"resources/json/" + news.getId() + ".json");
			LOG.info("Remaining:" + size--);
		}
	}

	public void writeNLPContentToJson(News news, String JsonOutputFile) {
		try {
			// creates a StanfordCoreNLP object, with POS tagging,lemmatization,
			// NER, parsing, and coreference resolution
			File jsonOutputFile = new File(JsonOutputFile);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Writer jsonFileWriter = new FileWriter(jsonOutputFile);
			StanfordNLPHandler sNlp = new StanfordNLPHandler();
			List<CoreMap> sentenceCoreList = sNlp.splitSentences(news);
			ArrayList<Sentence> sentenceList = new ArrayList<Sentence>();
			for (CoreMap sentCore : sentenceCoreList) {
				Sentence emrSent = new Sentence(sentCore.toString());
				emrSent.setMentions(sNlp.obtainMentions(sentCore));
				//?????????????????????????????????????????????????????????????
				System.out.println("..... Try to write exracted mentions:" + emrSent.getMentions());
				sentenceList.add(emrSent);
			}
			news.setSentences(sentenceList);
			gson.toJson(news, jsonFileWriter);
			LOG.info("JSON is written to the file : " + JsonOutputFile);
			jsonFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
