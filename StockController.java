package com.merugumala.stock.controller;

import com.merugumala.stock.model.StockItem;
import com.merugumala.stock.service.StockService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stocks")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        model.addAttribute("stocks", q == null ? service.findAll() : service.searchByName(q));
        model.addAttribute("q", q);
        return "list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("stockItem", new StockItem());
        return "add";
    }

    @PostMapping("/add")
    public String addSubmit(@Valid @ModelAttribute("stockItem") StockItem item, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        service.save(item);
        return "redirect:/stocks";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        StockItem item = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
        model.addAttribute("stockItem", item);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editSubmit(@PathVariable Long id, @Valid @ModelAttribute("stockItem") StockItem item, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        item.setId(id);
        service.save(item);
        return "redirect:/stocks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/stocks";
    }
}
