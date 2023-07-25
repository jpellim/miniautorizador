package br.com.vr.miniautorizador.infrastructure.repositories;

import br.com.vr.miniautorizador.infrastructure.entities.CartaoEntity;
import br.com.vr.miniautorizador.infrastructure.entities.TransacaoEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {

}
