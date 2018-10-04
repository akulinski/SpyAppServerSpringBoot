/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.core.beans.repositories;

import com.spyapp.server.core.beans.entities.Photo;
import com.spyapp.server.core.beans.entities.Victim;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {

    LinkedList<Photo> findByVictim(Victim victim);
}
