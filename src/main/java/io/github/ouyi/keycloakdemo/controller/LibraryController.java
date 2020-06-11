package io.github.ouyi.keycloakdemo.controller;


import io.github.ouyi.keycloakdemo.repository.BookRepository;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LibraryController {

    private final BookRepository bookRepository;

    public LibraryController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "/")
    public String getHome() {
        return "index";
    }

    @GetMapping(value = "/books")
    public String getBooks(Model model, HttpServletRequest request) {
        configCommonAttributes(model, request);
        model.addAttribute("books", bookRepository.readAll());
        return "books";
    }

    @GetMapping(value = "/manager")
    public String getManager(Model model, HttpServletRequest request) {
        configCommonAttributes(model, request);
        model.addAttribute("books", bookRepository.readAll());
        return "manager";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    private void configCommonAttributes(Model model, HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        model.addAttribute("name", token.getPrincipal());
    }

}
