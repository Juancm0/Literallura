package com.example.Literallura;

import com.example.Literallura.service.MenuService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {

    private final MenuService menuService;

    public ConsoleApp(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Elija una opción:");
            System.out.println("1.- Buscar libro por título");
            System.out.println("2.- Listar libros registrados");
            System.out.println("3.- Listar autores registrados");
            System.out.println("4.- Listar autores vivos en un determinado año");
            System.out.println("5.- Listar libros por idioma");
            System.out.println("6.- Salir");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    menuService.buscarLibroPorTitulo(titulo);
                    break;
                case 2:
                    menuService.listarLibrosRegistrados();
                    break;
                case 3:
                    menuService.listarAutoresRegistrados();
                    break;
                case 4:
                    System.out.print("Ingrese el año: ");
                    int anio = scanner.nextInt();
                    menuService.listarAutoresVivosEnAnio(anio);
                    break;
                case 5:
                    System.out.print("Ingrese el idioma: ");
                    String idioma = scanner.nextLine();
                    menuService.listarLibrosPorIdioma(idioma);
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
