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

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

@Controller
public class ConverterController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private DatesService datesService;
    @Autowired
    private OrdersService ordersService;



    @GetMapping(value = "/")
    public String index (Model model){
        List<Currency> currencyList=currencyService.findAll();

        model.addAttribute("currency", currencyService.findAll());
        return "index";
    }

    @PostMapping(value = "/")
    public void index (int i){
        System.out.println(i);
    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(required=false) Integer fromSelect,
                           @RequestParam(required=false) Float fromInput,
                           @RequestParam(required=false) Integer toSelect,
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

        if(fromSelect!=null&&fromInput!=null&&toSelect!=null){
            addedAttribute(model,fromSelect,toSelect,fromInput,dates);
        }


        model.addAttribute("date",date.toString());
        model.addAttribute("currencyes",dates.getListOrders());

        return "greeting";
    }
    void addedAttribute(Model model, Integer fromSelect, Integer toSelect,Float fromInput, Dates dates){
        //Currency currencyFrom;
        //fromSelect!=0?currencyFrom = currencyService.getOneByNumcode(fromSelect):currencyFrom=new Currency();
        //Currency currencyFrom = currencyService.getOneByNumcode(fromSelect);
        //Currency currencyTo = currencyService.getOneByNumcode(toSelect);
        float x,y;
        if(fromSelect==0){
            x=1;
        }else{
            Currency currencyFrom = currencyService.getOneByNumcode(fromSelect);
            x = ordersService.getValueById(dates.getId(),currencyFrom.getId());
        }

        if(toSelect==0){
            y=1;
        }else{
            Currency currencyTo = currencyService.getOneByNumcode(toSelect);
            y = ordersService.getValueById(dates.getId(),currencyTo.getId());
        }


        DecimalFormat df = new DecimalFormat("#.##");
        String value= df.format(x/y*fromInput);
        model.addAttribute("fromSelect",fromSelect);
        model.addAttribute("fromInput",fromInput);
        model.addAttribute("toSelect",toSelect);
        model.addAttribute("value",value);
    }



    //@GetMapping(value = "/1")

}
