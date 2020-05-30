package top.tyzhang.Entity;

public class Ima {
	private String url;
	private String name;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Ima(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}
	public Ima() {
		super();
		// TODO Auto-generated constructor stub
	}
}
