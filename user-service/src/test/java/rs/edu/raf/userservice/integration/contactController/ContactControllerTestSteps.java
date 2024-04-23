package rs.edu.raf.userservice.integration.contactController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import rs.edu.raf.userservice.domain.dto.contact.ContactDto;
import rs.edu.raf.userservice.domain.dto.contact.ContactPostPutDto;
import rs.edu.raf.userservice.domain.dto.login.LoginRequest;
import rs.edu.raf.userservice.integration.LoginResponseForm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerTestSteps extends ContactControllerTestsConfig{

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ContactControllerTestsState contactControllerTestsState;

    @Given("Admin logovan")
    public void adminLogovan() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("admin@admin.com");
        loginRequest.setPassword("admin1234");

        try {
            String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

            ResultActions resultActions = mockMvc.perform(
                    post("/api/v1/employee/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(loginRequestJson)

            ).andExpect(status().isOk());

            MvcResult result = resultActions.andReturn();
            String loginResponse = result.getResponse().getContentAsString();
            LoginResponseForm loginResponseForm = objectMapper.readValue(loginResponse, LoginResponseForm.class);
            contactControllerTestsState.setJwtToken(loginResponseForm.getJwt());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("Amin trazi sve kontakte od usera sa id {string}")
    public void aminTraziSveKontakteOdUseraSaId(String id) {
        try {
            ResultActions resultActions = mockMvc.perform(
                    get("/api/v1/contact/" + id)
                            .header("Authorization", "Bearer " + contactControllerTestsState.getJwtToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());

            MvcResult result = resultActions.andReturn();
            String contacts = result.getResponse().getContentAsString();
            ContactDto[] contactDtos = objectMapper.readValue(contacts, ContactDto[].class);
            for (ContactDto dto: contactDtos) {
                assertEquals(Long.parseLong(id), dto.getUser().getUserId());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("Admin brise kontakt sa id {string}")
    public void adminBriseKontaktSaId(String id) {
        try {
            ResultActions resultActions = mockMvc.perform(
                    delete("/api/v1/contact/" + id)
                            .header("Authorization", "Bearer " + contactControllerTestsState.getJwtToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
