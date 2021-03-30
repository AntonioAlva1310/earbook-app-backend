package com.marco.apps.repository;

import com.marco.apps.models.entity.AudioBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AudioBookRepository extends JpaRepository<AudioBook, Long> {


}