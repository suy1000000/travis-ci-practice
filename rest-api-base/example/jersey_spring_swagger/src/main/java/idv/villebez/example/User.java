package idv.villebez.example;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import idv.villebez.json.XSSDeserializer;
import io.swagger.annotations.ApiModelProperty;

public class User {
	@ApiModelProperty(value = "使用者編號", example = "b23a9577-a7b7-4614-b574-10af26da90b8", readOnly = true)
	private String id;
	@ApiModelProperty(value = "使用者名稱", required = true, example = "陳仁")
	@JsonDeserialize(using = XSSDeserializer.class)
	private String name;
	@ApiModelProperty(value = "年齡", example = "18")
	private int age;

	public User() {
		super();
	}
	
	public User(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
