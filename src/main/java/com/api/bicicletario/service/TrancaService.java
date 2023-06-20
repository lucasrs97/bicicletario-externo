package com.api.bicicletario.service;

import com.api.bicicletario.model.Tranca;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrancaService {
    private final Map<Integer, Tranca> trancasMap;
    private Integer nextId;

    public TrancaService(List<Tranca> trancas) {
        trancasMap = new HashMap<>();
        nextId = 1;

        for (Tranca tranca : trancas) {
            tranca.setId(nextId);
            trancasMap.put(nextId, tranca);
            nextId++;
        }
    }

    public List<Tranca> getTrancas() {
        return new ArrayList<>(trancasMap.values());
    }

    public Tranca getTrancaById(Integer id) {
        return trancasMap.get(id);
    }

    public Tranca createTranca(Tranca tranca) {
        if (trancasMap.containsKey(tranca.getId())) {
            return null;
        }

        int id = nextId++;
        Tranca newTranca = new Tranca(id, tranca.getBicicleta(), tranca.getNumero(), tranca.getLocalizacao(),
                tranca.getAnoDeFabricacao(), tranca.getModelo(), tranca.getStatus());

        trancasMap.put(id, newTranca);
        return newTranca;
    }


    public Tranca updateTranca(Tranca tranca) {
        if (tranca == null) {
            return null;
        }

        if (trancasMap.containsKey(tranca.getId())) {
            trancasMap.put(tranca.getId(), tranca);
            return tranca;
        }

        return null;
    }


    public void deleteTranca(Integer id) {
        trancasMap.remove(id);
    }



}
