package co.edu.uniandes.csw.cliente.master.logic.dto;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _ClienteMasterDTO {

 
    protected ClienteDTO clienteEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ClienteDTO getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteDTO clienteEntity) {
        this.clienteEntity = clienteEntity;
    }
    
    public List<FacturaDTO> createFactura;
    public List<FacturaDTO> updateFactura;
    public List<FacturaDTO> deleteFactura;
    public List<FacturaDTO> listFactura;	
    public List<CarritoDeComprasDTO> createCarritoDeCompras;
    public List<CarritoDeComprasDTO> updateCarritoDeCompras;
    public List<CarritoDeComprasDTO> deleteCarritoDeCompras;
    public List<CarritoDeComprasDTO> listCarritoDeCompras;	
    public List<ProductoDTO> createProducto;
    public List<ProductoDTO> updateProducto;
    public List<ProductoDTO> deleteProducto;
    public List<ProductoDTO> listProducto;	
	
	
	
    public List<FacturaDTO> getCreateFactura(){ return createFactura; };
    public void setCreateFactura(List<FacturaDTO> createFactura){ this.createFactura=createFactura; };
    public List<FacturaDTO> getUpdateFactura(){ return updateFactura; };
    public void setUpdateFactura(List<FacturaDTO> updateFactura){ this.updateFactura=updateFactura; };
    public List<FacturaDTO> getDeleteFactura(){ return deleteFactura; };
    public void setDeleteFactura(List<FacturaDTO> deleteFactura){ this.deleteFactura=deleteFactura; };
    public List<FacturaDTO> getListFactura(){ return listFactura; };
    public void setListFactura(List<FacturaDTO> listFactura){ this.listFactura=listFactura; };	
    public List<CarritoDeComprasDTO> getCreateCarritoDeCompras(){ return createCarritoDeCompras; };
    public void setCreateCarritoDeCompras(List<CarritoDeComprasDTO> createCarritoDeCompras){ this.createCarritoDeCompras=createCarritoDeCompras; };
    public List<CarritoDeComprasDTO> getUpdateCarritoDeCompras(){ return updateCarritoDeCompras; };
    public void setUpdateCarritoDeCompras(List<CarritoDeComprasDTO> updateCarritoDeCompras){ this.updateCarritoDeCompras=updateCarritoDeCompras; };
    public List<CarritoDeComprasDTO> getDeleteCarritoDeCompras(){ return deleteCarritoDeCompras; };
    public void setDeleteCarritoDeCompras(List<CarritoDeComprasDTO> deleteCarritoDeCompras){ this.deleteCarritoDeCompras=deleteCarritoDeCompras; };
    public List<CarritoDeComprasDTO> getListCarritoDeCompras(){ return listCarritoDeCompras; };
    public void setListCarritoDeCompras(List<CarritoDeComprasDTO> listCarritoDeCompras){ this.listCarritoDeCompras=listCarritoDeCompras; };	
    public List<ProductoDTO> getCreateProducto(){ return createProducto; };
    public void setCreateProducto(List<ProductoDTO> createProducto){ this.createProducto=createProducto; };
    public List<ProductoDTO> getUpdateProducto(){ return updateProducto; };
    public void setUpdateProducto(List<ProductoDTO> updateProducto){ this.updateProducto=updateProducto; };
    public List<ProductoDTO> getDeleteProducto(){ return deleteProducto; };
    public void setDeleteProducto(List<ProductoDTO> deleteProducto){ this.deleteProducto=deleteProducto; };
    public List<ProductoDTO> getListProducto(){ return listProducto; };
    public void setListProducto(List<ProductoDTO> listProducto){ this.listProducto=listProducto; };	
	
	
}

