package edu.unimagdalena.coworker.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domain.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Optional<Tag> findByName(String name);
    public List<Tag> findByNameIn(List<String> names);
}
