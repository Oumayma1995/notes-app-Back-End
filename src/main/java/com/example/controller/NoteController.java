package com.example.controller;
import com.example.models.Note;
import com.example.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notes")

public class NoteController {
	
	@Autowired
	private NoteRepository noteRepository;

	//filter with title or content
	@GetMapping("/search")
	public List<Note> searchNotes(@RequestParam("term") String term) {
		return noteRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(term, term);
	}

	// GET /notes → return all notes
	@GetMapping("")
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	// GET /notes/{id} → return a note by id (method name: getByIdNote)
	@GetMapping("/{id}")
	public Note getByIdNote(@PathVariable Long id) {
		return noteRepository.findById(id).orElse(null);
	}

	// POST /notes → create a new note from request body
	@PostMapping("")
	public Note createNote(@RequestBody Note note) {
		return noteRepository.save(note);
	}

	// DELETE /notes/{id} → delete a note by id
	@DeleteMapping("/{id}")
	public void deleteNote(@PathVariable Long id) {
		noteRepository.deleteById(id);
	}

	// PUT /notes/{id} → update a note by id
	@PutMapping("/{id}")
	public Note updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
		return noteRepository.findById(id)
			.map(note -> {
				note.setTitle(updatedNote.getTitle());
				note.setContent(updatedNote.getContent());
				return noteRepository.save(note);
			})
			.orElse(null);
	}
}
