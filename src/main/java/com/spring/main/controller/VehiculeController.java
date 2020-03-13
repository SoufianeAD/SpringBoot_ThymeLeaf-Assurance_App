package com.spring.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.main.entities.Client;
import com.spring.main.entities.Vehicule;
import com.spring.main.repositories.ClientRepository;
import com.spring.main.repositories.VehiculeRepository;

@Controller
@RequestMapping("/vehicule")
public class VehiculeController {

	@Autowired
	private VehiculeRepository vehiculeRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/vehicules")
	public String vehiculesForm(Model model) {
		model.addAttribute("vehicules", vehiculeRepository.findAll());
		return "GeneralVehicules";
	}
	
	@GetMapping("/clientVehicules/{id}")
	public String clientVehicules(@PathVariable("id") int id ,Model model, HttpServletRequest request) {
		model.addAttribute("vehicules", vehiculeRepository.findByClientId(id));
		model.addAttribute("client", clientRepository.findById(id));
		request.getSession().setAttribute("idActiveClient", id);
		return "Vehicules";
	}
	
	@GetMapping("/newVehiculeForm")
	public String newVehiculeForm(Model model,  HttpServletRequest request) {
		model.addAttribute("vehicule", new Vehicule());
		model.addAttribute("client", clientRepository.findById((int)request.getSession().getAttribute("idActiveClient")));
		return "NewVehicule";
	}
	
	@PostMapping("/newVehicule")
	public String newVehicule(@ModelAttribute("vehicule") Vehicule vehicule, HttpServletRequest request) {
		Client client = (Client)clientRepository.findById((int)request.getSession().getAttribute("idActiveClient"));
		vehicule.setClient(client);
		vehiculeRepository.save(vehicule);
		return "redirect:/vehicule/clientVehicules/" + (int)request.getSession().getAttribute("idActiveClient");
	}
	
	@GetMapping("/deleteVehicule/{id}")
	public String deleteVehicule(@PathVariable("id") int id, HttpServletRequest request) {
		vehiculeRepository.deleteById(id);
		return "redirect:/vehicule/clientVehicules/" + (int)request.getSession().getAttribute("idActiveClient");
	}
	
	@GetMapping("/editVehiculeForm/{id}")
	public String editVehiculeForm(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		model.addAttribute("vehicule", vehiculeRepository.findById(id));
		model.addAttribute("client", clientRepository.findById((int)request.getSession().getAttribute("idActiveClient")));
		return "EditVehicule";
	}
}
