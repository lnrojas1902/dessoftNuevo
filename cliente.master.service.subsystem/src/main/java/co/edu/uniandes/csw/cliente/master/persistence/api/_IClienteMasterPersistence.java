package co.edu.uniandes.csw.cliente.master.persistence.api;

import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteCarritoDeComprasEntity;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import java.util.List;

public interface _IClienteMasterPersistence {

    public ClienteFacturaEntity createClienteFactura(ClienteFacturaEntity entity);

    public void deleteClienteFactura(Long clienteId, Long facturaId);
    
    public List<FacturaDTO> getFacturaListForCliente(Long clienteId);
    public ClienteCarritoDeComprasEntity createClienteCarritoDeCompras(ClienteCarritoDeComprasEntity entity);

    public void deleteClienteCarritoDeCompras(Long clienteId, Long carritoDeComprasId);
    
    public List<CarritoDeComprasDTO> getCarritoDeComprasListForCliente(Long clienteId);
    public ClienteProductoEntity createClienteProducto(ClienteProductoEntity entity);

    public void deleteClienteProducto(Long clienteId, Long productoId);
    
    public List<ProductoDTO> getProductoListForCliente(Long clienteId);
    
    public ClienteMasterDTO getCliente(Long clienteId);

}
