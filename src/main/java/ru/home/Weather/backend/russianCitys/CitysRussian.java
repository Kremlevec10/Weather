package ru.home.Weather.backend.russianCitys;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ru.home.Weather.backend.russianRegions.RussianRegion;

public class CitysRussian {

	private List<StringBuilder> linksRegions;
	private Map<String, StringBuilder> citysMap;
	private Document pageCitys;

	public CitysRussian() {
		linksRegions = new RussianRegion().giveLinksRegion();
		addCityToCitysMap();
	}

	private void addCityToCitysMap() {
		citysMap = new HashMap<>();
		for (StringBuilder link : linksRegions) {

			conectCitysByRegion(link);

			Elements linksCitys = pageCitys.getElementsByAttributeValue("class", "MJZ5");
			
			for (Element element : linksCitys) {
				StringBuilder linkCity = new StringBuilder(RussianRegion.LINK_REGION);
				StringBuilder deletSymbol = new StringBuilder(element.attr("href"));
				linkCity.append(deletSymbol);
				String nameCity = element.text().split(" ")[0];
				
				citysMap.put(getNameCity(element.text()), linkCity);
			}
		}

	}
	
	private String getNameCity(String textElement) {
		String nameCity = textElement.split(" ")[0];
		return nameCity;
	}

	private void conectCitysByRegion(StringBuilder link) {
		try {
			pageCitys = Jsoup.connect(link.toString()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public Map<String, StringBuilder> getCitysMap() {
		return citysMap;
	}

}
