package com.epam.recommendation.managment.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.epam.recommendation.management.application.controller.DestinationController;
import com.epam.recommendation.management.application.service.DestinationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DestinationController.class)

public class DestinationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinationServiceImpl destinationService;

//    @Test
//    public void testUpdateDestination() throws Exception {
//        Long destinationId = 1L;
//        String destinationJson = "{\"name\": \"New Name\", \"description\": \"Updated description\"}";
//
//        // Assuming the service layer returns some updated object or void
//        given(destinationService.updateDestination(eq(destinationId), any(Destination.class))).willReturn(true);
//
//        mockMvc.perform(patch("/destinations/{id}", destinationId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(destinationJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("New Name"))
//                .andExpect(jsonPath("$.description").value("Updated description"));
//    }

//    @Test
//    public void testUpdateDestinationNotFound() throws Exception {
//        Long destinationId = 1L;
//        String destinationJson = "{\"name\": \"New Name\"}";
//
//        // Simulate not found situation
//        given(destinationService.updateDestination(eq(destinationId), any(Destination.class))).willReturn(false);
//
//        mockMvc.perform(patch("/destinations/{id}", destinationId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(destinationJson))
//                .andExpect(status().isNotFound());
//    }
}
