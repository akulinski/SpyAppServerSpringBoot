package com.spyapp.server.core.beans.repositories;

import com.spyapp.server.core.beans.entities.Stalker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface StalkerRepository extends CrudRepository<Stalker,Integer> {

    LinkedList<Stalker> findAllByDataOfJoiningNotNull();

    Optional<Stalker> findByUsername(String name);

    @Query(
            "SELECT s FROM Stalker s WHERE s.username = ?1 and s.password = ?2"
    )
    LinkedList<Stalker> findByUsernameAndPassword(String username,String password);

}
