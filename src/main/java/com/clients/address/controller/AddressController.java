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
 * Address Controller.
 * @author Edwin De Los Santos A.
 */
@Controller
@RequestMapping("/views/address")
public class AddressController {
	
	@Autowired
	private IAddressService addressServiceImpl;

	@Autowired
	private IClientService clientServiceImpl;
	
	/**
	 * This method takes users to the addresses page.
	 * @author Edwin De Los Santos A.
	 * @return Addresses route.
	 */
	@GetMapping("/")
	public String getAddresses(Model model) {
		List<Address> addressList = addressServiceImpl.getClientAddress();
		model.addAttribute("title", "Client Addresses");
		model.addAttribute("addresses", addressList);
		
		return "views/address/clientAddresses";
	}
	
	/**
	 * This method takes user to the create address form.
	 * @author Edwin De Los Santos A.
	 * @return Create Address route.
	 */
	@GetMapping("/createAddress")
	public String createAddress(Model model) {
		Address address = new Address();
		List<Client> clientList = clientServiceImpl.getClients();
		
		model.addAttribute("title", "Add Address");
		model.addAttribute("address", address);
		model.addAttribute("clients", clientList);
		
		return "views/address/createAddress";
	}
	
	/**
	 * This method saves an address and takes user to the addresses pages.
	 * @author Edwin De Los Santos A.
	 * @return Addresses route.
	 */
	@PostMapping("/saveAddress")
	public String saveAddress(@Validated @ModelAttribute Address address, BindingResult result, Model model, RedirectAttributes redirect) {
		List<Client> clients = clientServiceImpl.getClients();
		if(result.hasErrors()) {
			model.addAttribute("title", "Add Address");
			model.addAttribute("address", address);
			model.addAttribute("clients", clients);
			System.out.println("An error occurred saving address.");
			return "views/address/createAddress";
		}
		addressServiceImpl.save(address);
		redirect.addFlashAttribute("successMsg", "The address has been created successfully.");
		return "redirect:/views/address/";
	}
	
	/**
	 * This method takes user to the edit address form.
	 * @author Edwin De Los Santos A.
	 * @return edit Address route;
	 */
	@GetMapping("/editClientAddress/{id}")
	public String displayEditClientAddressForm(@PathVariable("id") Long addressId, Model model) {
		Address address = addressServiceImpl.findAddressById(addressId);
		List<Client> clients = clientServiceImpl.getClients();
		//Client client = address.getClient();
		model.addAttribute("title", "Edit Address");
		model.addAttribute("address", address);
		model.addAttribute("clients", clients);
		return "views/address/createAddress";
	}
	
	/**
	 * This method edits a specified address and takes user to the address page.
	 * @author Edwin De Los Santos A.
	 * @return Addresses route;
	 */
	@PostMapping("/editClientAddress/{id}")
	public String editClientAddress(@PathVariable("id") Long addressId, @Validated Address address, BindingResult result, Model model, RedirectAttributes redirect) {

		if(addressId > 0) {
			Address databaseAddress = addressServiceImpl.findAddressById(addressId);
			List<Client> clients = clientServiceImpl.getClients();
			
			if(result.hasErrors()) {
				model.addAttribute("title", "Edit Address");
				model.addAttribute("address", address);
				model.addAttribute("clients", clients);
				System.out.println("An error occurred editing address.");
				return "views/address/createAddress";
			}
			
			if(databaseAddress == null) {
				redirect.addFlashAttribute("errorMsg", "Error with Address ID");
				return "redirect:/views/client/";
			}
			
			databaseAddress.setAddress1(address.getAddress1());
			databaseAddress.setCity(address.getCity());
			databaseAddress.setClient(address.getClient());
			databaseAddress.setCountry(address.getCountry());
			databaseAddress.setProvince(address.getProvince());
			databaseAddress.setZipCode(address.getZipCode());
			
			addressServiceImpl.save(databaseAddress);
			
			redirect.addFlashAttribute("successMsg", "The Address has been edited successfully.");
			
			return "redirect:/views/address/";
			
		}else {
			redirect.addFlashAttribute("errorMsg", "Employee ID does not exist.");
			return "redirect:/views/address/";
		}
	}
	
	/**
	 * This method deletes a specified address.
	 * @author Edwin De Los Santos A.
	 * @return Addresses route;
	 */
	@GetMapping("/deleteClientAddress/{id}")
	public String deleteClientAddress(@PathVariable("id") Long addressId, RedirectAttributes redirect) {
		Address address = null;
		if(addressId>0) {
			address = addressServiceImpl.findAddressById(addressId);
			
			if(address == null) {
				redirect.addFlashAttribute("errorMsg", "Error with Client Address ID.");
				return "redirect:/views/address/";
			}
			
		} else {
			redirect.addFlashAttribute("errorMsg", "Address ID does not exist.");
			return "redirect:/views/address/";
		}
		
		redirect.addFlashAttribute("successMsg", "Client address has been deleted successfully.");
		addressServiceImpl.delete(addressId);
		return "redirect:/views/address/";
	}
}
