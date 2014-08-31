package controller;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.RSS;

public class RSSReader {

	private static RSSReader instance = null;

	private RSSReader() {
		// Singleton
	}

	public static RSSReader getInstance() {
		if (instance == null) {
			instance = new RSSReader();
		}
		return instance;
	}

	public RSS getRSS() throws JAXBException {

		JAXBContext ctx = JAXBContext.newInstance(RSS.class);
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		
		ClassLoader loader = this.getClass().getClassLoader();
		URL url = loader.getResource("res/rss.xml");
		Object obj = unmarshaller.unmarshal( url );
		RSS rss = (RSS) obj;
		return rss;

	}

}
