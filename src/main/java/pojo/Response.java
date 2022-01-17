package pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	@JsonProperty("clients")
	private List<ClientsItem> clients;

	public List<ClientsItem> getClients(){
		return clients;
	}
}