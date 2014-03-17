package co.edu.uniandes.csw.cliente.master.persistence.entity;

import co.edu.uniandes.csw.carritodecompras.persistence.entity.CarritoDeComprasEntity;
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
@IdClass(ClienteCarritoDeComprasEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ClienteCarritoDeComprasEntity.getCarritoDeComprasListForCliente", query = "SELECT  u FROM ClienteCarritoDeComprasEntity u WHERE u.clienteId=:clienteId"),
    @NamedQuery(name = "ClienteCarritoDeComprasEntity.deleteClienteCarritoDeCompras", query = "DELETE FROM ClienteCarritoDeComprasEntity u WHERE u.carritoDeComprasId=:carritoDeComprasId and  u.clienteId=:clienteId")
})
public class ClienteCarritoDeComprasEntity implements Serializable {

    @Id
    @Column(name = "clienteId")
    private Long clienteId;
    @Id
    @Column(name = "carritoDeComprasId")
    private Long carritoDeComprasId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "carritoDeComprasId", referencedColumnName = "id")
    @JoinFetch
    private CarritoDeComprasEntity carritoDeComprasEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "clienteId", referencedColumnName = "id")
    @JoinFetch
    private ClienteEntity clienteEntity;

    public ClienteCarritoDeComprasEntity() {
    }

    public ClienteCarritoDeComprasEntity(Long clienteId, Long carritoDeComprasId) {
        this.clienteId = clienteId;
        this.carritoDeComprasId = carritoDeComprasId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCarritoDeComprasId() {
        return carritoDeComprasId;
    }

    public void setCarritoDeComprasId(Long carritoDeComprasId) {
        this.carritoDeComprasId = carritoDeComprasId;
    }

    public CarritoDeComprasEntity getCarritoDeComprasEntity() {
        return carritoDeComprasEntity;
    }

    public void setCarritoDeComprasEntity(CarritoDeComprasEntity carritoDeComprasEntity) {
        this.carritoDeComprasEntity = carritoDeComprasEntity;
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

}
