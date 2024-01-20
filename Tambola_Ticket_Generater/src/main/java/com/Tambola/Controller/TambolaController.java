package com.Tambola.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Tambola.Entity.Tambola;
import com.Tambola.Exceptions.NoDataFoundExceptionclass;
import com.Tambola.Service.TambolaService;

@RestController
public class TambolaController {
	@Autowired
	TambolaService tambolaService;

	// method for insert Ticket
	@GetMapping("/generatetickets/{sets}")
	public ResponseEntity<Map<String, List<List<Integer>>>> generateTambolaSets(
			@PathVariable @jakarta.validation.constraints.Min(value = 1, message = "Sets must be at least 1") int sets) {
		Map<String, List<List<Integer>>> result = tambolaService.generateTambolaSets(sets);

		// Checking if the result is not null
		if (result != null) {
			// Returning ResponseEntity with OK status
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			// If result is null, returning ResponseEntity with NOT_FOUND status
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getalltambolatickets")
	public List<List<Tambola>> getAllData() throws NoDataFoundExceptionclass {

		List<List<Tambola>> tambolaData = tambolaService.getAllData();
		if (tambolaData.get(0).isEmpty()) {
			throw new NoDataFoundExceptionclass("We are very sorry there is not data in DataBase");
		} else {
			return tambolaData;
		}
	}

}
