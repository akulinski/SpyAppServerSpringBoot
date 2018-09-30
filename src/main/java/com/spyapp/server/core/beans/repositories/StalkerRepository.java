package com.spyapp.server.core.beans.repositories;

import com.spyapp.server.core.beans.entities.Stalker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface StalkerRepository extends CrudRepository<Stalker,Integer> {

    LinkedList<Stalker> findAllByDataOfJoiningNotNull();
}
