import java.util.ArrayList;
import java.util.List;


public class RandomStorage implements StorageService {
    
    static int[] cPitches = {-1, -1, 60, 62, 64, 65, 67, 69, 71, 72, 74, 76};
    static final int MCL = 8; // # 1/8th notes in melody chromosome
    static final int NUM_MONSTERS = 4;
    
    public List<Model.Monster> loadMonsters(){
        List<Model.Monster> monsters = new ArrayList<Model.Monster>();
        
        for (int j = 0; j < NUM_MONSTERS; j++) {
        
            Model.Note[] notes1 = new Model.Note[MCL];
            Model.Note[] notes2 = new Model.Note[MCL];
            
            for (int i = 0; i < MCL; i++) {
                notes1[i] = new Model.Note(cPitches[(int)(Math.random() * cPitches.length)]);
                notes2[i] = new Model.Note(cPitches[(int)(Math.random() * cPitches.length)]);
            }
            
            Model.Monster monster1 = new Model.Monster(notes1, notes2); //fixme make less direct
            
            monsters.add(monster1);
        }
        
        return monsters;
        
    }
}
