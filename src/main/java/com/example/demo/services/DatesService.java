package com.example.demo.services;

import com.example.demo.component.CourseCbr;
import com.example.demo.entities.Currency;
import com.example.demo.entities.Dates;
import com.example.demo.repositories.ICurrencyRepository;
import com.example.demo.repositories.IDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class DatesService {
    @Autowired
    private IDatesRepository iDatesRepository;

    @Autowired
    private CourseCbr curseCbr;
    //System.out.println(xml.getXML());
    //model.addAttribute("exchangeRates",xml.getXML());

    public Dates getOneById(Integer id){

        return iDatesRepository.getOne(id);
    }

    public Dates getOneByDaterequest(Date date){
        Dates dates = iDatesRepository.findByDaterequest(date);
        if(dates == null){
            System.out.println("создаем новую дату");
            CreateNewDates(date, dates);
        }
        return dates;
    }

    public void save(Dates dates){
        System.out.println(dates.toString());
        //iDatesRepository.save(dates);
    }

    private void CreateNewDates(Date date, Dates dates){
        dates = new Dates();
        dates.setDaterequest(date);
        iDatesRepository.save(dates);
        curseCbr.getCurrencys(date, dates);
        System.out.println("==="+dates+"====");
        iDatesRepository.save(dates);
        //iDatesRepository.save(dates);
        //System.out.println(curseCbr.getXML());
    }


}
