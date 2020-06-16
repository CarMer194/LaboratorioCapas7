package com.uca.capas.Laboratorio7.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.Laboratorio7.Domain.Estudiante;




@Repository
public class EstudianteDAOImp implements EstudianteDAO {
	
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;
	
	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		
		StringBuffer sb=new StringBuffer();
		sb.append("select * from ESTUDIANTE");
		Query query= entityManager.createNativeQuery(sb.toString(),Estudiante.class);
		List<Estudiante> estudiantes=query.getResultList();
		return estudiantes;
	}

	@Override
	public Estudiante findOne(Integer codigo) throws DataAccessException {
		Estudiante estudiante =entityManager.find(Estudiante.class, codigo);
		return estudiante;
	}

	@Override
	@Transactional
	public void insertarEstudiante(Estudiante estudiante) throws DataAccessException {
		entityManager.persist(estudiante);	
	}

	@Override
	@Transactional
	public void eliminarEstudiante(Estudiante estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.find(Estudiante.class, estudiante.getCodigo()));
		
	}
	
	@Transactional
	@Override
	public void save(Estudiante estudiante) throws DataAccessException {
		
		try {
			if(estudiante.getCodigo()==null) {
				System.out.println("ESTUDIANTE: " + estudiante.getCodigo());
				entityManager.persist(estudiante);
			}else {
				entityManager.merge(estudiante);
				entityManager.flush();
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Override
	public void delete(Integer codigoEstudiante) throws DataAccessException {
		Estudiante estudiante = entityManager.find(Estudiante.class, codigoEstudiante);
		entityManager.remove(estudiante);
	}
	
}
