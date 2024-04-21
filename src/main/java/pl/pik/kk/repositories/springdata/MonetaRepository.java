package pl.pik.kk.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.pik.kk.entities.Moneta;


@Repository
public interface MonetaRepository 
         extends JpaRepository<Moneta, Long>{
    public Moneta findByNumerKatalogowy(Long id);

    public void deleteByNumerKatalogowy(Long id);
}
