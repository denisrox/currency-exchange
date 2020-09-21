package com.example.demo.contollers;

import com.example.demo.component.CourseCbr;
import com.example.demo.entities.Currency;
import com.example.demo.entities.Dates;
import com.example.demo.services.CurrencyService;
import com.example.demo.services.DatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Controller
public class ConverterController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private DatesService datesService;

    @Autowired
    private CourseCbr xml;


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
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World")String name, Model model) throws IOException, SAXException, ParserConfigurationException {
        System.out.println(xml.getXML());
        model.addAttribute("exchangeRates",xml.getXML());
        //Dates date=datesService.getOne(1);
        //System.out.println(date.toString());
        return "greeting";
    }



    //@GetMapping(value = "/1")

}