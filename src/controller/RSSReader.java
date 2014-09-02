package controller;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.RSS;

/**
 * Read the rss from any source, unmarshal to convert xml to object using JAXB.
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
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

	/**
	 * Get RSS object from specified URL.
	 * 
	 * @param url as source.
	 * @return RSS object constructed from given source.
	 * @throws JAXBException if the is a fault in unmarshalling.
	 */
	public RSS getRSS(URL url) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(RSS.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object obj = unmarshaller.unmarshal(url);
		RSS rss = (RSS) obj;
		return rss;

	}

}
