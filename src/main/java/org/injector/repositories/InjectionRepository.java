package org.injector.repositories;

import org.injector.entities.Injection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InjectionRepository extends JpaRepository<Injection, UUID> {
}
