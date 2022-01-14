package twiliosms.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twiliosms.api.models.ParkingSpot;

import javax.validation.Valid;

@RestController
@RequestMapping("twilio/sms")
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping(value="/", consumes = "application/json")
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        System.out.println("sending sms to: " + smsRequest.getPhoneNumber());

    
        service.sendSms(smsRequest);
    }
    @PutMapping(value = "/park", consumes = "application/json")
    public void changeParkingSpotState(@Valid @RequestBody ParkingSpot parkingSpot){
        ParkingSpotService parkingSpotService = new ParkingSpotService();

        parkingSpotService.setParkingSpotState(parkingSpot);

    }



    /*@PostMapping
    public void sendSmsTest(SmsRequestTest smsRequestTest) {
        service.sendSmsTest(smsRequestTest);
    }*/
}
