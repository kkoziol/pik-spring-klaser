package pl.pik.kk.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import pl.pik.kk.entities.Moneta;
import pl.pik.kk.repositories.springdata.MonetaRepository;


@Service
@Primary
@Qualifier("spring-data")
//@Profile("default")
@Profile("!dev")
public class KlaserServiceJPAImpl implements KlaserService {

    @Autowired
    private MonetaRepository bazaDanych;

    @Override
    public List<Moneta> findAll() {
        List<Moneta> l = new ArrayList<>();
        for (Moneta item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Moneta> findAllToSell() {
        List<Moneta> l = new ArrayList<>();
        for (Moneta item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Moneta> findById(Long id) {
        return Optional.ofNullable(bazaDanych.findByNumerKatalogowy(id));
    }

    @Override
    public Optional<Moneta> create(Moneta moneta) {
        return Optional.of(bazaDanych.save(moneta));
    }

    @Override
    public Optional<Moneta> edit(Moneta moneta) {
        return Optional.of(bazaDanych.save(moneta));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        bazaDanych.deleteByNumerKatalogowy(id);
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Moneta> findLatest3() {
        return Collections.emptyList();
    }

}
