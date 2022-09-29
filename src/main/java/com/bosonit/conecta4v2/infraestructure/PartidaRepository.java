package com.bosonit.conecta4v2.infraestructure;

import com.bosonit.conecta4v2.domain.Partida;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends ReactiveSortingRepository<Partida, Integer> {
}
