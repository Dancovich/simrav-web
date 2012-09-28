package br.gov.serpro.simrav.simravweb.domain.mensageria;


public class FilaDTO {
	
	private String name;
	private String messageCount;
	private String consumerCount;
	private String messagesAdded;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(String messageCount) {
		this.messageCount = messageCount;
	}
	public String getConsumerCount() {
		return consumerCount;
	}
	public void setConsumerCount(String consumerCount) {
		this.consumerCount = consumerCount;
	}
	public String getMessagesAdded() {
		return messagesAdded;
	}
	public void setMessagesAdded(String messagesAdded) {
		this.messagesAdded = messagesAdded;
	}
}
