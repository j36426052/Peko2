import java.util.ArrayList;

public class Game {
	
	private String name;
	private String prices;
	private String url;
	private ArrayList<String> tags;
	private Keyword k;

	public Game(String name) {
		this.name = name;
		prices = "";
		url = "";
		tags = new ArrayList<String>();
		k = new Keyword(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public void setPrices(String prices) {
		this.prices = prices;
	}
	public void addTags(String tag) {
		tags.add(tag);
	}
	

	public String getName() {
		return name;
	}
	
	public Keyword getKey() {
		return k;
	}
	
	public String getPrices() {
		return prices;
	}
	
	public String geturl() {
		return url; 
	}
	
	public ArrayList<String> getTags() {
		return tags;
	}


	public void output() {
		System.out.println("[" + name + "," + prices + "]");
	}
}
