package co.edu.uniandes.csw.cliente.master.persistence.converter;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteCarritoDeComprasEntity;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.persistence.converter.CarritoDeComprasConverter;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _ClienteMasterConverter {

    public static ClienteMasterDTO entity2PersistenceDTO(ClienteEntity clienteEntity 
    ,List<ClienteFacturaEntity> clienteFacturaEntity 
    ,List<ClienteCarritoDeComprasEntity> clienteCarritoDeComprasEntity 
    ,List<ClienteProductoEntity> clienteProductoEntity 
    ) {
        ClienteDTO clienteDTO = ClienteConverter.entity2PersistenceDTO(clienteEntity);
        ArrayList<FacturaDTO> facturaEntities = new ArrayList<FacturaDTO>(clienteFacturaEntity.size());
        for (ClienteFacturaEntity clienteFactura : clienteFacturaEntity) {
            facturaEntities.add(FacturaConverter.entity2PersistenceDTO(clienteFactura.getFacturaEntity()));
        }
        ArrayList<CarritoDeComprasDTO> carritoDeComprasEntities = new ArrayList<CarritoDeComprasDTO>(clienteCarritoDeComprasEntity.size());
        for (ClienteCarritoDeComprasEntity clienteCarritoDeCompras : clienteCarritoDeComprasEntity) {
            carritoDeComprasEntities.add(CarritoDeComprasConverter.entity2PersistenceDTO(clienteCarritoDeCompras.getCarritoDeComprasEntity()));
        }
        ArrayList<ProductoDTO> productoEntities = new ArrayList<ProductoDTO>(clienteProductoEntity.size());
        for (ClienteProductoEntity clienteProducto : clienteProductoEntity) {
            productoEntities.add(ProductoConverter.entity2PersistenceDTO(clienteProducto.getProductoEntity()));
        }
        ClienteMasterDTO respDTO  = new ClienteMasterDTO();
        respDTO.setClienteEntity(clienteDTO);
        respDTO.setListFactura(facturaEntities);
        respDTO.setListCarritoDeCompras(carritoDeComprasEntities);
        respDTO.setListProducto(productoEntities);
        return respDTO;
    }

}