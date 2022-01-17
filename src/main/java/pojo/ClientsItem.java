package pojo;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import serializer.ZonedDateTimeDeserializer;
import serializer.ZonedDateTimeSerializer;


@JsonPropertyOrder(value = {
		"address",
		"birthDay_new",
		"birthDay",
		"name",
		"age",
		"isActive",
		"id"})

public class ClientsItem{

	@JsonIgnore
	@JsonProperty("address")
	private String address;

	@JsonSetter("clientName")
	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("isActive")
	private boolean isActive;

	@JsonProperty("age")
	private int age;

	@JsonDeserialize(using = ZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@JsonAlias({"birthDay", "birthDay_new"})
	private String birthDay;

	public String getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public int getAge(){
		return age;
	}

	public String getBirthDay(){
		return birthDay;
	}
}