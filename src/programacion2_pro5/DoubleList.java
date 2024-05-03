package programacion2_pro5;
//@author jesus
import java.io.*;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class DoubleList {
    Child Cab;
    Child Info;

    public DoubleList() { Cab = null; }

    public boolean Empty() { return Cab == null; }

    public int GetLength() {
        if ( Empty() )  return 0; 
        else {
            Child A = Cab;
            int Cont = 0;
            while (A != null) {
                Cont++;
                A = A.Sig;
            }
            return Cont;
        }
    }

    public Child ChildCod(int cod) {
        if ( Empty() )  return null; 
        else {
            Child p = Cab;
            while (p != null) {
                if (p.Record == cod || p.Id == cod) return p;
                else p = p.Sig;
            }
            return null;
        }
    }

    public boolean Create( JComboBox Municipality, JTextField Id, JTextField Name,
            JLabel Age, JTextField Record, JTextField name, JTextField Size,
            JTextField Weight ) {
        Child b = null;
        Info = null;
        try {
            do {
                b = ChildCod( Integer.parseInt( Record.getText() ) );
                if ( b != null ) {
                    JOptionPane.showMessageDialog(null,
                            "El código Del Niño ya existe!!"
                            + " Intenta nuevamente.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    Record.setText("");
                    Record.requestFocus();
                }
            } while ( b != null );

            Info = new Child(Municipality.getSelectedItem().toString(),
                    Integer.parseInt(Id.getText()),
                    Name.getText(), Integer.parseInt(Age.getText()),
                    Integer.parseInt(Record.getText()), name.getText(),
                    Float.parseFloat(Size.getText()),
                    Float.parseFloat(Weight.getText()));
            JOptionPane.showMessageDialog(null,
                    "Se Ha Resgistrado Un Nuevo Niño.");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e + ".  No fue posible crear el nodo.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            Info = null;
            Record.grabFocus();
            return false;
        }
    }

    public void AddS(JComboBox Municipality, JTextField Id, JTextField Name,
            JLabel Age, JTextField Record, JTextField name, JTextField Size,
            JTextField Weight) {
        Create(Municipality, Id, Name, Age, Record, name, Size, Weight);
        if (Info != null) {
            if (Cab == null) {
                Cab = Info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado el nodo a la lista, " + " la lista estaba vacía.");
            } else {
                Info.Sig = Cab;
                Cab.Ant = Info;
                Cab = Info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado el nodo al principio " + " de la lista.");
            }
        }
    }

    public Child Lasted() {
        if ( Empty()) return null;
        else {
            Child p = Cab;
            while (p.Sig != null) { p = p.Sig; }
            return p;
        }
    }

    public void AddF(JComboBox Municipality, JTextField Id, JTextField Name,
            JLabel Age, JTextField Record, JTextField name, JTextField Size,
            JTextField Weight) {
        Create( Municipality, Id, Name, Age, Record, name, Size, Weight);
        if (Info != null) {
            if (Cab == null) {
                Cab = Info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado Un Nuevo Niño , " + "No Habia Niños Registrados.");
            } else {
                Child u = Lasted();
                u.Sig = Info;
                Info.Ant = u;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado Un Niño Al Final " + " de la lista.");
            }
        }
    }

    public void AddB( JComboBox Municipality, JTextField Id, JTextField Name,
            JLabel Age, JTextField Record, JTextField name, JTextField Size,
            JTextField Weight, int position ) {
        // Creamos el nuevo nodo a registrar
        Create( Municipality, Id, Name, Age, Record, name, Size, Weight );
        if ( Info != null ) {
            if (Cab == null) {
                Cab = Info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento a la lista. La lista estaba vacía.");
            } else if (position == 0) {
                Info.Sig = Cab;
                Cab.Ant = Info; // Enlace del puntero Ant
                Cab = Info;
                JOptionPane.showMessageDialog(null,
                        "Se ha registrado un nuevo elemento al inicio de la lista.");
            } else {
                Child temp = Cab;
                int count = 0;
                while (count < position - 1 && temp.Sig != null) {
                    temp = temp.Sig;
                    count++;
                }
                if (count == position - 1) {
                    Info.Sig = temp.Sig;
                    if (temp.Sig != null) temp.Sig.Ant = Info; // Enlace del puntero Ant
                    temp.Sig = Info;
                    Info.Ant = temp; // Enlace del puntero Ant
                    JOptionPane.showMessageDialog(null,
                            "Se ha registrado un nuevo elemento en la posición " + position + ".");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "La posición especificada es mayor que la longitud de la lista. "
                            + "Se insertará al final.");
                    Child ultimo = Lasted();
                    if (ultimo != null) {
                        ultimo.Sig = Info;
                        Info.Ant = ultimo; // Enlace del puntero Ant
                    } else Cab = Info; // Si la lista estaba vacía, ahora el nuevo nodo es la cabeza
                }
            }
        }
    }

    public void setRegistrarFilaJTable( DefaultTableModel miModelo, int pFila,
            Child p ) {
        miModelo.setValueAt(p.Municipality, pFila, 0);
        miModelo.setValueAt(p.Id, pFila, 1);
        miModelo.setValueAt(p.Name, pFila, 2);
        miModelo.setValueAt(p.Age, pFila, 3);
        miModelo.setValueAt(p.Record, pFila, 4);
        miModelo.setValueAt(p.name, pFila, 5);
    }

    public void LLenarTabla(JTable tab ) {
        int posFilaT = 0; //Este índice recorre los elementos de la fila Tabla
        Child p = Cab;  //Este nodo me mueve posición x posición en la lista
        DefaultTableModel miModelo = new DefaultTableModel();

        miModelo.addColumn("Municipio");
        miModelo.addColumn("Identificacion");
        miModelo.addColumn("Acudiente");
        miModelo.addColumn("Edad");
        miModelo.addColumn("Registro");
        miModelo.addColumn("Nombre");

        while ( p != null ) {
            miModelo.addRow(new Object[]{"", "", "", "", ""});
            setRegistrarFilaJTable(miModelo, posFilaT, p);
            p = p.Sig;
            posFilaT++;
        }
        tab.setModel(miModelo);
    }

    public void Stop(JTextField Id, JTextField Name) {
        Child A = Cab;
        int Cont = 0;
        int Comt = 0;
        while (A != null) {
            if ( A.Id == Integer.parseInt( Id.getText() ) ) Cont++;
            if ( A.Name.equals( Name.getText() ) ) Comt++;
            A = A.Sig;
        }
        boolean condicionDeError = false;
        if (Cont >= 2) {
            JOptionPane.showMessageDialog(null,
                    "Ese Id Ya Se Ha Registrado Dos Veces", "ERROR", JOptionPane.ERROR_MESSAGE);
            condicionDeError = true;
        }
        if (Comt >= 2) {
            JOptionPane.showMessageDialog(null,
                    "Ese Nombre Ya Se Ha Registrado Dos Veces");
            condicionDeError = true;
        }
        if ( condicionDeError ) throw new RuntimeException("Error: Condición no válida");
    }

    public void Suka(JComboBox Name, JComboBox Id) {
        Child B = Cab;
        int Cont = 0;
        int Comt = 0;
        int Index = -1;
        int index = -1;
        while (B != null) {
            if (B.Id == Integer.parseInt(Id.getSelectedItem().toString())) {
                Cont++;
                Index = Id.getSelectedIndex();
            }
            if (B.Name.equals(Name.getSelectedItem().toString())) {
                Comt++;
                index = Name.getSelectedIndex();
            }
            B = B.Sig;
        }

        if (Cont >= 2) Id.removeItemAt(Index);
        if (Comt >= 2) Name.removeItemAt(index);
    }
    
    public void Deleted(int cod){
        if( Empty() )
            JOptionPane.showMessageDialog(null, 
                "La listae Es vacía!!");
        else{
            Child b = ChildCod( cod );
            if( b == null)
                JOptionPane.showMessageDialog(null, 
                        "El código a eliminar no existe!!");
            else{
                if( b == Cab && Cab.Sig == null ){
                    Cab = null;
                    JOptionPane.showMessageDialog(null, 
                        "Elemento eliminado de la cabecera!! "
                        + "La lista quedó vacía.");
                } else if( b == Cab && Cab.Sig != null ){
                    Cab = Cab.Sig;
                    b = null;
                    JOptionPane.showMessageDialog(null, 
                        "Elemento eliminado de la cabecera!! "
                        + "La lista tiene elementos.");
                } else if( b.Sig == null ){
                    b.Ant.Sig = null;
                    b.Ant = null;
                    b = null;
                    JOptionPane.showMessageDialog(null, 
                        "Elemento eliminado al final de la lista!!");
                } else{
                    b.Ant.Sig = b.Sig;
                    b.Sig.Ant = b.Ant;
                    b.Sig = b.Ant = null;
                    b = null;
                    JOptionPane.showMessageDialog(null, 
                        "Elemento eliminado entre dos nodos de la lista!!");
                }
            }
        }
    }
    
    public void Peso( String Municipality ){
        Child B = Cab;
        int Con = 0;
        while (B != null){
            if( B.Age <= 3 && B.Age >= 2 && Municipality.equals( B.Municipality ) && B.Weight < 15){
                Con++;
            }
            B = B.Sig;
        }
        JOptionPane.showMessageDialog(null,
                "Los Niños Con Bajo Talla En " + Municipality
                +"\n\nSon: " + Con);
    }
    
    public void Talla(String Municipality){
        Child A = Cab;
        int Cont = 0;
        while (A != null){
            if( A.Age <= 6 && A.Age >= 4 && Municipality.equals( A.Municipality ) && A.Size < 100){
                Cont++;
            }
            A = A.Sig;
        }
        JOptionPane.showMessageDialog(null,
                "Los Niños Con Bajo Talla En " + Municipality
                +"\n\nSon: " + Cont);
    }
    
    public void Poto( String Municipality, JTable Tab ){
        int posFilaT = 0;
        Child p = Cab;
        DefaultTableModel miModelo = new DefaultTableModel();
        int Cout = 0;
        miModelo.addColumn("Municipio");
        miModelo.addColumn("Identificacion");
        miModelo.addColumn("Acudiente");
        miModelo.addColumn("Edad");
        miModelo.addColumn("Registro");
        miModelo.addColumn("Nombre");

        while ( p != null ) {
            miModelo.addRow(new Object[]{"", "", "", "", ""});
            if( p.Municipality.equals(Municipality) ){
                setRegistrarFilaJTable(miModelo, posFilaT, p);
                Cout++;
            }
            p = p.Sig;
            posFilaT++;
        }
        Tab.setModel(miModelo);
        JOptionPane.showMessageDialog(Tab,
                "La Cantidad De Niños Registrados En " + Municipality + "Es : " + Cout);
    }
    
    public void Txt(){
        JFileChooser Fc = new JFileChooser();
        Fc.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator + "Downloads\\Programacion 2"));
        Fc.setDialogTitle("Donde Desea Guardar El Archivo");
        Fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int Seleccion = Fc.showOpenDialog(null);
        if ( Seleccion == JFileChooser.APPROVE_OPTION ) {
            File selectedFolder = Fc.getSelectedFile();
            String fileName = selectedFolder + "\\Informe.txt";
            String encoding = "UTF-8";
            Child D = Cab;
            try {
                PrintWriter writer = new PrintWriter(fileName, encoding);
                if( Empty() ) writer.println("No Hay Nada En La Lista");
                while( D != null){
                    writer.println("Municipio: " + D.Municipality
                                    +"\nInformacion Del Acudiente:"
                                    +"\nIdentificacion: " + D.Id
                                    +"\nNombre: " + D.Name
                                    +"\nInformacion Del Niño: "
                                    +"\nEdad: " + D.Age
                                    +"\nRegistro: " + D.Record
                                    +"\nNombre: " + D.name
                                    +"\nTalla: " + D.Size
                                    +"\nPeso: " + D.Weight + "\n\n//////\n\n");
                    D = D.Sig;
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Ocurrió un error.");
                e.printStackTrace();
            }
        }
    }
    
    public void Neder(Child E, int Res){
        JOptionPane.showMessageDialog(null,
                            "La Informacion Del Acudiente Con El Codigo: " + Res + " Es "
                            +"\nMunicipio: " + E.Municipality
                            +"\nInformacion Del Acudiente:"
                            +"\nIdentificacion: " + E.Id
                            +"\nNombre: " + E.Name
                            +"\nInformacion Del Niño: "
                            +"\nEdad: " + E.Age
                            +"\nRegistro: " + E.Record
                            +"\nNombre: " + E.name
                            +"\nTalla: " + E.Size
                            +"\nPeso: " + E.Weight);
    }
    public void SearchC(int Res){
        Child E = Cab;
        if (E == null) JOptionPane.showMessageDialog(null,
                "La Lista Esta Vacia", "Info", JOptionPane.INFORMATION_MESSAGE);
        while ( E != null ){
            if( Res == E.Record || Res == E.Id){
                if( Res == E.Record ){
                    JOptionPane.showMessageDialog(null,
                            "La Informacion Del Niño Con El Codigo: " + Res + " Es "
                            +"\nMunicipio: " + E.Municipality
                            +"\nInformacion Del Acudiente:"
                            +"\nIdentificacion: " + E.Id
                            +"\nNombre: " + E.Name
                            +"\nInformacion Del Niño: "
                            +"\nEdad: " + E.Age
                            +"\nRegistro: " + E.Record
                            +"\nNombre: " + E.name
                            +"\nTalla: " + E.Size
                            +"\nPeso: " + E.Weight);
                    break;
                } else if( Res == E.Id ){
                    Neder(E, Res);
                    Child U = Lasted();
                    while( U != Cab){
                        if( Res == E.Id ){
                            Neder(U, Res);
                            break;
                        }
                        U = U.Ant;
                    }
                    break;
                } else{
                    JOptionPane.showMessageDialog(null,
                            "No Se Encontró Un Codigo Que Coincida Con El Buscado");
                }
            }         
            E = E.Sig;
        }
    }
}