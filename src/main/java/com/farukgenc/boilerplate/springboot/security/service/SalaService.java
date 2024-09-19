package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.model.Sala;
import com.farukgenc.boilerplate.springboot.repository.SalaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {


    @Autowired
    private SalaRepository salaRepository;

    @Transactional
    public List<Sala> findAll() {
        return (List<Sala>) salaRepository.findAll();
    }


    //CRUD:


    public Optional<Sala> findById(Integer idSala) {
        return salaRepository.findById(idSala);

    }

    public Sala save(Sala sala) {
        try {
            return salaRepository.save(sala);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la sala: " + e.getMessage(), e);
        }
    }
}

