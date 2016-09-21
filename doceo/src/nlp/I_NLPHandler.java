package nlp;

import java.util.ArrayList;
import java.util.List;

import model.Mention;
import model.News;
import edu.stanford.nlp.util.CoreMap;

public interface I_NLPHandler {
	
	public List<CoreMap> splitSentences(News news);
	public ArrayList<Mention> obtainMentions(CoreMap sentenceCore);

}
