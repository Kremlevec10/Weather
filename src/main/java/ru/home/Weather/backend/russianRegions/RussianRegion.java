package ru.home.Weather.backend.russianRegions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RussianRegion {

	private Document page;
	private List<StringBuilder> links;
	List<Element> lineLinks;
	public static String LINK_REGION = "https://weather.rambler.ru";

	public RussianRegion() {
		givePageRegion();
		giveLineLinksRegion();
	}

	private void givePageRegion() {
		try {
			page = Jsoup.connect(LINK_REGION + "/world/rossiya/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void giveLineLinksRegion() {
		lineLinks = page.getElementsByAttributeValue("class", "kgSF");
	}

	public List<StringBuilder> giveLinksRegion() {
		links = new ArrayList<>();
		for (Element element : lineLinks) {
			StringBuilder link = new StringBuilder(LINK_REGION);
			link.append(element.attr("href"));
			links.add(link);
		}
		return links;
	}
}
