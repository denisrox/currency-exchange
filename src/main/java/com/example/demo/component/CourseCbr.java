package com.example.demo.component;

import com.example.demo.entities.Currency;
import com.example.demo.entities.Dates;
import com.example.demo.entities.Orders;
import com.example.demo.services.CurrencyService;
import com.example.demo.services.DatesService;
import com.example.demo.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class CourseCbr {
    @Autowired
    private CurrencyService currencyService;
    public void getCurrencys(Dates dates){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(getUrlByDate(dates));
            NodeList nodeList = document.getChildNodes();
            int countElements = nodeList.item(0).getChildNodes().getLength();

            for(int x=0; x<countElements; x++) {
                int numCode=Integer.parseInt(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(0).getTextContent());
                float value=Float.parseFloat((nodeList.item(0).getChildNodes().item(x).getChildNodes().item(4).getTextContent()).replace(',','.'));

                Currency currency = currencyService.getOneByNumcode(numCode);

                if(currency==null)
                {
                    String title = (nodeList.item(0).getChildNodes().item(x).getChildNodes().item(3).getTextContent());
                    currency = currencyService.createNewCurrency(numCode,title);
                }
                List<Orders> listOrders=dates.getListOrders();
                if(listOrders==null)
                    listOrders=new ArrayList<>();
                Orders order = new Orders(dates,currency,value);
                listOrders.add(order);
                dates.setListOrders(listOrders);



            }

        } catch (Exception ex) {
            System.out.println("Ошибка: "+ ex.getMessage());
        }
    }

    private String getUrlByDate(Dates dates){
        Date date = dates.getDaterequest();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String month = String.format("%02d",calendar.get(Calendar.MONTH));
        String day = String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH));
        int year = calendar.get(Calendar.YEAR);

        return "http://cbr.ru/scripts/XML_daily.asp?date_req="+day+"/"+month+"/"+year;
    }
}
