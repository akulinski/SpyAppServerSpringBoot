/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.core.beans.repositories;

import com.spyapp.server.core.beans.entities.Stalker;
import com.spyapp.server.core.beans.entities.Victim;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface VictimRepository extends CrudRepository<Victim, Integer> {

    LinkedList<Victim> findVictimByStalker(Stalker stalker);

    Optional<Victim> findVictimByStalkerAndName(Stalker stalker,String name);
}
