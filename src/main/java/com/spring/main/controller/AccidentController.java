package com.spring.main.controller;

import java.sql.Date;
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
import com.spring.main.entities.Client;
import com.spring.main.entities.Vehicule;
import com.spring.main.repositories.AccidentRepository;
import com.spring.main.repositories.ClientRepository;
import com.spring.main.repositories.VehiculeRepository;

@Controller
@RequestMapping("/accident")
public class AccidentController {

	@Autowired
	private AccidentRepository accidentRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private VehiculeRepository vehiculeRepository;
	
	@GetMapping("/vehiculeAccidents/{id}")
	public String vehiculeAccidents(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		model.addAttribute("accidents", accidentRepository.findByVehiculeId(id));
		model.addAttribute("vehicule", vehiculeRepository.findById(id));
		request.getSession().setAttribute("idActiveVehicule", id);
		model.addAttribute("client", clientRepository.findById((int)request.getSession().getAttribute("idActiveClient")));
		return "Accidents";
	}
	
	@GetMapping("/chart")
	public String chart(Model model) {
		String []months = new String [] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
		int data[] = new int[12];
		for(int i=1; i <= 9; i++) {
			data[i - 1] = accidentRepository.countAccident("0"+i);
		}
		for(int i=10; i <= 12; i++) {
			data[i - 1] = accidentRepository.countAccident(i+"");
		}
		model.addAttribute("label", months);
		model.addAttribute("data", data);
		return "AccidentsChart";
	}
	
	@GetMapping("/newAccidentForm")
	public String newAccidentForm(Model model, HttpServletRequest request) {
		Vehicule vehicule = (Vehicule)vehiculeRepository.findById((int)request.getSession().getAttribute("idActiveVehicule"));
		model.addAttribute("accident", new Accident());
		Client client = (Client)clientRepository.findById((int)request.getSession().getAttribute("idActiveClient"));
		model.addAttribute("client", client);
		model.addAttribute("vehicule", vehicule);
		return "NewAccident";
	}
	
	@PostMapping("/newAccident")
	public String newAccident(@ModelAttribute("accident") Accident accident, @RequestParam("accidentDate") String date, HttpServletRequest request) {
		System.out.println(date);
		String []t = date.split("-");
		System.err.println(t[0] + "-" + t[1] + "-" + t[2]);
		accident.setDate(LocalDate.of(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2])));
		System.out.println(accident.getDate());
		Vehicule vehicule = (Vehicule)vehiculeRepository.findById((int)request.getSession().getAttribute("idActiveVehicule"));
		accident.setVehicule(vehicule);
		accidentRepository.save(accident);
		return "redirect:/accident/vehiculeAccidents/" + (int)request.getSession().getAttribute("idActiveVehicule");
	}
	
	@GetMapping("/deleteAccident/{id}")
	public String deleteAccident(@PathVariable("id") int id, HttpServletRequest request) {
		accidentRepository.deleteById(id);
		return "redirect:/accident/vehiculeAccidents/" + (int)request.getSession().getAttribute("idActiveVehicule");
	}
	
	
}
