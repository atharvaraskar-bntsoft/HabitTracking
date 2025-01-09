package com.bnt.HabitTracker.service;

import com.bnt.HabitTracker.model.Month;
import java.util.*;

public interface MonthService {

    public Month saveMonth(Month month);

     public Optional<Month> getMonthById(long id);

     public Month updateMonth(Month month);

     public List<Month>  getAllMonths();

     public void  deleteMonth(long id);

}
