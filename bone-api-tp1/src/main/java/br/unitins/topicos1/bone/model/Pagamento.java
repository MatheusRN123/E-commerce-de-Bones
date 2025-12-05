package br.unitins.topicos1.bone.model;

import java.time.LocalDateTime;

<<<<<<< HEAD
import jakarta.persistence.Column;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
<<<<<<< HEAD
=======
import jakarta.persistence.JoinColumn;
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento {

<<<<<<< HEAD
    @Id
=======
    @Id 
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;
    private LocalDateTime data;
    private String status;

    @OneToOne(mappedBy = "pagamento")
<<<<<<< HEAD
=======
    @JoinColumn(name = "id_pedido")
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    private Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
