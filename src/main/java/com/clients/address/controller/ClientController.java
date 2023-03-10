package com.clients.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clients.address.model.Address;
import com.clients.address.model.Client;
import com.clients.address.services.IAddressService;
import com.clients.address.services.IClientService;

/**
 * Client Controller.
 * @author Edwin De Los Santos A.
 */
@Controller
@RequestMapping("/views/client")
public class ClientController {

	@Autowired
	private IClientService clientServiceImpl;
	
	@Autowired
	private IAddressService addressServiceImpl;
	
	/**
	 * This method takes users to the clients page.
	 * @author Edwin De Los Santos A.
	 * @return Clients route.
	 */
	@GetMapping("/")
	public String getClients(Model model) {
		List<Client> clientList = clientServiceImpl.getClients();
		model.addAttribute("title", "List of Clients");
		model.addAttribute("clients", clientList);
		return "views/client/clients";
	}
	
	/**
	 * This method takes user to the create client form.
	 * @author Edwin De Los Santos A.
	 * @return Create client route.
	 */
	@GetMapping("/createClient")
	public String createClient(Model model) {
		Client client = new Client();
		model.addAttribute("title", "Add Client");
		model.addAttribute("client", client);
		return "views/client/createClient";
	}
	
	/**
	 * This method saves an address and takes user to the clients pages.
	 * @author Edwin De Los Santos A.
	 * @return Clients route.
	 */
	@PostMapping("/saveClient")
	public String saveClient(@Validated @ModelAttribute Client client, BindingResult result, Model model, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Add Client");
			model.addAttribute("client", client);
			System.out.println("An error ocurred saving client.");
			return "views/client/createClient"; 
		}
		
		clientServiceImpl.save(client);
		redirect.addFlashAttribute("successMsg", "The client has been created successfully.");
		return "redirect:/views/client/";
	}
	
	/**
	 * This method takes user to the edit client form.
	 * @author Edwin De Los Santos A.
	 * @return Edit client route;
	 */
	@GetMapping("/editClient/{id}")
	public String displayEditClientForm(@PathVariable("id") Long clientId, Model model, RedirectAttributes redirect) {
		Client client = null;
		if(clientId > 0) {
			client = clientServiceImpl.findClientById(clientId);
			if(client == null) {
				redirect.addFlashAttribute("errorMsg", "Error with Client ID.");
				return "redirect:/views/client/";
			}
		}else {
			redirect.addFlashAttribute("errorMsg", "Client ID does not exist.");
			return "redirect:/views/client/";
		}
		
		model.addAttribute("title", "Edit Client");
		model.addAttribute("client", client);
		return "views/client/createClient";
	}
	
	/**
	 * This method edits a specified client and takes user to the client page.
	 * @author Edwin De Los Santos A.
	 * @return Clients route;
	 */
	@PostMapping("/editClient/{id}")
	public String editClient(@PathVariable("id") Long clientId, @Validated Client client, BindingResult result, Model model, RedirectAttributes redirect) {
		if(clientId > 0) {
			Client databaseClient = clientServiceImpl.findClientById(clientId);
			
			if(result.hasErrors()) {
				model.addAttribute("title", "Edit Client");
				model.addAttribute("client", client);
				System.out.println("An error ocurred saving client.");
				return "views/client/createClient"; 
			}
			
			if(client == null) {
				redirect.addFlashAttribute("errorMsg", "Error with Client ID");
				return "redirect:/views/client/";
			}
			
			databaseClient.setFullName(client.getFullName());
			databaseClient.setEmail(client.getEmail());
			databaseClient.setBirthdate(client.getBirthdate());
			databaseClient.setPhoneNumber(client.getPhoneNumber());
			databaseClient.setPhotoId(client.getPhotoId());
			
			clientServiceImpl.save(databaseClient);
			redirect.addFlashAttribute("successMsg", "The client has been edited successfully.");
			
			return "redirect:/views/client/";
			
		}else {
			redirect.addFlashAttribute("errorMsg", "Client ID does not exist.");
			return "redirect:/views/client/";
		}
	}
	
	/**
	 * This method deletes a specified client.
	 * @author Edwin De Los Santos A.
	 * @return Clients route;
	 */
	@GetMapping("/deleteClient/{id}")
	public String deleteClient(@PathVariable("id") Long clientId, Model model, RedirectAttributes redirect) {
		Client client = null;
		if(clientId > 0) {
			client = clientServiceImpl.findClientById(clientId);
			if(client == null) {
				redirect.addFlashAttribute("errorMsg", "Error with Client ID");
				return "redirect:/views/client/";
			}
		}else {
			redirect.addFlashAttribute("errorMsg", "Client ID does not exist.");
			return "redirect:/views/client/";
		}
		
		List<Address> addresses = addressServiceImpl.getClientAddress();
		for(int i=0; i<addresses.size(); i++) {
			if(addresses.get(i).getClient() == client) {
				addressServiceImpl.delete(addresses.get(i).getId());
			}
		}
		
		redirect.addFlashAttribute("successMsg", "Client record has been deleted successfully.");
		clientServiceImpl.delete(clientId);
		return "redirect:/views/client/";
	}
	
}

