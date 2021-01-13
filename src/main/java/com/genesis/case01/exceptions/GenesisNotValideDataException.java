package com.genesis.case01.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;




public class GenesisNotValideDataException extends RuntimeException {
	
	public GenesisNotValideDataException(String s) {
        super(" Customer ::  " + s);
        
    }

}
