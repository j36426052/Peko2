import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tester {
	
	public static void print(String word) {
		System.out.println(word);
	}
	
	
	public static void main(String args[]) throws IOException {
		
		ArrayList<Game> gameList = new ArrayList<Game>();
		String keyword;
		Scanner scanner = new Scanner(System.in);
		keyword = scanner.nextLine();
		
		GoogleQuery g = new GoogleQuery(keyword);
		
		//把輸入的用進URL
		
		//String[] kewordListG = g.query();
		String[] keywordList = keyword.split(" ");
		//String URL1 = "https://www.metacritic.com/search/game/";
		String URL1 = "https://store.steampowered.com/search/?term=";
		String URL2 = keywordList[0];
		String URLG = "https://www.google.com/search?q=" + keywordList[0];
		
		for (int i = 1; i < keywordList.length; i++) {
			URL2 += "%20" + keywordList[i];
			URLG += "+" + keywordList[i];
		}
		
		URLG += "+PC+game";
		String URLG1 = URLG + "&start=10";
		String URLG2 = URLG + "&start=20";
		String URLG3 = URLG + "&start=30";
		String URLG4 = URLG + "&start=40";
		//String URL3 = "/results?plats[3]=1&search_type=advanced";
		
		//String URL = URL1 + URL2 + URL3;
		String URL = URL1 + URL2;
		
		//連進 steam網站，抓取目標遊戲名稱還有URL
		Connection con1 = Jsoup.connect(URL);
		System.out.println("Now connecting to " + URL);

		//取得 html
		Document html1 = con1.get();
		//取的每個遊戲的名字 和 url
		Elements urls = html1.select("div#search_resultsRows a");
		Elements names = html1.select("span.title");
		Elements prices = html1.select("div.col.search_price.responsive_secondrow");
		
		int ac=0,bc=0,cc=0;
		
		for (Element e : names) {
			ac++;
			String name = e.text();
			gameList.add(new Game(name));
		}
		
		for (Element e : urls) {
			bc++;
			String url = e.attr("href");
			gameList.get(bc-1).setURL(url);
		}
		
		for (Element e : prices) {
			cc++;
			String price = e.text();
			print(price);
			gameList.get(cc-1).setPrices(price);
		}
		print(ac+"---"+bc+"---"+cc);
		print("============");
		
		//把url 變成 game
		
		//連進google
		System.out.println("Connection 1 completed\nNow connecting to " + URLG);
		Connection con2 = Jsoup.connect(URLG);

		Document html2 = con2.get();
		
		//每個子網頁的名稱
		String dataG = "";
		
		//抓google搜尋結果的網頁
		Elements childURL = html2.select("div.yuRUbf>a[href]");
		int a = 1;
		for (Element e : childURL) {
			Connection child = Jsoup.connect(e.attr("abs:href"));
			System.out.println(e.attr("abs:href"));
			Document cd = child.get();
			dataG += cd.text();
			a++;
		}
		
		System.out.println("Connection 2 completed\nNow connecting to " + URLG1);
		Connection con3 = Jsoup.connect(URLG1);
		Document html3 = con3.get();
		System.out.println("Connection 3 completed\nNow connecting to " + URLG2);
		Connection con4 = Jsoup.connect(URLG2);
		Document html4 = con4.get();
		System.out.println("Connection 4 completed\nNow connecting to " + URLG3);
		Connection con5 = Jsoup.connect(URLG3);
		Document html5 = con5.get();
		System.out.println("Connection 5 completed\nNow connecting to " + URLG4);
		Connection con6 = Jsoup.connect(URLG4);
		Document html6 = con6.get();
		
		
		dataG = html2.text() + html3.text() + html4.text() + html5.text() + html6.text();
		
		
		for (Game game : gameList) {
			Keyword k = game.getKey();
			KeywordCounter keywordCounter = new KeywordCounter(dataG, k.getName());
			k.setCount(keywordCounter.getCount());
		}
		
		Sort sort = new Sort(gameList,666);
		sort.sort();
		sort.output();
	}

}