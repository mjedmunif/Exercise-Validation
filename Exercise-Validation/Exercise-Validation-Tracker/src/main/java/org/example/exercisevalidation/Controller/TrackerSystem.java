package org.example.exercisevalidation.Controller;


import jakarta.validation.Valid;
import org.example.exercisevalidation.Api.ApiResponse;
import org.example.exercisevalidation.Modell.Tracker;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/tracker")
public class TrackerSystem {

    ArrayList<Tracker> trackers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Tracker> getAllTracker(){
        return trackers;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewTracker(@RequestBody @Valid Tracker tracker , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            trackers.add(tracker);
        }
        return ResponseEntity.status(200).body(new ApiResponse("added new tracker successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateTracker(@PathVariable int index , @RequestBody @Valid Tracker tracker , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        trackers.set(index , tracker);
        return ResponseEntity.status(200).body(new ApiResponse("Updated tracker successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTracker(@PathVariable int index){
        trackers.remove(index);
        return new ApiResponse("deleted tracker successfully");
    }


    @PutMapping("/change/{index}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable int index , @PathVariable String status){
        for (Tracker t : trackers){
            if (index != t.getId()){
               return ResponseEntity.status(400).body(new ApiResponse("invalid id"));
            }
              else if (status.equalsIgnoreCase("completed")) {
                t.setStatus("completed");
                return ResponseEntity.status(200).body(new ApiResponse("changed status to completed successfully"));
            } else if (status.equalsIgnoreCase("in-progress")) {
                t.setStatus("in-progress");
                return ResponseEntity.status(200).body(new ApiResponse("changed status to in progress successfully"));
            }else {
                t.setStatus("not-started");
                return ResponseEntity.status(200).body(new ApiResponse("changed status to Not Started successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Status must be one of : completed or not Started or in progress "));
    }

}
