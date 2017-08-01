package com.org.readability.repo;

import org.springframework.data.repository.CrudRepository;

import com.org.readability.model.ComplexWord;

public interface ComplexWordRepo extends CrudRepository<ComplexWord, Long> {
    ComplexWord findByWord(String word);
}
