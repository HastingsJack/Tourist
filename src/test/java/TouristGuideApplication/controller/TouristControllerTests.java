package TouristGuideApplication.controller;

import TouristGuideApplication.model.TouristAttraction;
import TouristGuideApplication.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void getAllAttractions() {
    }

    @Test
    void getAttraction() {
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
    void editAttractionForm() {
    }

    @Test
    void editAttraction() {
    }

    //Johan end//


    //Jacob//
    @Test
    void deleteAttraction_ShouldRedirect_WhenAttractionExists() throws Exception {
        // Arrange
        String attractionName = "YellowStone";
        TouristAttraction mockAttraction = new TouristAttraction(null, attractionName, null, null,null);

        when(touristService.getAttractionByName(attractionName)).thenReturn(mockAttraction);
        doNothing().when(touristService).deleteAttraction(any());

        // Act & Assert
        mockMvc.perform(post("/attractions/delete/" + attractionName))
                .andExpect(status().is3xxRedirection()) // Expecting redirect
                .andExpect(redirectedUrl("/attractions"));

        // Verify that deleteAttraction was called once
        verify(touristService, times(1)).deleteAttraction(any());
    }

    @Test
    void deleteAttraction_ShouldRedirect_WhenAttractionDoesNotExist() throws Exception {
        // Arrange
        String attractionName = "NonExistent";

        when(touristService.getAttractionByName(attractionName)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(post("/attractions/delete/" + attractionName))
                .andExpect(status().is3xxRedirection()) // Expecting redirect
                .andExpect(redirectedUrl("/attractions"));

        // Verify that deleteAttraction was never called since the attraction doesn't exist
        verify(touristService, times(0)).deleteAttraction(any());
    }



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