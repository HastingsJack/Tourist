package TouristGuideApplication.controller;

import TouristGuideApplication.model.TouristAttraction;
import TouristGuideApplication.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractions";
    }

    @GetMapping("/{name}")
    public String getAttraction(Model model, @PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "attraction-form";
    }

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction attraction = new TouristAttraction();
        List<String> tags = touristService.getAllTags();
        model.addAttribute("tags", tags);
        model.addAttribute("attraction", attraction);
        return "addAttraction-form";
    }

    @PostMapping("/save")
    public String addAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttractionForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        if(attraction == null) {
            throw new IllegalArgumentException("No such attraction");
        }
        model.addAttribute("attraction", attraction);
        return "editAttraction-form";
    }






    @PostMapping("/update")
    public String editAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        if(attraction == null) {
            throw new IllegalArgumentException("No such attraction");
        }
        touristService.deleteAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/tags")
    public String getTagsForAttraction(@PathVariable String name, Model model) {
        List<String> tags = touristService.getTagsForAttraction(name);

        model.addAttribute("tags", tags);
        model.addAttribute("name", name);
        return "tags";
    }
}
