package dados.entidade;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private Integer ano;
    private String genero;
    private String autor;
    private Integer volume;
    private String editora;
    private Exemplar exemplar;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    /* public Integer obterDisponivel() {
        for (Exemplar e : getExemplar()) {
            if (e.getDisponivel()) {
                return e.getNumExemplar();
            }
        }
        return 0;
    }

  

    public void setExemplares(ArrayList<Exemplar> exemplares) {
        this.exemplares = exemplares;
        if (exemplares.isEmpty()) {
            System.out.println("Lista vazia");
        }
    }
     */
    @Override

    public String toString() {

        return getTitulo();
    }

}
