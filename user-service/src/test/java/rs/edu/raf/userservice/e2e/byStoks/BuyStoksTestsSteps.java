package rs.edu.raf.userservice.e2e.byStoks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import rs.edu.raf.userservice.domain.dto.login.LoginRequest;
import rs.edu.raf.userservice.domain.dto.user.UserDto;
import rs.edu.raf.userservice.e2e.byStoks.helper.*;
import rs.edu.raf.userservice.integration.LoginResponseForm;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BuyStoksTestsSteps extends BuyStoksTestsConfig{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;
//
//    private CompanyAccountDto companyAccountDto;
//    private CompanyAccountDto afterBuyCompanyAccountDto;
//    private StockDto[] stockDtos;
//
//    @Given("Korisnik se loguje na aplikaciju kao supervisor sa emailom {string} i lozinkom {string}")
//    public void korisnikSeLogujeNaAplikacijuKaoSupervisorSaEmailomILozinkom(String email, String password) {
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail(email);
//        loginRequest.setPassword(password);
//
//        try {
//            String loginRequestJson = objectMapper.writeValueAsString(loginRequest);
//
//            ResultActions resultActions = mockMvc.perform(
//                    post("/api/v1/employee/auth/login")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .content(loginRequestJson)
//
//            ).andExpect(status().isOk());
//
//            MvcResult result = resultActions.andReturn();
//            String loginResponse = result.getResponse().getContentAsString();
//            LoginResponseForm loginResponseForm = objectMapper.readValue(loginResponse, LoginResponseForm.class);
//            jwtToken = loginResponseForm.getJwt();
//            System.out.println("JWT: " + loginResponseForm.getJwt());
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
//    }
//
//    @And("preverava balans na racunu")
//    public void preveravaBalansNaRacunu() {
//        try {
//            ResultActions resultActions = mockMvc.perform(
//                    get("/api/v1/companyAccount/getAll")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .header("Authorization", "Bearer " + jwtToken)
//            ).andExpect(status().isOk());
//
//            MvcResult result = resultActions.andReturn();
//            String response = result.getResponse().getContentAsString();
//
//            CompanyAccountDto[] companyAccountDtos = objectMapper.readValue(response, CompanyAccountDto[].class);
//            for (CompanyAccountDto companyAccDto : companyAccountDtos) {
//                if(companyAccDto.getAccountNumber().equals("3030303030303030")) {
//                    companyAccountDto = companyAccDto;
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
//    }
//
//    @And("pregleda listu svih stock-ova")
//    public void pregledaListuSvihStockOva() throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8083/api/v1/stock";
//
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            String responseBody = response.getBody();
//            stockDtos = objectMapper.readValue(responseBody, StockDto[].class);
//        } else {
//            fail(response.getStatusCode().toString());
//        }
//    }
//
//    @When("korisnik izabere stock sa liste za kupovinu i unese kolicinu")
//    public void korisnikIzabereStockSaListeZaKupovinuIUneseKolicinu() throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8083/api/v1/stock/buyStock";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        BuyStockDto buyStockDto = new BuyStockDto();
//        buyStockDto.setAon(false);
//        buyStockDto.setAmount(10);
//        buyStockDto.setEmployeeId(6l);
//        buyStockDto.setTicker(stockDtos[0].getTicker());
//        buyStockDto.setMargin(false);
//        buyStockDto.setLimitValue(0.0);
//        buyStockDto.setStopValue(0.0);
//
//
//        HttpEntity<BuyStockDto> requestEntity = new HttpEntity<>(buyStockDto, headers);
//        System.out.println(requestEntity.getBody().toString());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            String responseBody = response.getBody();
//            System.out.println(responseBody);
//        } else {
//            fail(response.getStatusCode().toString());
//        }
//    }
//
//    @Then("proverava da li je stock dodat u listu kupljenih stock-ova")
//    public void proveravaDaLiJeStockDodatUListuKupljenihStockOva() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8083/api/v1/stock/myStock/getAll";
//
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            String responseBody = response.getBody();
//            System.out.println(
//                    responseBody
//            );
//        } else {
//            fail(response.getStatusCode().toString());
//        }
//    }
//
//    @And("proverava da li je balans na racunu smanjen za odgovarajucu sumu")
//    public void proveravaDaLiJeBalansNaRacunuSmanjenZaOdgovarajucuSumu() {
//        try {
//            ResultActions resultActions = mockMvc.perform(
//                    get("/api/v1/companyAccount/getAll")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .header("Authorization", "Bearer " + jwtToken)
//            ).andExpect(status().isOk());
//
//            MvcResult result = resultActions.andReturn();
//            String response = result.getResponse().getContentAsString();
//
//            CompanyAccountDto[] companyAccountDtos = objectMapper.readValue(response, CompanyAccountDto[].class);
//            for (CompanyAccountDto companyAccDto : companyAccountDtos) {
//                if(companyAccDto.getAccountNumber().equals("3030303030303030")) {
//                    afterBuyCompanyAccountDto = companyAccDto;
//                    break;
//                }
//            }
//            assertEquals(companyAccountDto.getAvailableBalance(), afterBuyCompanyAccountDto.getAvailableBalance());
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
//    }

    private UserDto userDto;
    private String adminJwtToken;
    private UserAccountDto userAccountDtoBeforeCredit;
    @Given("Korisnik se loguje na aplikaciju sa email {string} i sifrom {string}")
    public void korisnikSeLogujeNaAplikacijuSaEmailISifrom(String email, String password) {
        try{
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(email);
            loginRequest.setPassword(password);

            LoginRequest adminRequest = new LoginRequest();
            adminRequest.setEmail("admin@admin.com");
            adminRequest.setPassword("admin1234");

            String adminLoginRequestJson = objectMapper.writeValueAsString(adminRequest);

            ResultActions adminActions = mockMvc.perform(
                    post("/api/v1/employee/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(adminLoginRequestJson)

            ).andExpect(status().isOk());

            MvcResult adminResult = adminActions.andReturn();
            String loginResponse = adminResult.getResponse().getContentAsString();
            LoginResponseForm loginResponseForm = objectMapper.readValue(loginResponse, LoginResponseForm.class);
            adminJwtToken = loginResponseForm.getJwt();

            String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

            ResultActions resultActions = mockMvc.perform(
                    post("/api/v1/user/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(loginRequestJson)

            ).andExpect(status().isOk());

            ResultActions findbyEmail = mockMvc.perform(
                    get("/api/v1/user/findByEmail/" + email)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer " + adminJwtToken)
            ).andExpect(status().isOk());

            MvcResult result = findbyEmail.andReturn();
            String response = result.getResponse().getContentAsString();
            userDto = objectMapper.readValue(response, UserDto.class);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    @And("korisnik cekira balans na svom {string} racunu")
    public void korisnikCekiraBalansNaSvomRacunu(String mark) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/api/v1/account/getByUser/" + userDto.getId();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            List<UserAccountDto> accountDtos = objectMapper.readValue(responseBody, new TypeReference<List<UserAccountDto>>(){});
            for (UserAccountDto userAccountDto: accountDtos) {
                if (userAccountDto.getCurrency().getMark().equals(mark)) {
                    userAccountDtoBeforeCredit = userAccountDto;
                }
            }
            System.out.println(userAccountDtoBeforeCredit);
        } else {
            fail(response.getStatusCode().toString());
        }
    }

    @When("korisnik salje zahtev za kredit")
    public void korisnikSaljeZahtevZaKredit() {
        CreditRequestCreateDto creditRequestCreateDto = new CreditRequestCreateDto();
        creditRequestCreateDto.setAmount(1000.0);
        creditRequestCreateDto.setPaymentPeriod(12);
        creditRequestCreateDto.setUserId(userDto.getId());
        creditRequestCreateDto.setAccountNumber(userAccountDtoBeforeCredit.getAccountNumber());
        creditRequestCreateDto.setName("Kes kredit");
        creditRequestCreateDto.setEmployed(true);
        creditRequestCreateDto.setApplianceReason("Kredit za stan");
        creditRequestCreateDto.setCurrencyMark(userAccountDtoBeforeCredit.getCurrency().getMark());
        creditRequestCreateDto.setDateOfEmployment(System.currentTimeMillis());

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/api/v1/credit-request";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreditRequestCreateDto> requestEntity = new HttpEntity<>(creditRequestCreateDto, headers);
        System.out.println(requestEntity.getBody().toString());

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.getBody());
        } else {
            fail(response.getStatusCode().toString());
        }
    }

    @Then("Zaposleni se loguje na aplikaciju i odobrava zahtev")
    public void zaposleniSeLogujeNaAplikacijuIOdobravaZahtev() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/api/v1/credit-request";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            CreditRequestDto creditReqDto;
            List<CreditRequestDto> creditRequestCreateDtos = objectMapper.readValue(responseBody, new TypeReference<List<CreditRequestDto>>(){});
            creditReqDto = creditRequestCreateDtos.stream().max((c1, c2) -> c1.getCreditRequestId().compareTo(c2.getCreditRequestId())).get();


            ProcessCreditRequestDto processCreditRequestDto = new ProcessCreditRequestDto();
            processCreditRequestDto.setAccepted(true);
            processCreditRequestDto.setCreditRequestId(creditReqDto.getCreditRequestId());
            processCreditRequestDto.setEmployeeId(1l);


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ProcessCreditRequestDto> requestEntity = new HttpEntity<>(processCreditRequestDto, headers);
            System.out.println(requestEntity.getBody().toString());

            ResponseEntity<String> responseCredit = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);



        } else {
            fail(response.getStatusCode().toString());
        }
    }

    @And("korisnik cekira balans")
    public void korisnikCekiraBalans() throws JsonProcessingException, InterruptedException {
        Thread.sleep(35000);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/api/v1/account/getByUser/" + userDto.getId();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            UserAccountDto userAccountAfterCredit = null;
            List<UserAccountDto> accountDtos = objectMapper.readValue(responseBody, new TypeReference<List<UserAccountDto>>(){});
            for (UserAccountDto userAccountDto: accountDtos) {
                if (userAccountDto.getCurrency().getMark().equals("EUR")) {
                    userAccountAfterCredit = userAccountDto;
                }
            }

            assertTrue(userAccountDtoBeforeCredit.getAvailableBalance().compareTo(userAccountAfterCredit.getAvailableBalance()) == -1);
        } else {
            fail(response.getStatusCode().toString());
        }

    }


}
