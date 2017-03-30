import java.util.ArrayList;
import java.util.List;


public class StorageServiceImpl implements StorageService {
    
    static int[] cPitches = {-1, -1, 60, 62, 64, 65, 67, 69, 71, 72, 74, 76};
    static final int MCL = 8; // # 1/8th notes in melody chromosome
    
    public List<Model.Monster> loadMonsters(){
        
        Model.Note[] notes1 = new Model.Note[MCL];
        Model.Note[] notes2 = new Model.Note[MCL];
        
        for (int i = 0; i < MCL; i++) {
            notes1[i] = new Model.Note(cPitches[(int)(Math.random() * cPitches.length)]);
            notes2[i] = new Model.Note(cPitches[(int)(Math.random() * cPitches.length)]);
        }
        
        Model.Monster monster1 = new Model.Monster(notes1, notes2); //fixme make less direct
        
        List<Model.Monster> monsters = new ArrayList<Model.Monster>();
        
        monsters.add(monster1);
        
        return monsters;
        
    }
}