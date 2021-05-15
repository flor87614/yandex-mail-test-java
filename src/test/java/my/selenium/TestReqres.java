package my.selenium;

import in.reqres.api.ReqResDTO;
import in.reqres.api.ReqResPersonDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class TestReqres {
    @Test
    public void testCorrectEmails() {

        ReqResDTO reqResDTO = null;
        int page = 0;
        do {
            page++;
            Response response = RestAssured.get("https://reqres.in/api/users?page=" + page);
            reqResDTO = response.as(ReqResDTO.class);

            Optional<ReqResPersonDTO> person = reqResDTO.getData()
                    .stream()
                    .filter(p -> p.getFirst_name().equals("George") && p.getLast_name().equals("Bluth"))
                    .findFirst();
            person.ifPresent(p -> Assert.assertEquals("george.bluth@reqres.in", p.getEmail()));

            person = reqResDTO.getData()
                    .stream()
                    .filter(p -> p.getFirst_name().equals("Michael") && p.getLast_name().equals("Lawson"))
                    .findFirst();
            person.ifPresent(p -> Assert.assertEquals("michael.lawson@reqres.in", p.getEmail()));
        } while (reqResDTO.getPage() < reqResDTO.getTotal_pages());
    }
}
