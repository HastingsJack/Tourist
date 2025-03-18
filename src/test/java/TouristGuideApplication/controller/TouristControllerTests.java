package TouristGuideApplication.controller;

import TouristGuideApplication.model.TouristAttraction;
import TouristGuideApplication.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TouristController.class)
class TouristControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristService touristService;

    @InjectMocks
    private TouristController touristController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    //kasper//

    @Test
    void getAllAttractions() throws Exception {
        List<TouristAttraction> attractions = List.of(
                new TouristAttraction("Eiffel Tower", "Iconic landmark", "https://eiffel.com",
                        List.of("historic", "landmark"), List.of("Paris")),
                new TouristAttraction("Colosseum", "Ancient Roman amphitheater", "https://colosseum.com",
                        List.of("historic", "architecture"), List.of("Rome"))
        );

        when(touristService.getAllAttractions()).thenReturn(attractions);
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractions"))
                .andExpect(model().attributeExists("attractions"))
                .andExpect(model().attribute("attractions", attractions));
    }

    @Test
    void getAttraction() throws Exception {
        String attractionName = "Eiffel Tower";
        TouristAttraction attraction = new TouristAttraction(attractionName, "Iconic landmark", "https://eiffel.com",
                List.of("historic", "landmark"), List.of("Paris"));

        Mockito.when(touristService.getAttractionByName(attractionName)).thenReturn(attraction);
        mockMvc.perform(get("/attractions/" + attractionName))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-form"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attribute("attraction", attraction));
    }


    //kasper end//



    //Jack//
    @Test
    void testAddAttraction() throws Exception{
        List<String> mockTags = Arrays.asList("Nature", "Paid", "Diving");
        when(touristService.getAllTags()).thenReturn(mockTags);

        mockMvc.perform(MockMvcRequestBuilders.get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addAttraction-form"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("tags", mockTags));
    }

    @Test
    void testSaveAttraction() throws Exception{
        TouristAttraction attraction = new TouristAttraction();
        attraction.setName("TestAttraction");
        attraction.setDescription("Test");

        mockMvc.perform(MockMvcRequestBuilders.post("/attractions/save")
                .flashAttr("attraction",attraction))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/attractions"));

        verify(touristService, times(1)).addAttraction(attraction);
    }
    //Jack END//



    //Johan//
    @Test
    void testEditAttractionForm() throws Exception {

        TouristAttraction mockAttraction = new TouristAttraction(
                "yellowstone", "Big field", "www.yellowstone.netlix", new ArrayList<>(), new ArrayList<>());
        when(touristService.getAttractionByName("yellowstone")).thenReturn(mockAttraction);

        mockMvc.perform(get("/attractions/yellowstone/edit"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attribute("attraction", mockAttraction))
                .andExpect(view().name("editAttraction-form"));

        when(touristService.getAttractionByName("nonExisting")).thenReturn(null);

        mockMvc.perform(get("/nonExisting/edit"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEditAttraction() throws Exception {

        //arrange
        TouristAttraction mockAttraction = new TouristAttraction(
                "YellowStone", "Big field", "www.yellowstone.netlix", new ArrayList<>(),
                new ArrayList<>());

        //act and assert
        mockMvc.perform(post("/attractions/update")
                .flashAttr("attraction", mockAttraction))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));


        verify(touristService, times(1)).updateAttraction(mockAttraction);

    }

    //Johan end//


    //Jacob//

    @Test
    void getTagsForAttraction() throws Exception {
        //arrange
        String attractionName = "YellowStone";
        List<String> mockTags = List.of("Nature", "Adventure", "Hiking");

        when(touristService.getTagsForAttraction(attractionName)).thenReturn(mockTags);

        // act
        mockMvc.perform(get("/attractions/" + attractionName + "/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("tags", mockTags))
                .andExpect(model().attribute("name", attractionName));

        // assert
        verify(touristService, times(1)).getTagsForAttraction(attractionName);
    }


    //Jacob End//
}