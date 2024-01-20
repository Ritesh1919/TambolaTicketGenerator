package com.Tambola.TambolaServiceImplmentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tambola.Entity.Tambola;
import com.Tambola.Repository.TambolaRepository;
import com.Tambola.Service.TambolaService;

@Service
public class TambolaServiceImpl implements TambolaService {
	@Autowired
	TambolaRepository tambolaRepo;

	// method for post data
	@Override
	public Map<String, List<List<Integer>>> generateTambolaSets(int sets) {
		// TODO Auto-generated method stub
		Map<String, List<List<Integer>>> ticketsMap = new HashMap<>();

		for (int set = 0; set < sets; set++) {
			List<List<Integer>> ticketList = generateSingleTambolaSet();
			Tambola tambolaTicket = new Tambola(ticketList);
			tambolaRepo.save(tambolaTicket);

			ticketsMap.put(String.valueOf(tambolaTicket.getId()), ticketList);
		}

		return ticketsMap;

	}

	private List<List<Integer>> generateSingleTambolaSet() {
		// Logic to fill the Tambola ticket according to the rules goes here
		// Similar to the previous examples
		List<List<Integer>> single = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			int[] arr = new int[9];
			for (int j = 0; j < 9; j++) {

				// random number range
				long max_num = (j + 1) * 10;
				long min = max_num - 10;
				int random_num = (int) (min + (max_num - min) * Math.random());

				// now we are checking list is empty or not
				if (single.isEmpty()) {

					arr[j] = random_num;

				} else {

					/*
					 * now we are checking the upper element of the current element that is
					 */
					if (random_num != single.get(i - 1).get(j)) {
						if (2 - i == 0 && random_num != single.get(i - 2).get(j)) {
							arr[j] = random_num;
						} else {
							arr[j] = random_num;

						}
					} else {

						arr[j] = 0;

					}

				}
			}
			List<Integer> int_list = Arrays.asList(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7],
					arr[8]);
			single.add(int_list);
		}
		System.out.println(single);
		return single;

	}

	// method for get all data
	@Override
	public List<List<Tambola>> getAllData() {
		// TODO Auto-generated method stub
		List<Tambola> tambola = tambolaRepo.findAll();
		List<List<Tambola>> tambolaList = new ArrayList<>();
		tambolaList.add(tambola);
		return tambolaList;
	}

}
