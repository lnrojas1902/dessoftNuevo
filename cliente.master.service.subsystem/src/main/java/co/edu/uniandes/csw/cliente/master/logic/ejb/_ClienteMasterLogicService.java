package co.edu.uniandes.csw.cliente.master.logic.ejb;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.persistence.api.ICarritoDeComprasPersistence;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.api._IClienteMasterLogicService;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.master.persistence.api.IClienteMasterPersistence;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteCarritoDeComprasEntity;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteProductoEntity;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import javax.inject.Inject;

public abstract class _ClienteMasterLogicService implements _IClienteMasterLogicService {

    @Inject
    protected IClientePersistence clientePersistance;
    @Inject
    protected IClienteMasterPersistence clienteMasterPersistance;
    @Inject
    protected IFacturaPersistence facturaPersistance;
    @Inject
    protected ICarritoDeComprasPersistence carritoDeComprasPersistance;
    @Inject
    protected IProductoPersistence productoPersistance;

    public ClienteMasterDTO createMasterCliente(ClienteMasterDTO cliente) {
        ClienteDTO persistedClienteDTO = clientePersistance.createCliente(cliente.getClienteEntity());
        if (cliente.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                ClienteFacturaEntity clienteFacturaEntity = new ClienteFacturaEntity(persistedClienteDTO.getId(), persistedFacturaDTO.getId());
                clienteMasterPersistance.createClienteFactura(clienteFacturaEntity);
            }
        }
        if (cliente.getCreateCarritoDeCompras() != null) {
            for (CarritoDeComprasDTO carritoDeComprasDTO : cliente.getCreateCarritoDeCompras()) {
                CarritoDeComprasDTO persistedCarritoDeComprasDTO = carritoDeComprasPersistance.createCarritoDeCompras(carritoDeComprasDTO);
                ClienteCarritoDeComprasEntity clienteCarritoDeComprasEntity = new ClienteCarritoDeComprasEntity(persistedClienteDTO.getId(), persistedCarritoDeComprasDTO.getId());
                clienteMasterPersistance.createClienteCarritoDeCompras(clienteCarritoDeComprasEntity);
            }
        }
        if (cliente.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : cliente.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                ClienteProductoEntity clienteProductoEntity = new ClienteProductoEntity(persistedClienteDTO.getId(), persistedProductoDTO.getId());
                clienteMasterPersistance.createClienteProducto(clienteProductoEntity);
            }
        }
        return cliente;
    }

    public ClienteMasterDTO getMasterCliente(Long id) {
        return clienteMasterPersistance.getCliente(id);
    }

    public void deleteMasterCliente(Long id) {
        clientePersistance.deleteCliente(id);
    }

    public void updateMasterCliente(ClienteMasterDTO cliente) {
        clientePersistance.updateCliente(cliente.getClienteEntity());

        //---- FOR RELATIONSHIP
        // persist new factura
        if (cliente.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                ClienteFacturaEntity clienteFacturaEntity = new ClienteFacturaEntity(cliente.getClienteEntity().getId(), persistedFacturaDTO.getId());
                clienteMasterPersistance.createClienteFactura(clienteFacturaEntity);
            }
        }
        // update factura
        if (cliente.getUpdateFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getUpdateFactura()) {
                facturaPersistance.updateFactura(facturaDTO);
            }
        }
        // delete factura
        if (cliente.getDeleteFactura() != null) {
            for (FacturaDTO facturaDTO : cliente.getDeleteFactura()) {
                clienteMasterPersistance.deleteClienteFactura(cliente.getClienteEntity().getId(), facturaDTO.getId());
                facturaPersistance.deleteFactura(facturaDTO.getId());
            }
        }
        // persist new carritoDeCompras
        if (cliente.getCreateCarritoDeCompras() != null) {
            for (CarritoDeComprasDTO carritoDeComprasDTO : cliente.getCreateCarritoDeCompras()) {
                CarritoDeComprasDTO persistedCarritoDeComprasDTO = carritoDeComprasPersistance.createCarritoDeCompras(carritoDeComprasDTO);
                ClienteCarritoDeComprasEntity clienteCarritoDeComprasEntity = new ClienteCarritoDeComprasEntity(cliente.getClienteEntity().getId(), persistedCarritoDeComprasDTO.getId());
                clienteMasterPersistance.createClienteCarritoDeCompras(clienteCarritoDeComprasEntity);
            }
        }
        // update carritoDeCompras
        if (cliente.getUpdateCarritoDeCompras() != null) {
            for (CarritoDeComprasDTO carritoDeComprasDTO : cliente.getUpdateCarritoDeCompras()) {
                carritoDeComprasPersistance.updateCarritoDeCompras(carritoDeComprasDTO);
            }
        }
        // delete carritoDeCompras
        if (cliente.getDeleteCarritoDeCompras() != null) {
            for (CarritoDeComprasDTO carritoDeComprasDTO : cliente.getDeleteCarritoDeCompras()) {
                clienteMasterPersistance.deleteClienteCarritoDeCompras(cliente.getClienteEntity().getId(), carritoDeComprasDTO.getId());
                carritoDeComprasPersistance.deleteCarritoDeCompras(carritoDeComprasDTO.getId());
            }
        }
        // persist new producto
        if (cliente.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : cliente.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                ClienteProductoEntity clienteProductoEntity = new ClienteProductoEntity(cliente.getClienteEntity().getId(), persistedProductoDTO.getId());
                clienteMasterPersistance.createClienteProducto(clienteProductoEntity);
            }
        }
        // update producto
        if (cliente.getUpdateProducto() != null) {
            for (ProductoDTO productoDTO : cliente.getUpdateProducto()) {
                productoPersistance.updateProducto(productoDTO);
            }
        }
        // delete producto
        if (cliente.getDeleteProducto() != null) {
            for (ProductoDTO productoDTO : cliente.getDeleteProducto()) {
                clienteMasterPersistance.deleteClienteProducto(cliente.getClienteEntity().getId(), productoDTO.getId());
                productoPersistance.deleteProducto(productoDTO.getId());
            }
        }
    }
}
