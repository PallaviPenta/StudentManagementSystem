package com.example.student.controller;
import com.example.student.entity.PersonRecord;
import com.example.student.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/persons")
public class PersonController {
    @Autowired private PersonService personService;
    @GetMapping
    public String listPersons(Model model, @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false) String groupName) {
        Page<PersonRecord> personPage;
        if (search != null && !search.isEmpty()) {
            personPage = personService.searchByName(search, page, size);
        } else if (groupName != null && !groupName.isEmpty()) {
            personPage = personService.searchByGroup(groupName, page, size);
        } else {
            personPage = personService.getAllPersons(page, size);
        }
        model.addAttribute("persons", personPage);
        return "person-list";
    }
    @GetMapping("/add")
    public String addPersonForm(Model model) {
        model.addAttribute("person", new PersonRecord());
        return "person-form";
    }
    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") PersonRecord person, BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "person-form";
        personService.addPerson(person);
        redirectAttributes.addFlashAttribute("message", "Person saved successfully!");
        return "redirect:/persons";
    }
    @GetMapping("/edit/{id}")
    public String editPersonForm(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));
        return "person-form";
    }
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        personService.deletePerson(id);
        redirectAttributes.addFlashAttribute("message", "Person deleted successfully!");
        return "redirect:/persons";
    }
}
