package io;

import static org.junit.Assert.assertEquals;
import model.News;

import org.junit.Test;

public class TestFileParser {

	@Test
	public void testFileParser() throws Exception {
		News news = new News(
				"1",
				"ISIS killed 20 Iraqi militants",
				"Reuters",
				"September 6, 2016",
				"ISIS killed 20 Iraqi militants. Syrian Army killed 25 ISIS militants.",
				"http://www.turkishpress.com/news/417149/");

		News readNews = new FileOperator().readNewsFile("test_set/99.txt");
		assertEquals(news.getTitle(), readNews.getTitle());
		assertEquals(news.getDate(), readNews.getDate());

	}
}
