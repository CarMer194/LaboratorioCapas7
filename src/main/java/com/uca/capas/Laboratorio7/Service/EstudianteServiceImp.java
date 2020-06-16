package com.uca.capas.Laboratorio7.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.Laboratorio7.Domain.Estudiante;
import com.uca.capas.Laboratorio7.Repositories.EstudianteRepo;

@Service
public class EstudianteServiceImp implements EstudianteService{
	
	@Autowired
	EstudianteRepo estudianteRepo;

	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		return estudianteRepo.mostrarTodos();
	}

	@Override
	public Estudiante findOne(Integer code) throws DataAccessException {
		return estudianteRepo.getOne(code);
	}
	
	@Transactional
	@Override
	public void save(Estudiante estudiante) throws DataAccessException {
		estudianteRepo.save(estudiante);
	}
	
	@Transactional
	@Override
	public void delete(Integer codigoEstudiante) throws DataAccessException {
		estudianteRepo.deleteById(codigoEstudiante);
	}

	@Override
	public List<Estudiante> filtrarPor(String cadena) throws DataAccessException {
		return estudianteRepo.mostrarPorNombre(cadena);
	}

	@Override
	public List<Estudiante> startsWith(String cadena) throws DataAccessException {
		return estudianteRepo.findByApellidoStartingWith(cadena);
	}
	
}
