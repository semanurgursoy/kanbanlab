package com.kanban.kanbanlab.business;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusinessRules {
    public static ResponseEntity<String> Run(ResponseEntity<String>...states)
    {
        for(ResponseEntity<String> state:states)
        {
            if (!state.getStatusCode().equals(HttpStatus.OK))
            {
                return new ResponseEntity<>(state.getBody(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
