package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * RSS data model.
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class RSS {
	
	@XmlElement
	private Channel channel;
	
	public RSS() {
		
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
