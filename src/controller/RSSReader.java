package controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.RSS;

public class RSSReader {

	public RSS getRSS() {

		try {
			JAXBContext ctx = JAXBContext.newInstance(RSS.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			File file = new File("/Users/mapfap/Desktop/rss.xml");
			Object obj = unmarshaller.unmarshal( file );
			RSS rss = (RSS) obj;
			return rss;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Test JAXB
	public static void main(String[] args) {
		RSS rss = (new RSSReader()).getRSS();
		System.out.println(rss.getChannel().getItems().get(0).getDescription());
	}


}
