package supermercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Supermercado {

    public static void main(String[] args) {

        try {
            //Hacemos la conexion 
            Connection cX = DriverManager.getConnection("jdbc:mysql://localhost:3307/supermercado_arpr", "root", "");
            Statement sT = cX.createStatement();

            // Obtener la lista de clientes de la Base de Datos e instanciarlos
            System.out.println("*********************************\nLISTADO DE CLIENTES:");

            String query = ("SELECT * FROM clientes");
            ResultSet resultset = sT.executeQuery(query);

            List<Cliente> listaClientes = new ArrayList<Cliente>();

            System.out.println("CLIENTE        CUIL");
            while (resultset.next()) {
                Cliente cliente = new Cliente(resultset.getString(1), resultset.getString(2), resultset.getString(3),
                        resultset.getString(4));
                listaClientes.add(cliente);
                System.out.println(resultset.getString(1) + " " + resultset.getString(2));
            }

            // Obtener la lista de productos disponibles en la Base de Datos e instanciarlos
            System.out.println("*********************************\nLISTADO DE PRODUCTOS DISPONIBLES:");

            query = "SELECT * FROM item";
            resultset = sT.executeQuery(query);

            List<Item> listaItems = new ArrayList<Item>();

            System.out.println("ID PRECIO PRODUCTO");
            while (resultset.next()) {
                Item item = new Item(resultset.getInt(1), resultset.getDouble(2), resultset.getString(3));
                listaItems.add(item);
                System.out.println(resultset.getInt(1) + "  " + resultset.getDouble(2) + "  " + resultset.getString(3));
            }

            // Instanciar un carrito
            Carrito carrito = new Carrito();

            // Elegir el cliente que realizará la compra
            boolean encontrado = false;
            Scanner scanner = new Scanner(System.in);

            // Chequear si existe un cliente con ese cuil y si es así agregarlo al carrito
            while (!encontrado) {
                System.out
                        .println(
                                "*********************************\nIngrese el CUIL del Cliente\n que realizará la compra:");
                String cuil = scanner.nextLine();

                for (int i = 0; i < listaClientes.size(); i++) {
                    if (listaClientes.get(i).getCuil().equals(cuil)) {
                        System.out.println("El cliente seleccionado es:\n" + listaClientes.get(i).getNombres());
                        carrito.setCliente(listaClientes.get(i));
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("No existe ningún cliente con ese CUIL");
                }
            }

            // Armar carrito de compras
            System.out.println("*********************************\nARMANDO CARRITO DE COMPRAS......");

            List<ItemCarrito> listaItemCarrito = new ArrayList<ItemCarrito>();
            boolean entradaItem = true;

            while (entradaItem) {
                System.out.println("Ingrese el ID del producto:");
                int idItem = scanner.nextInt();
                System.out.println("Ingrese la cantidad:");
                int cantidad = scanner.nextInt();
                encontrado = false;
                for (int i = 0; i < listaItems.size(); i++) {
                    if (idItem == listaItems.get(i).getId()) {
                        ItemCarrito itemCarrito = new ItemCarrito(listaItems.get(i), cantidad);
                        listaItemCarrito.add(itemCarrito);
                        carrito.setMontoTotal(carrito.getMontoTotal() + listaItems.get(i).getPrecio() * cantidad);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("El ID seleccionado no se encuentra en el listado");
                }
                System.out.println("¿Desea cargar más productos? SI / NO");
                scanner.nextLine();
                if (scanner.nextLine().equals("SI")) {
                    entradaItem = true;
                } else {
                    entradaItem = false;
                }
            }
            carrito.setItems(listaItemCarrito);

            // Definir qué descuento aplica
            Descuento dFijo = new DescuentoFijo();
            Descuento dPorc = new DescuentoProp();

            if (carrito.getMontoTotal() > 0) {

                LocalDate diaDesc = LocalDate.now();
                int dia = diaDesc.getDayOfMonth();

                if (dia % 2 == 0) {
                    dFijo.setMontoDesc(300);
                    double montoDF = dFijo.getMontoDesc();
                    if (montoDF > 0) {
                        System.out.println("Descuento fijo aplicado");
                        carrito.setMontoTotalconDescuento(dFijo.montoFinal(carrito.getMontoTotal()));
                    } else {
                        System.out.println("El descuento no se puede aplicar a este carrito");
                    }
                } else {
                    dPorc.setMontoPorc(30);
                    double montoDP = dPorc.getMontoDesc();
                    if (montoDP > 0) {
                        System.out.println("Descuento con porcentaje aplicado");
                        carrito.setMontoTotalconDescuento(dPorc.montoFinal(carrito.getMontoTotal()));
                    } else {
                        System.out.println("El descuento no se puede aplicar a este carrito");
                    }
                }
            } else {
                System.out.println("No se puede aplicar descuentos en montos iguales a 0");
            }

            // Imprimir resumen de la compra
            System.out.println("*********************************\nRESUMEN DE LA COMPRA:");
            System.out.println("PRODUCTO        PRECIO  CANTIDAD");

            System.out.println(listaItemCarrito.toString());
            System.out.println(carrito.toString());

            // Insertar carrito en la Base de Datos
            query = "INSERT INTO carrito (idcarrito,cuil,montototal,montocondesc) VALUES (idcarrito,?,?,?)";
            PreparedStatement preparedSt = cX.prepareStatement(query);

            preparedSt.setObject(1, carrito.getCliente().getCuil());
            preparedSt.setDouble(2, carrito.getMontoTotal());
            preparedSt.setDouble(3, carrito.getMontoTotalconDescuento());

            preparedSt.executeUpdate();

            System.out.println("Carrito creado con éxito");

            // Obtener el último carrito creado
            query = ("SELECT idcarrito FROM carrito ORDER BY idcarrito DESC LIMIT 1");
            resultset = sT.executeQuery(query);
            if (resultset.next()) {
                carrito.setId(resultset.getInt(1));
            }

            // Insertar itemCarrito en la Base de Datos
            query = "INSERT INTO itemcarrito (idcarrito,iditem,cantidad) VALUES (?,?,?)";
            preparedSt = cX.prepareStatement(query);

            for (int i = 0; i < listaItemCarrito.size(); i++) {
                preparedSt.setInt(1, carrito.getId());
                preparedSt.setInt(2, listaItemCarrito.get(i).getItem().getId());
                preparedSt.setInt(3, listaItemCarrito.get(i).getCantidad());
                preparedSt.executeUpdate();
            }

            cX.close();
            scanner.close();

        } catch (Exception obj) {
            System.out.println("Error en la conexion");
            System.out.println(obj.fillInStackTrace());
        }
    }
}
