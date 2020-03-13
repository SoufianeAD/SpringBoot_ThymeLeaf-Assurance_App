package com.spring.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.main.entities.Client;
import com.spring.main.repositories.ClientRepository;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/clients")
	public String clients(Model model) {
		model.addAttribute("clients", clientRepository.findAll());
		return "Clients";
	}
	
	@GetMapping("/newClientForm")
	public String newClientForm(Model model) {
		model.addAttribute("client", new Client());
		return "NewClient";
	}
	
	@PostMapping("/newClient")
	public String newClient(@ModelAttribute("client") Client client) {
		clientRepository.save(client);
		return "redirect:/client/clients";
	}
	
	@GetMapping("/deleteClient/{id}")
	public String deleteClient(@PathVariable("id") int id) {
		clientRepository.deleteById(id);
		return "redirect:/client/clients";
	}
	
	@GetMapping("/editClientForm/{id}")
	public String editClientForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("client", clientRepository.findById(id));
		return "EditClient";
	}
	
	@PostMapping("/editClient/{id}")
	public String editClient(@PathVariable("id") int id,@ModelAttribute("client") Client client) {
		clientRepository.save(client);
		return "redirect:/client/clients";
	}
}
