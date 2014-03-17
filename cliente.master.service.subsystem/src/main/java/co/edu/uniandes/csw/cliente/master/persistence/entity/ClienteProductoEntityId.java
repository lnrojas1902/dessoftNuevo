package co.edu.uniandes.csw.cliente.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ClienteProductoEntityId implements Serializable{

    private Long clienteId;
    private Long productoId;

    @Override
    public int hashCode() {
        return (int) (clienteId + productoId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ClienteProductoEntityId) {
            ClienteProductoEntityId otherId = (ClienteProductoEntityId) object;
            return (otherId.clienteId == this.clienteId) && (otherId.productoId == this.productoId);
        }
        return false;
    }

}
