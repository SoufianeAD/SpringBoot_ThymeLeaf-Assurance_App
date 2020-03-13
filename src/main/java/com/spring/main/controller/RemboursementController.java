package com.spring.main.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.main.entities.Accident;
import com.spring.main.entities.Remboursement;
import com.spring.main.repositories.AccidentRepository;
import com.spring.main.repositories.RemboursementRepository;

@Controller
@RequestMapping("/remboursement")
public class RemboursementController {

	@Autowired
	private RemboursementRepository remboursementRepository;
	
	@Autowired
	private AccidentRepository accidentRepository;
	
	@GetMapping("/newRemboursementForm/{id}")
	public String newRemboursementForm(@PathVariable("id") int idAccident, Model model, HttpServletRequest request) {
		request.getSession().setAttribute("idActiveAccident", idAccident);
		model.addAttribute("remboursement", new Remboursement());
		return "NewRemboursement";
	}
	
	@GetMapping("/chart")
	public String chart(Model model) {
		String []months = new String [] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
		int data[] = new int[12];
		for(int i=1; i <= 9; i++) {
			data[i - 1] = remboursementRepository.countSumRemboursement("0"+i);
		}
		for(int i=10; i <= 12; i++) {
			data[i - 1] = remboursementRepository.countSumRemboursement(i+"");
		}
		for (int i : data) {
			System.err.println(i);
		}
		model.addAttribute("label", months);
		model.addAttribute("data", data);
		return "RemboursementChart";
	}
	
	@PostMapping("/newRemboursement")
	public String newRemboursement(@RequestParam("remboursementDate") String date, @ModelAttribute("remboursement") Remboursement remboursement, HttpServletRequest request) {
		Accident accident = accidentRepository.findById((int)request.getSession().getAttribute("idActiveAccident"));
		accident.setRemboursement(remboursement);
		System.out.println(date);
		String []t = date.split("-");
		System.err.println(t[0] + "-" + t[1] + "-" + t[2]);
		remboursement.setDate(LocalDate.of(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2])));
		remboursementRepository.save(remboursement);
		return "redirect:/accident/vehiculeAccidents/" + (int)request.getSession().getAttribute("idActiveVehicule");
	}
	
}
