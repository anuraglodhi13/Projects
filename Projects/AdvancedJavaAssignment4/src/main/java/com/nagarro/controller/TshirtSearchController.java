package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.constants.Constants;
import com.nagarro.dao.TshirtDao;
import com.nagarro.model.TshirtData;
import com.nagarro.service.SortingService;

import java.util.Collections;
import java.util.List;

@Controller
public class TshirtSearchController {
	@RequestMapping("/tshirt")
	private ModelAndView TshirtSearch(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(Constants.COLOUR) String colour, 
			@RequestParam(Constants.SIZE) String size,
			@RequestParam(Constants.GENDER_RECOMMENDATION) String gender, 
			@RequestParam(Constants.OUTPUT_PREFERNCE) String outputPreference)
			throws IOException, ServletException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
		        Constants.TSHIRT_XML_FILE);
		((AbstractApplicationContext) factory).refresh();
		TshirtDao tshirtDao = factory.getBean(TshirtDao.class);
		List<TshirtData> searchedTshirtList = tshirtDao.getTshirts(colour, gender, size);
		
		 if (outputPreference.equals(Constants.PRICE_SORTED_VALUE)) {
             Collections.sort(searchedTshirtList, SortingService.priceSorter);
         } else if (outputPreference.equals(Constants.RATING_SORTED_VALUE)) {
             Collections.sort(searchedTshirtList, SortingService.ratingSorter);
         } else if (outputPreference.equals(Constants.PRICE_AND_RATING_SORTED_VALUE)) {
             Collections.sort(searchedTshirtList, SortingService.priceAndRatingSorter);
         }
		 Integer listSize = searchedTshirtList.size();
		 ModelAndView mv = new ModelAndView("tshirt.jsp");
		 mv.addObject("searched-tshirt",searchedTshirtList);
		 if(colour != null && listSize == 0) {
			 mv.addObject("show-not-found", "true");
		 }
		return mv;
	}
}
