package com.mergen.vtys.vtysdatabaseap.Controller;

import com.mergen.vtys.vtysdatabaseap.Model.Career;
import com.mergen.vtys.vtysdatabaseap.Model.UserDetails;
import com.mergen.vtys.vtysdatabaseap.Repository.UserDetailsRepository;
import com.mergen.vtys.vtysdatabaseap.Service.CareerService;
import com.mergen.vtys.vtysdatabaseap.Service.UserDetailsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "career")
@RequiredArgsConstructor
@Slf4j
public class CareerController {

    @Autowired
    private final CareerService careerService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Career>> getCareersList(){
        List<Career> careerList = careerService.getCareerList();
        log.info("All Careers Returned - {}",careerList);
        return ResponseEntity.ok(careerList);
    }


        @GetMapping(value = "list/{id}")
    public ResponseEntity<Optional<Career>> getCareersById(@PathVariable Long id){
        Optional<Career> status = careerService.getCareerById(id);
        log.info("Career Got by Name Status - {}",status);
        return  ResponseEntity.ok(status);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Career> createCareer(@RequestBody Career career){
        Career status = careerService.Create(career);
        log.info("Career Added Status - {}",status);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<String> updateCareer(@PathVariable Long id, @RequestBody Career career) {
        String status = careerService.Update(id, career);
        log.info("Career Updated Status - {}",status);
        return ResponseEntity.ok(career.getAdmin_name() + " updated!");
    }

    @DeleteMapping(value = "remove/{id}")
    public ResponseEntity<String> deleteCareer(@PathVariable() Long id){
        String status = careerService.Delete(id);
        log.info("Career Deleted Status - {}",status);
        return ResponseEntity.ok(id + " th deleted!");
    }

}