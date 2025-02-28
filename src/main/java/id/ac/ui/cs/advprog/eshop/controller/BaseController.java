package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.service.GenericService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T> {
    protected final GenericService<T> service;

    protected BaseController(GenericService<T> service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("entity", createEntity());
        return getCreateView();
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute T entity) {
        service.create(entity);
        return "redirect:/"+getBaseUrl()+"/list";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        List<T> allEntities = service.findAll();
        model.addAttribute("entities", allEntities);
        return getListView();
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") String id, Model model) {
        T entity = service.findById(id);
        model.addAttribute("entity", entity);
        return getEditView();
    }

    @PostMapping("/edit/{id}")
    public String editPost(@ModelAttribute T entity, @PathVariable("id") String id) {
        service.update(id, entity);
        return "redirect:/"+getBaseUrl()+"/list";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") String id) {
        service.delete(id);
        return "redirect:/"+getBaseUrl()+"/list";
    }

    protected abstract String getBaseUrl();
    protected abstract String getCreateView();
    protected abstract String getListView();
    protected abstract String getEditView();
    protected abstract T createEntity();
}
