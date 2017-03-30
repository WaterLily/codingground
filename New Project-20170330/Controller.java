import java.util.List;

public class Controller {
    
    private static final View view = new ConsoleView();
    private static final StorageService storageService = new StorageServiceImpl();

     public static void main(String[] args){
         // get existing models
         // initialize view with them
         
         testConcepts();
     }
     
     
     private static void testConcepts() {
         
       view.start();
         
         List<Model.Monster> monsters = storageService.loadMonsters();
         
         for (Model.Monster monster : monsters) {
             view.display(monster);
         }
         
     }

}