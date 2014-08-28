package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement  
public class RSS {
	
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
