package br.com.vr.miniautorizador.infrastructure.repositories;

import br.com.vr.miniautorizador.infrastructure.entities.CartaoEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Integer> {

    CartaoEntity findByNumero(Long numero);

}
