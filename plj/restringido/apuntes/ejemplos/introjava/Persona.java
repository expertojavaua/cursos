/**
 * Ejemplo de clase Java
 */
class Persona {
    public String nombre;
    int edad;

    /**
     * Constructor
     */
    public Persona() {
        nombre = "Pepe";
        edad = 33;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public static void main(String[] args) {
        Persona p = new Persona();
        System.out.println("Nombre de persona: " + p.getNombre());
        p.setNombre("Maria");
        System.out.println("Nombre de persona: " + p.getNombre());
        System.out.println("Edad de persona: " + p.getEdad());
    }
}