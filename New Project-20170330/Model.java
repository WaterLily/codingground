import java.util.ArrayList;
import java.util.List;

public class Model {
    
    private static class MelodyBase {
        final Note note;
        private MelodyBase next;
        
        MelodyBase(Note note) {
            this.note = note;
        }
        
        MelodyBase setNext(MelodyBase next) {
            this.next = next;
            return this;
        }
        
        MelodyBase next() {
            return next;
        }
        
        public String toString() {
            String result = note.toString();
            if (next != null) {
                result += " " + next.toString();
            }
            return result;
        }
    }
    
    public static class Note {
        final int pitch; // 60 = Middle C
        
        Note(int pitch) {
            this.pitch = pitch;
        }
        
        public String toString() {
            if (pitch < 0) return "--";
            
            int octave = (pitch / 12) - 1;
            int noteIndex = (pitch % 12);
            String note = noteString[noteIndex];
            return note + octave;
        }
    }
    private static final String[] noteString = new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };

    
    public static class Gamete {
        public final MelodyBase melodyChromosome;
        
        private Gamete(MelodyBase melodyChromosome) {
            this.melodyChromosome = melodyChromosome;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder {
            private MelodyBase melodyChromosome;
            private MelodyBase last;
            public Builder setMelody(MelodyBase melodyBase){ //fixme allow building melodybases?
                this.melodyChromosome = melodyBase;
                return this;
            }
            public Gamete build() {
                return new Gamete(melodyChromosome);
            }
            private Builder() {}
        }
        
    }
    
    private static MelodyBase makeMelodyBases(Note[] notes) {
        MelodyBase last = null;
        for (int i = notes.length - 1; i >= 0; i--) {
            MelodyBase base = new MelodyBase(notes[i]);
            base.setNext(last);
            last = base;
        }
        return last;
    }
    
    public static class Monster {
        private MelodyBase melodyChromosome1;
        private MelodyBase melodyChromosome2;
        
        public Monster(Gamete gamete1, Gamete gamete2) {
            this.melodyChromosome1 = gamete1.melodyChromosome; //fixme
            this.melodyChromosome2 = gamete2.melodyChromosome;
        }
        
        public Monster(Note[] notes1, Note[] notes2) { //fixme
            this.melodyChromosome1 = makeMelodyBases(notes1);
            this.melodyChromosome2 = makeMelodyBases(notes2);
        }
        
        public List<Note> getMelody() {
            // apply all genes
            List<Note> notes = new ArrayList<>();
            for (MelodyBase base = melodyChromosome1; base != null; base = base.next()) {
                notes.add(base.note);
            }
            for (MelodyBase base = melodyChromosome2; base != null; base = base.next()) {
                notes.add(base.note);
            }
            return notes;
        }
        
        public Gamete makeGamete() {
            Gamete.Builder g = Gamete.builder();
            boolean r = Math.random() < 0.5;
            MelodyBase first = r ? melodyChromosome1 : melodyChromosome2;
            MelodyBase second = r ? melodyChromosome2 : melodyChromosome1;
            MelodyBase newBase = new MelodyBase(first.note);
            addNotes(newBase, first.next(), second.next());
            return g.setMelody(newBase).build();
        }
        
        private void addNotes(MelodyBase base, MelodyBase current, MelodyBase other) {
            if (current == null) {
                return;
            } else {
                base.setNext(new MelodyBase(current.note));
            }
            boolean cross = Math.random() < getCrossoverChance(current, other);
            MelodyBase first = cross ? other : current;
            MelodyBase second = cross ? current : other;
            addNotes(base.next(), first.next(), second.next());
        }
        
        private double getCrossoverChance(MelodyBase one, MelodyBase two) {
            return 0.25;
        }
        
        
    }
    
    public static Monster breed(Monster parent1, Monster parent2) {
        Gamete one = parent1.makeGamete();
        Gamete two = parent2.makeGamete();
        return new Monster(one, two);
    }   
    
}
