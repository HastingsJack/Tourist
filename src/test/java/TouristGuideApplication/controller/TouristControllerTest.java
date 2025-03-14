package TouristGuideApplication.controller;

import TouristGuideApplication.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


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
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getAllAttractions() {
    }

    @Test
    void getAttraction() {
    }

    @Test
    void addAttraction() {
    }

    @Test
    void testAddAttraction() {
    }

    @Test
    void editAttractionForm() {
    }

    @Test
    void editAttraction() {
    }

    @Test
    void deleteAttraction() {
    }

    @Test
    void getTagsForAttraction() {
    }
}