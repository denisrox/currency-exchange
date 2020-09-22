package com.example.demo.component;

import com.example.demo.entities.Currency;
import com.example.demo.entities.Dates;
import com.example.demo.services.CurrencyService;
import com.example.demo.services.DatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
@Component
public class CourseCbr {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private DatesService datesService;

    public String getXMLString() {
        String xml2String = null;
        try{
            URL rssURL = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            URLConnection yc = rssURL.openConnection();
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();

            while( line != null){
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            bufReader.close();

            xml2String = sb.toString();

        }catch(Exception ignored){

        }
        return xml2String;
    }
    public Map<String,String> getXML() {
        Map<String,String> valute = new TreeMap<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("http://www.cbr.ru/scripts/XML_daily.asp");

            NodeList nodeList = document.getChildNodes();
            for(int x=0,size= 34; x<size; x++) {
                valute.put(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(3).getTextContent(),nodeList.item(0).getChildNodes().item(x).getChildNodes().item(4).getTextContent());
            }
        } catch (Exception ignored) { }
        return valute;
    }
    public void getCurrencys(Date date, Dates dates){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("http://www.cbr.ru/scripts/XML_daily.asp");

            NodeList nodeList = document.getChildNodes();
            for(int x=0,size= 34; x<size; x++) {
                //сначала нужно определить, мб уже есть созданный currency
                //Currency currency = currencyService
                //int numCode=Integer.parseInt(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(1).getTextContent());
                int numCode=Integer.parseInt(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(0).getTextContent());
                String title = (nodeList.item(0).getChildNodes().item(x).getChildNodes().item(3).getTextContent());

                Currency currency = currencyService.getOneByNumcode(numCode);
                if(currency==null)
                {
                    currency = currencyService.createNewCurrency(numCode,title);
                }

                List<Currency> listCurrency=dates.getCurrencyList();
                if(listCurrency==null)
                    listCurrency=new ArrayList<>();
                listCurrency.add(currency);
                dates.setCurrencyList(listCurrency);
                //Currency currency = new Currency();
                //currency.setNumcode(Integer.parseInt(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(1).getTextContent()));
                //currency.setTitle((nodeList.item(0).getChildNodes().item(x).getChildNodes().item(1).getTextContent()));

                //valute.put(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(3).getTextContent(),nodeList.item(0).getChildNodes().item(x).getChildNodes().item(4).getTextContent());
            }
            datesService.save(dates);
            //System.out.println(dates.getCurrencyList().size());
        } catch (Exception ex) {
            System.out.println("Ошибка: "+ ex.getMessage());
        }
    }
}
