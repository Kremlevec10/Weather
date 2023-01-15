package ru.home.Weather.frontend.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.home.Weather.backend.russianCitys.CitysRussian;
import ru.home.Weather.backend.russianRegions.RussianRegion;

public class HeadController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField cityField;

    @FXML
    private Label dateLabel;

    @FXML
    private Label filingTempiraturaLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label tempiraturaLabel;

    @FXML
    private Button weatherButton;

    @FXML
    private Label windLabel;
    
    private Map<String, StringBuilder> citysMap;
    private Document pageCity;

    @FXML
    void initialize() {
    	pressWeatherButton();
    }

    private void pressWeatherButton() {
		weatherButton.setOnAction(event -> {
			updateTextLabel();
			citysMap = new CitysRussian().getCitysMap();
			try {
				conectCity();
			} catch (IOException e) {
				e.printStackTrace();
			}
			dateLabel.setText(dateLabel.getText().concat(giveDate()));
			tempiraturaLabel.setText(tempiraturaLabel.getText().concat(giveTempiratura()));
			filingTempiraturaLabel.setText(filingTempiraturaLabel.getText().concat(giveFilingTempiratura()));
			windLabel.setText(windLabel.getText().concat(giveWind()));
			pressureLabel.setText(pressureLabel.getText().concat(givePressure()));
			System.out.println("Susseful");
		});
	}
    
    private void conectCity() throws IOException {
    	String urlCity = citysMap.get(cityField.getText()).toString();
    	pageCity = Jsoup.connect(urlCity).get();
	}
    
    private String giveDate() {
		Element dateElement = pageCity.selectFirst("div[class=w4bT]");
		return dateElement.text();
	}
    
    private String giveTempiratura() {
		Element tempiraturaElement = pageCity.selectFirst("div[class=HhSR MBvM]");
		return tempiraturaElement.text();
	}
    
    private String giveFilingTempiratura() {
		Element filingTempiraturaElement = pageCity.selectFirst("span[class=iO0y]").child(0);
		return filingTempiraturaElement.text();
	}
    
    private String giveWind() {
		Element windElement = pageCity.selectFirst("span[class=VaOz PB4k]");
		return windElement.text();
	}
    
    private String givePressure() {
		Element pressurElement = pageCity.selectFirst("div[class=hjtR pressure hC8A iBF1]").selectFirst("span[class=VaOz PB4k]");
		return pressurElement.text();
	}
    
    private void updateTextLabel() {
		dateLabel.setText("Дата: ");
		tempiraturaLabel.setText("Темпиратура: ");
		filingTempiraturaLabel.setText("Темпиратура по ощущению: ");
		windLabel.setText("Ветер: ");
		pressureLabel.setText("Давление: ");
	}
}

