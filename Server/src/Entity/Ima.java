package Entity;

public class Ima {
	private String url;//文件名
	private String name;//作者
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
