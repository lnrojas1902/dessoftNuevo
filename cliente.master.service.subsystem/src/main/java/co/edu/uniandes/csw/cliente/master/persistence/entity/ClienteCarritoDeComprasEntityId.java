package co.edu.uniandes.csw.cliente.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ClienteCarritoDeComprasEntityId implements Serializable{

    private Long clienteId;
    private Long carritoDeComprasId;

    @Override
    public int hashCode() {
        return (int) (clienteId + carritoDeComprasId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ClienteCarritoDeComprasEntityId) {
            ClienteCarritoDeComprasEntityId otherId = (ClienteCarritoDeComprasEntityId) object;
            return (otherId.clienteId == this.clienteId) && (otherId.carritoDeComprasId == this.carritoDeComprasId);
        }
        return false;
    }

}
