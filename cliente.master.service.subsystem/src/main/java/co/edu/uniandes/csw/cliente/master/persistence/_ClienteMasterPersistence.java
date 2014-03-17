package co.edu.uniandes.csw.cliente.master.persistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteCarritoDeComprasEntity;
import co.edu.uniandes.csw.carritodecompras.persistence.converter.CarritoDeComprasConverter;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteProductoEntity;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.master.persistence.api._IClienteMasterPersistence;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _ClienteMasterPersistence implements _IClienteMasterPersistence {

    @PersistenceContext(unitName = "ClienteMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IClientePersistence clientePersistence;  

    public ClienteMasterDTO getCliente(Long clienteId) {
        ClienteMasterDTO clienteMasterDTO = new ClienteMasterDTO();
        ClienteDTO cliente = clientePersistence.getCliente(clienteId);
        clienteMasterDTO.setClienteEntity(cliente);
        clienteMasterDTO.setListFactura(getFacturaListForCliente(clienteId));
        clienteMasterDTO.setListCarritoDeCompras(getCarritoDeComprasListForCliente(clienteId));
        clienteMasterDTO.setListProducto(getProductoListForCliente(clienteId));
        return clienteMasterDTO;
    }

    public ClienteFacturaEntity createClienteFactura(ClienteFacturaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteClienteFactura(Long clienteId, Long facturaId) {
        Query q = entityManager.createNamedQuery("ClienteFacturaEntity.deleteClienteFactura");
        q.setParameter("clienteId", clienteId);
        q.setParameter("facturaId", facturaId);
        q.executeUpdate();
    }

    public List<FacturaDTO> getFacturaListForCliente(Long clienteId) {
        ArrayList<FacturaDTO> resp = new ArrayList<FacturaDTO>();
        Query q = entityManager.createNamedQuery("ClienteFacturaEntity.getFacturaListForCliente");
        q.setParameter("clienteId", clienteId);
        List<ClienteFacturaEntity> qResult =  q.getResultList();
        for (ClienteFacturaEntity clienteFacturaEntity : qResult) { 
            if(clienteFacturaEntity.getFacturaEntity()==null){
                entityManager.refresh(clienteFacturaEntity);
            }
            resp.add(FacturaConverter.entity2PersistenceDTO(clienteFacturaEntity.getFacturaEntity()));
        }
        return resp;
    }
    public ClienteCarritoDeComprasEntity createClienteCarritoDeCompras(ClienteCarritoDeComprasEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteClienteCarritoDeCompras(Long clienteId, Long carritoDeComprasId) {
        Query q = entityManager.createNamedQuery("ClienteCarritoDeComprasEntity.deleteClienteCarritoDeCompras");
        q.setParameter("clienteId", clienteId);
        q.setParameter("carritoDeComprasId", carritoDeComprasId);
        q.executeUpdate();
    }

    public List<CarritoDeComprasDTO> getCarritoDeComprasListForCliente(Long clienteId) {
        ArrayList<CarritoDeComprasDTO> resp = new ArrayList<CarritoDeComprasDTO>();
        Query q = entityManager.createNamedQuery("ClienteCarritoDeComprasEntity.getCarritoDeComprasListForCliente");
        q.setParameter("clienteId", clienteId);
        List<ClienteCarritoDeComprasEntity> qResult =  q.getResultList();
        for (ClienteCarritoDeComprasEntity clienteCarritoDeComprasEntity : qResult) { 
            if(clienteCarritoDeComprasEntity.getCarritoDeComprasEntity()==null){
                entityManager.refresh(clienteCarritoDeComprasEntity);
            }
            resp.add(CarritoDeComprasConverter.entity2PersistenceDTO(clienteCarritoDeComprasEntity.getCarritoDeComprasEntity()));
        }
        return resp;
    }
    public ClienteProductoEntity createClienteProducto(ClienteProductoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteClienteProducto(Long clienteId, Long productoId) {
        Query q = entityManager.createNamedQuery("ClienteProductoEntity.deleteClienteProducto");
        q.setParameter("clienteId", clienteId);
        q.setParameter("productoId", productoId);
        q.executeUpdate();
    }

    public List<ProductoDTO> getProductoListForCliente(Long clienteId) {
        ArrayList<ProductoDTO> resp = new ArrayList<ProductoDTO>();
        Query q = entityManager.createNamedQuery("ClienteProductoEntity.getProductoListForCliente");
        q.setParameter("clienteId", clienteId);
        List<ClienteProductoEntity> qResult =  q.getResultList();
        for (ClienteProductoEntity clienteProductoEntity : qResult) { 
            if(clienteProductoEntity.getProductoEntity()==null){
                entityManager.refresh(clienteProductoEntity);
            }
            resp.add(ProductoConverter.entity2PersistenceDTO(clienteProductoEntity.getProductoEntity()));
        }
        return resp;
    }

}
