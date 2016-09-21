package main;

import io.FileOperator;
import io.JSONOperator;

import java.util.ArrayList;

import pipeline.EntityLinker;
import kb.KbHandler;
import model.News;
import model.Triple;

public class PipelineHandler {

	public static void main(String[] args) {
		// this is the comment just to check the github repository
		// 0. gets all the Triple list from graph
		ArrayList<Triple> TRIPLE_LIST = new KbHandler().getTriples();

		// 1. reads test news file
		ArrayList<News> readFileList = new FileOperator()
				.readFromFolder("resources/test_set/");
		for (News news : readFileList) {
			System.out.println("Content: " + news.getContent());
		}

		// 2. transforms news file content into JSON file
		new JSONOperator().writeFileListToJson(readFileList);

		// 3. reads JSON news file and sent to the Entity Linking task
		ArrayList<News> jsonNewsList = new JSONOperator()
				.readFromJsonFolder("resources/json/");

		// 4. operates Entity Linking task and add entities to JSON file and
		// sent them to the Relation Extraction task
		new EntityLinker().obtainEntities(jsonNewsList, TRIPLE_LIST);

		// 5. operates Relation Extraction task and offers new triples

		// 6. adds new triples to the current tiple list
	}

}
