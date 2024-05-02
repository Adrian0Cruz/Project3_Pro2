package programacion2_pro5;
//@author jesus
public class Child {
    String Municipality;
    //Info Representante
    int Id;
    String Name;
    //Info Niño
    int Age;
    int Record;
    String name;
    float Size;
    float Weight;
    //Punteros
    Child Sig, Ant;
    
    //Constructor
    public Child(String Municipality, int Id, String Name, int Age, int Record,
            String name, float Size, float Weight) {
        this.Municipality = Municipality;
        this.Id = Id;
        this.Name = Name;
        this.Age = Age;
        this.Record = Record;
        this.name = name;
        this.Size = Size;
        this.Weight = Weight;
        this.Ant = this.Sig = null;
    }
}