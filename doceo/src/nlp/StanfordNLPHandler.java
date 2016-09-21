package nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Mention;
import model.News;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class StanfordNLPHandler implements I_NLPHandler {

	private StanfordCoreNLP pipeline;

	public StanfordNLPHandler() {
		Properties props = new Properties();
		props.put("annotators",
				"tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		pipeline = new StanfordCoreNLP(props);
	}

	public List<CoreMap> splitSentences(News news) {
		Annotation document = new Annotation(news.getContent());
		pipeline.annotate(document);
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);

		return sentences;
	}

	public ArrayList<Mention> obtainMentionList(CoreMap sentenceCore) {
		ArrayList<Mention> mentionList = new ArrayList<Mention>();
		for (CoreLabel token : sentenceCore.get(TokensAnnotation.class)) {
			String word = token.get(TextAnnotation.class);
			String pos = token.get(PartOfSpeechAnnotation.class);
			String ne = token.get(NamedEntityTagAnnotation.class);
			mentionList.add(new Mention(word, pos, ne));
		}
		return mentionList;
	}

	@Override
	public ArrayList<Mention> obtainMentions(CoreMap sentenceCore) {
		// TODO Auto-generated method stub
		return null;
	}
}
