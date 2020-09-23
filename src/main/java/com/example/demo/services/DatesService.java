package com.example.demo.services;

import com.example.demo.component.CourseCbr;
import com.example.demo.entities.Dates;
import com.example.demo.repositories.IDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class DatesService {
    @Autowired
    private IDatesRepository iDatesRepository;

    @Autowired
    private CourseCbr curseCbr;


    public Dates getOneById(Integer id){

        return iDatesRepository.getOne(id);
    }

    public Dates getOneByDaterequest(Date date){
        Dates dates = iDatesRepository.findByDaterequest(date);
        if(dates == null){
            CreateNewDates(date, dates);
        }
        return dates;
    }


    private void CreateNewDates(Date date, Dates dates){
        dates = new Dates();
        dates.setDaterequest(date);
        iDatesRepository.save(dates);
        curseCbr.getCurrencys(dates);
        iDatesRepository.save(dates);
    }


}
