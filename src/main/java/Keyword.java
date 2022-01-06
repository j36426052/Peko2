public class Keyword {
	private String name;
	private int count;

	public Keyword(String name) {
		this.name = name;
		count = 0;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public void output() {
		System.out.println("[" + name + "," + count + "]");
	}
}
