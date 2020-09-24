package com.example.demo.contollers;

import com.example.demo.component.CourseCbr;
import com.example.demo.entities.Currency;
import com.example.demo.entities.Dates;
import com.example.demo.entities.Orders;
import com.example.demo.services.CurrencyService;
import com.example.demo.services.DatesService;
import com.example.demo.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;

@Controller
public class ConverterController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private DatesService datesService;
    @Autowired
    private OrdersService ordersService;



    @GetMapping("/")
    public String greeting(@RequestParam(required=false) Integer fromNumCode,
                           @RequestParam(required=false) Float fromCountMoney,
                           @RequestParam(required=false) Integer toNumCode,
                           @RequestParam(required=false) String dateInput,
                           Model model) throws IOException, SAXException, ParserConfigurationException
    {

        Date date;
        try {
            date = Date.valueOf(dateInput);
        }catch (Exception e){
            date = new Date(Calendar.getInstance().getTime().getTime());
        }
        Dates dates = datesService.getOneByDaterequest(date);

        if(fromNumCode!=null&&toNumCode!=null&&fromCountMoney!=null){
            addedAttribute(model,fromNumCode,toNumCode,fromCountMoney,dates);
        }


        model.addAttribute("date",date.toString());
        model.addAttribute("currencyes",dates.getListOrders());

        return "converter";
    }
    void addedAttribute(Model model, Integer fromNumCode, Integer toNumCode,Float fromCountMoney, Dates dates){
        float costFromCurrency,costToCurrency;
        if(fromNumCode==0){
            costFromCurrency=1;
        }else{
            Currency currencyFrom = currencyService.getOneByNumcode(fromNumCode);
            costFromCurrency = ordersService.getValueById(dates.getId(),currencyFrom.getId());
        }

        if(toNumCode==0){
            costToCurrency=1;
        }else{
            Currency currencyTo = currencyService.getOneByNumcode(toNumCode);
            costToCurrency = ordersService.getValueById(dates.getId(),currencyTo.getId());
        }
        if(costFromCurrency == 0 || costToCurrency==0) //метод getValueById возвращает 0 тогда, когда не находит нужную валюту
            return;                                    //а это значит, что в данном году нет информации на сайте CBR про эту валюту

        DecimalFormat df = new DecimalFormat("#.##");
        String toCountMoney= df.format(fromCountMoney*costFromCurrency/costToCurrency);
        model.addAttribute("fromNumCode",fromNumCode);
        model.addAttribute("fromCountMoney",fromCountMoney);
        model.addAttribute("toNumCode",toNumCode);
        model.addAttribute("toCountMoney",toCountMoney);
    }
}
