package org.example.exercisevalidtioneventsystem.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jdk.jfr.Event;
import org.example.exercisevalidtioneventsystem.Api.ApiResponse;
import org.example.exercisevalidtioneventsystem.Modell.Events;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
@Validated
public class EventSystem {
    ArrayList<Events> events = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Events> getEvents(){
        return events;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewEvent(@RequestBody @Valid Events event , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("added event successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateEvent(@PathVariable int index , @RequestBody @Valid Events event , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        events.set(index , event);
        return ResponseEntity.status(200).body(new ApiResponse("Updated Event successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index){
        events.remove(index);
        return new ApiResponse("delete event successfully");
    }

    @PutMapping("/change/{id}/{newCapacity}")
    public ResponseEntity<?> changeCapacity(@PathVariable int id , @PathVariable int newCapacity){
        if (newCapacity > 25){
            return ResponseEntity.status(400).body(new ApiResponse("capacity should be more than 25"));
        }
        for (Events e : events){
            if (e.getId() == id){
                e.setCapacity(newCapacity);
                return ResponseEntity.status(200).body(new ApiResponse("change capacity to " + newCapacity + " successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("invalid id"));
    }



    @GetMapping("/searchByid/{index}")
    public ResponseEntity<?> searchById(@PathVariable int index){
        for (Events e : events){
            if (index == e.getId()){
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("invalid input"));
    }
}
