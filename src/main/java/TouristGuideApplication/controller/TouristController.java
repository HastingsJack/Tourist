package TouristGuideApplication.controller;

import TouristGuideApplication.model.TouristAttraction;
import TouristGuideApplication.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class TouristController {
    private final TouristService touristService;


    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractions";
    }

    @GetMapping("/attractions/{name}")
    public String getAttraction(Model model, @PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "attraction-form";
    }

    @GetMapping("/attractions/add")
    public String addAttraction(Model model) {
        TouristAttraction attraction = new TouristAttraction();
        model.addAttribute("attraction", attraction);
        return "addAttraction-form";
    }

    @PostMapping("/attractions/save")
    public String addAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/{name}/edit")
    public String editAttractionForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        if(attraction == null) {
            throw new IllegalArgumentException("No such attraction");
        }
        model.addAttribute("attraction", attraction);
        return "editAttraction-form";
    }

    @PostMapping("/attractions/update")
    public String editAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/attractions";
    }

    @PostMapping("/attractions/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        if(attraction == null) {
            throw new IllegalArgumentException("No such attraction");
        }
        touristService.deleteAttraction(attraction);
        return "redirect:/attractions";
    }
}
