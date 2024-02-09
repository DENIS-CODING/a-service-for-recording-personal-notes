import com.example.demo.exceptions.NoteNotFoundException;
import com.example.demo.models.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note addNote(Note note) {
        note.setCreationDate(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Записка с идентификатором " + id + " не найдено"));
    }

    public Note editNote(Long id, Note updatedNote) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            updatedNote.setId(id);
            updatedNote.setCreationDate(existingNote.get().getCreationDate());
            return noteRepository.save(updatedNote);
        } else {
            throw new NoteNotFoundException("Записка с идентификатором " + id + " не найдено");
        }
    }

    public void deleteNoteById(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new NoteNotFoundException("Записка с идентификатором " + id + " не найдено");
        }
    }
}
