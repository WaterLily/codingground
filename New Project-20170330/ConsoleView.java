

public class ConsoleView implements View {
    
    
    public void start() {
        outl("starting program.");
    }
    
    public void display(Model.Monster monster) {
        
        for (Model.Note note : monster.getMelody()) {
            out(note.toString() + " ");
        }
        
        outl("");
        
    }
    
    
    private static void out(String output) {
        System.out.print(output);
    }
    private static void outl(String output) {
        System.out.println(output);
    }
}