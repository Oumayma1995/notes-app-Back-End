
package com.example.repository;

import com.example.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	// Filter notes by title (case-insensitive contains)
	List<Note> findByTitleContainingIgnoreCase(String title);
	List<Note> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}
