package pipeline;

import java.util.ArrayList;

import model.News;
import model.Triple;

public class EntityLinker {

	public void obtainEntities(ArrayList<News> jsonNewsList,
			ArrayList<Triple> tripleList) {
		for (News news : jsonNewsList) {
			
			for (Triple triple : tripleList) {
				if(news.getContent().contains(triple.getSubject()))
					System.out.println("found.");
			}
			
		}
	}

}
