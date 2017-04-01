import java.util.List;
public interface View {
    
    public void start();
    public void display(Model.Monster monster);
    public void display(List<Model.Monster> monsters);
    public void setData(List<Model.Monster> monsters);
    public void add(Model.Monster monster);
    
}
