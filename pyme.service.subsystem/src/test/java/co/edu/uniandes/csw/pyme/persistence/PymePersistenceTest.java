
package co.edu.uniandes.csw.pyme.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;

@RunWith(Arquillian.class)
public class PymePersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PymePersistence.class.getPackage())
				.addPackage(PymeEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPymePersistence pymePersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from PymeEntity").executeUpdate();
	}

	private List<PymeEntity> data=new ArrayList<PymeEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PymeEntity entity=new PymeEntity();
			entity.setName(generateRandom(String.class));
			entity.setDescripcion(generateRandom(String.class));
			entity.setIdCatalogo(generateRandom(Long.class));
			entity.setClienteId(generateRandom(Long.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createPymeTest(){
		PymeDTO dto=new PymeDTO();
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setIdCatalogo(generateRandom(Long.class));
		dto.setClienteId(generateRandom(Long.class));
		
		
		PymeDTO result=pymePersistence.createPyme(dto);
		
		Assert.assertNotNull(result);
		
		PymeEntity entity=em.find(PymeEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());	
		Assert.assertEquals(dto.getIdCatalogo(), entity.getIdCatalogo());	
		Assert.assertEquals(dto.getClienteId(), entity.getClienteId());	
	}
	
	@Test
	public void getPymesTest(){
		List<PymeDTO> list=pymePersistence.getPymes();
		Assert.assertEquals(list.size(), data.size());
        for(PymeDTO dto:list){
        	boolean found=false;
            for(PymeEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPymeTest(){
		PymeEntity entity=data.get(0);
		PymeDTO dto=pymePersistence.getPyme(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getDescripcion(), dto.getDescripcion());
		Assert.assertEquals(entity.getIdCatalogo(), dto.getIdCatalogo());
		Assert.assertEquals(entity.getClienteId(), dto.getClienteId());
        
	}
	
	@Test
	public void deletePymeTest(){
		PymeEntity entity=data.get(0);
		pymePersistence.deletePyme(entity.getId());
        PymeEntity deleted=em.find(PymeEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePymeTest(){
		PymeEntity entity=data.get(0);
		
		PymeDTO dto=new PymeDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setIdCatalogo(generateRandom(Long.class));
		dto.setClienteId(generateRandom(Long.class));
		
		
		pymePersistence.updatePyme(dto);
		
		
		PymeEntity resp=em.find(PymeEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(dto.getIdCatalogo(), resp.getIdCatalogo());	
		Assert.assertEquals(dto.getClienteId(), resp.getClienteId());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}