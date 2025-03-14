package TouristGuideApplication.controller;

import TouristGuideApplication.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
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
    void addAttraction() {
    }

    @Test
    void testAddAttraction() {
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
    void deleteAttraction() throws Exception {

        //arrange
        String attractionName = "YellowStone";

        doNothing().when(touristService).deleteAttraction(any());

        // act
        mockMvc.perform(post("/attractions/delete/" + attractionName))
                .andExpect(status().is3xxRedirection()) // Redirect expected
                .andExpect(redirectedUrl("/attractions"));

        // assert
        verify(touristService, times(1)).deleteAttraction(any());
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