package com.manoj.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manoj.spring.web.dao.Offer;
import com.manoj.spring.web.service.OffersService;

//controller annotation define controller on data
@Controller
public class OffersController {

	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	
	/*//handle data exception
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex){
		return "error";
	}*/
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		System.out.println("Id is: " + id);
		return "home";
	}

	// show offers
	@RequestMapping("/offers")
	public String showOffers(Model model) {
		
		//offersService.throwTestException();

		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);

		return "offers";
	}

	// create offers
	@RequestMapping("/createoffer")
	public String createOffer(Model model) {

		model.addAttribute("offer", new Offer());

		return "createoffer";
	}

	// offer creating using database
	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result) {

		if (result.hasErrors()) {

			return "createoffer";

		}
		
		offersService.create(offer);

		return "offercreated";
	}

}
