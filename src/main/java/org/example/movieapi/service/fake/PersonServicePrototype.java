package org.example.movieapi.service.fake;

import org.example.movieapi.dto.PersonCreate;
import org.example.movieapi.dto.PersonSimple;
import org.example.movieapi.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServicePrototype implements PersonService {
    @Override
    public Optional<PersonSimple> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<PersonSimple> getAll() {
        return null;
    }

    @Override
    public PersonSimple add(PersonCreate person) {
        return null;
    }

    @Override
    public Optional<PersonSimple> update(PersonCreate person, int id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
