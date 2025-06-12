
package GestDeCliente;

/**
 *
 * @author salda
 */
import javax.swing.*;
import java.awt.*;

public class GestionClientesHash extends JFrame 
{
    private static final int TAMANO_TABLA=29;
    private Cliente[] tablaLineal = new Cliente[TAMANO_TABLA];
    private NodoLista[] tablaEncadenada = new NodoLista[TAMANO_TABLA];
    private JTextField campoCodigo, campoNombre, campoApellido, campoTelefono, campoCorreo, campoDireccion, campoCP;
    private JTextArea areaResultado;

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(()->new GestionClientesHash().setVisible(true));
    }

    public GestionClientesHash() 
    {
        setTitle("Gestor de Clientes");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        construirInterfaz();
        
        Cliente cliente1 = new Cliente("1", "Lisa", "Morales", "5551234", "linda@gmail.com", "Av. Central 100", "12345");
        Cliente cliente2 = new Cliente("2", "Carlos", "Torres", "5555678", "cartorr@gmail.com", "Calle Norte 200", "54321");
        insertarLineal(cliente1);
        insertarEncadenado(cliente1);
        insertarLineal(cliente2);
        insertarEncadenado(cliente2);
    }

    private void construirInterfaz() 
    {
        JPanel panel=new JPanel(new GridLayout(10, 2));
        campoCodigo=new JTextField();
        campoNombre=new JTextField();
        campoApellido=new JTextField();
        campoTelefono=new JTextField();
        campoCorreo=new JTextField();
        campoDireccion=new JTextField();
        campoCP=new JTextField();

        panel.add(new JLabel("Código:"));
        panel.add(campoCodigo);
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(campoApellido);
        panel.add(new JLabel("Teléfono:"));
        panel.add(campoTelefono);
        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);
        panel.add(new JLabel("Dirección:"));
        panel.add(campoDireccion);
        panel.add(new JLabel("Código Postal:"));
        panel.add(campoCP);

        JButton btnInsertar=new JButton("Insertar Cliente");
        JButton btnBuscar=new JButton("Buscar Cliente");

        panel.add(btnInsertar);
        panel.add(btnBuscar);
        
        JLabel mensajeBusqueda = new JLabel("      <La búsqueda requiere solo del nombre y apellido>");
        mensajeBusqueda.setForeground(Color.RED);
        panel.add(new JLabel());
        panel.add(mensajeBusqueda);

        areaResultado=new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scroll=new JScrollPane(areaResultado);

        add(panel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnInsertar.addActionListener(e->insertarCliente());
        btnBuscar.addActionListener(e->buscarCliente());
    }

    private void insertarCliente() 
    {
        Cliente c=new Cliente
        (
                campoCodigo.getText(),
                campoNombre.getText(),
                campoApellido.getText(),
                campoTelefono.getText(),
                campoCorreo.getText(),
                campoDireccion.getText(),
                campoCP.getText()
        );

        long tiempoInicioLineal=System.nanoTime();
        insertarLineal(c);
        long tiempoFinalLineal=System.nanoTime();
        long tiempoInicioEncadenado=System.nanoTime();
        insertarEncadenado(c);
        long tiempoFinalEncadenado=System.nanoTime();

        areaResultado.append("Cliente insertado.\n");
        areaResultado.append("Tiempo(Lineal): "+(tiempoFinalLineal-tiempoInicioLineal)+" ns\n");
        areaResultado.append("Tiempo(Encadenado): "+(tiempoFinalEncadenado-tiempoInicioEncadenado)+" ns\n\n");
    }

    private void buscarCliente() 
    {
        String nombre=campoNombre.getText();
        String apellido=campoApellido.getText();

        long inicioB1=System.nanoTime();
        Cliente resultado1=buscarLineal(nombre, apellido);
        long finB1=System.nanoTime();
        long inicioB2=System.nanoTime();
        Cliente resultado2=buscarEncadenado(nombre, apellido);
        long finB2=System.nanoTime();

        areaResultado.append("Resultado Búsqueda:\n");
        if (resultado1!=null) 
        {
            areaResultado.append("[Lineal] Cliente: "+resultado1+"\n");
        } 
        else 
        {
            areaResultado.append("[Lineal] Cliente no encontrado\n");
        }

        if (resultado2!=null) 
        {
            areaResultado.append("[Encadenado] Cliente: "+resultado2+"\n");
        } 
        else 
        {
            areaResultado.append("[Encadenado] Cliente no encontrado\n");
        }

        areaResultado.append("Tiempo búsqueda Lineal: "+(finB1-inicioB1)+" ns\n");
        areaResultado.append("Tiempo búsqueda Encadenada: "+(finB2-inicioB2)+" ns\n\n");
    }

    private int calcularHash(String nombre, String apellido) 
    {
        int hash=7;
        String clave=nombre+apellido;
        for (char c:clave.toCharArray()) 
        {
            hash=(hash*31+c)%TAMANO_TABLA;
        }
        return hash;
    }

    private void insertarLineal(Cliente cliente) 
    {
        int posicion=calcularHash(cliente.nombre, cliente.apellido);
        int intentos=0;
        while (tablaLineal[posicion]!=null && intentos<TAMANO_TABLA) 
        {
            posicion=(posicion+1)%TAMANO_TABLA;
            intentos++;
        }
        if (intentos<TAMANO_TABLA) 
        {
            tablaLineal[posicion]=cliente;
        }
    }

    private Cliente buscarLineal(String nombre, String apellido) 
    {
        int posicion=calcularHash(nombre, apellido);
        int intentos=0;
        while (tablaLineal[posicion]!=null && intentos<TAMANO_TABLA) 
        {
            if (tablaLineal[posicion].nombre.equals(nombre) && tablaLineal[posicion].apellido.equals(apellido)) 
            {
                return tablaLineal[posicion];
            }
            posicion=(posicion+1)%TAMANO_TABLA;
            intentos++;
        }
        return null;
    }

    private void insertarEncadenado(Cliente cliente) 
    {
        int pos=calcularHash(cliente.nombre, cliente.apellido);
        tablaEncadenada[pos]=new NodoLista(cliente, tablaEncadenada[pos]);
    }

    private Cliente buscarEncadenado(String nombre, String apellido) 
    {
        int pos=calcularHash(nombre, apellido);
        NodoLista actual=tablaEncadenada[pos];
        while (actual!=null) 
        {
            if (actual.dato.nombre.equals(nombre) && actual.dato.apellido.equals(apellido)) 
            {
                return actual.dato;
            }
            actual=actual.siguiente;
        }
        return null;
    }

    private static class Cliente 
    {
        String codigo, nombre, apellido, telefono, correo, direccion, cp;
        Cliente(String codigo, String nombre, String apellido, String telefono, String correo, String direccion, String cp) 
        {
            this.codigo=codigo;
            this.nombre=nombre;
            this.apellido=apellido;
            this.telefono=telefono;
            this.correo=correo;
            this.direccion=direccion;
            this.cp=cp;
        }

        public String toString() 
        {
            return nombre+" "+apellido+" ("+codigo+")";
        }
    }

    private static class NodoLista 
    {
        Cliente dato;
        NodoLista siguiente;
        NodoLista(Cliente dato, NodoLista siguiente) 
        {
            this.dato=dato;
            this.siguiente=siguiente;
        }
    }
}