package com.Tambola.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Tambola.Entity.Tambola;

@Service
public interface TambolaService {
    public Map<String, List<List<Integer>>> generateTambolaSets(int sets); 
    
    public List<List<Tambola>> getAllData();
}
