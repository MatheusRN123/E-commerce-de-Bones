package br.unitins.topicos1.bone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
=======
import jakarta.persistence.ManyToOne;

@Entity
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_bone")
=======
    private Pedido pedido;

    @ManyToOne
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    private Bone bone;

    private Integer quantidade;
    private Double preco;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Bone getBone() {
        return bone;
    }
    public void setBone(Bone bone) {
        this.bone = bone;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public Double getSubTotal(){
        return quantidade*preco;
    }
}
