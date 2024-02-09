package com.example.demo.repository;

import com.example.demo.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findById(Long id);
        /* Метод поиска записи по заданному идентификатору.
        Возвращает объект типа Optional,
        который содержит найденную запись или пустое значение,
        если запись не была найдена. */

    List<Note> findByTitleContainingIgnoreCase
            (String keywordTitle);
        /* Метод поиска записей,
        у которых заголовок содержит указанное ключевое слово
        (без учёта регистра). Возвращает список найденных записей. */

    List<Note> findByContentContainingIgnoreCase
            (String keywordContent);
        /* Метод поиска записей,
        у которых содержимое содержит указанное ключевое слово
        (без учёта регистра). Возвращает список найденных записей. */

    List<Note> findByTitleContainingIgnoreCaseAndContentContainingIgnoreCase
            (String keywordTitle, String keywordContent);
        /* Метод поиска записей,
        у которых заголовок и содержимое содержат указанные ключевые слова
        (без учёта регистра). Возвращает список найденных записей. */
}

