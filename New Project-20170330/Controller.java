import java.util.List;

public class Controller {
    
    private static final View view = new ConsoleView();
    private static final StorageService storageService = new RandomStorage();

     public static void main(String[] args){
         // get existing models
         // initialize view with them
         
         testConcepts();
     }
     
     public static void end() {
         System.out.println("Type Ctrl-C to quit.");
     }
     
     public static void breed(Model.Monster parent1, Model.Monster parent2) {
         Monster child = Model.breed(parent1, parent2);
         view.add(child);
     }
     
     
     private static void testConcepts() {
         List<Model.Monster> monsters = storageService.loadMonsters();
         view.setData(monsters);
         view.start();
     }

}