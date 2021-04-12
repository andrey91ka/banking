package com.banking.bank.controller;

import com.banking.bank.model.Client;
import com.banking.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String Clients(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "/client/clients";
    }

    @GetMapping("/client-add")
    public String createClient(Client client) {
        return "client/client-add";
    }

    @PostMapping("/client-add")
    public String clientAdd(Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("client-delete/{id}")
    public String deleteClient(@PathVariable("id") String id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }

    @GetMapping("client-update/{id}")
    public String updateClient(@PathVariable("id") String id, Model model) {
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        return "client/client-update";
    }

    @PostMapping("/client-update")
    public String clientUpd(Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }
}
