package pl.pik.kk.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import pl.pik.kk.entities.Moneta;
import pl.pik.kk.entities.Status;
import pl.pik.kk.repositories.MonetaAlreadyExistsException;
import pl.pik.kk.repositories.MonetyRepository;
import pl.pik.kk.repositories.NoSuchMonetaException;


@Service
@Profile("dev")
@Qualifier("lista")
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private MonetyRepository monety;

    @Override
    public List<Moneta> findAll() {
        return monety.findAll();
    }

    @Override
    public List<Moneta> findLatest3() {
        return monety.findAll().stream().sorted((a, b) -> b.getDataNabycia().compareTo(a.getDataNabycia())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Moneta> findById(Long id) {
        try {
            return Optional.of(monety.readById(id));
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Moneta> create(Moneta moneta) {
        try {
            return Optional.of(monety.create(moneta));
        } catch (MonetaAlreadyExistsException e) {
            try {
                return Optional.of(monety.readById(moneta.getNumerKatalogowy()));
            } catch (NoSuchMonetaException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Moneta> edit(Moneta moneta) {
        try {
            return Optional.of(monety.update(moneta));
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            monety.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMonetaException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Moneta> findAllToSell() {
        return monety.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }
}
