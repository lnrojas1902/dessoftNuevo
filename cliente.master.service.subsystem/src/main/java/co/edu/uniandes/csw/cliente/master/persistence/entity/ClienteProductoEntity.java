package co.edu.uniandes.csw.cliente.master.persistence.entity;

import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(ClienteProductoEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ClienteProductoEntity.getProductoListForCliente", query = "SELECT  u FROM ClienteProductoEntity u WHERE u.clienteId=:clienteId"),
    @NamedQuery(name = "ClienteProductoEntity.deleteClienteProducto", query = "DELETE FROM ClienteProductoEntity u WHERE u.productoId=:productoId and  u.clienteId=:clienteId")
})
public class ClienteProductoEntity implements Serializable {

    @Id
    @Column(name = "clienteId")
    private Long clienteId;
    @Id
    @Column(name = "productoId")
    private Long productoId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "clienteId", referencedColumnName = "id")
    @JoinFetch
    private ClienteEntity clienteEntity;

    public ClienteProductoEntity() {
    }

    public ClienteProductoEntity(Long clienteId, Long productoId) {
        this.clienteId = clienteId;
        this.productoId = productoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

}
