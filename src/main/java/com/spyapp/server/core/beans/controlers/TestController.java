package com.spyapp.server.core.beans.controlers;

import com.spyapp.server.core.beans.entities.Stalker;
import com.spyapp.server.core.beans.repositories.StalkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;

@Controller
@RequestMapping("/testserver")
public class TestController {

    @Autowired
    StalkerRepository stalkerRepository;

    @GetMapping("/test")
    public @ResponseBody LinkedList<Stalker> getStalkers() {

        Iterable<Stalker> stalkers = stalkerRepository.findAll();

        LinkedList<Stalker> linkedList = new LinkedList<>();

        stalkers.forEach(linkedList::add);

        System.out.println(linkedList.size());
        return linkedList;
    }
}
