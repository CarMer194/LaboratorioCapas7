package com.uca.capas.Laboratorio7.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.Laboratorio7.Domain.Estudiante;
import com.uca.capas.Laboratorio7.Service.EstudianteService;



@Controller
public class MainController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	//Comienza Aca
	@RequestMapping("/estudiante")
	public ModelAndView initMain() {
		ModelAndView model=new ModelAndView();
		List<Estudiante> estudiantes=null; 
		try {
			estudiantes=estudianteService.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		model.addObject("estudiantes",estudiantes);
		model.setViewName("main");
		
		return model;
	}
	
	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView model=new ModelAndView();
		model.setViewName("main2");
		return model;
	}
	
	@RequestMapping(value="/mostrarEstudiante",method=RequestMethod.POST)
	public ModelAndView findOne(@RequestParam(value="codigo") int id) {
		ModelAndView model=new ModelAndView();
		Estudiante estudiante=null;
		try {
			estudiante=estudianteService.findOne(id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		model.addObject("estudiante",estudiante);
		model.setViewName("estudiante");
		return model;
	}
	
	@RequestMapping("/inicio")
	public ModelAndView init() {
		ModelAndView model = new ModelAndView();
		model.addObject("estudiante",new Estudiante());
		model.setViewName("index");		
		return model;
	}
	
	@PostMapping("/save")
	public ModelAndView guardar(@Valid @ModelAttribute Estudiante estudiante,BindingResult result) {
		ModelAndView mav=new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("editarEstudiante");
		}else {
			estudianteService.save(estudiante);
			List<Estudiante> estudiantes=null;
			try {
				estudiantes=estudianteService.findAll();
			}catch(Exception e){
				e.printStackTrace();
			}
			mav.addObject("estudiantes",estudiantes);
			mav.setViewName("main");
		}
		return mav;
	}
	
	@RequestMapping(value="/borrarEstudiante",method=RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value="codigo") int id) {
		ModelAndView mav=new ModelAndView();
		List<Estudiante> estudiantes=null;
		try {
			estudianteService.delete(id);
			estudiantes=estudianteService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("main");
		return mav;
	}
	@GetMapping("/insertarEstudiante")
	public ModelAndView inicio() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("estudiante",new Estudiante());
		mav.setViewName("agregarEstudiante");
		return mav;
	}
	
	@GetMapping("/filtrar")
	public ModelAndView filtrar(@RequestParam(value="nombre") String nombre) {
		ModelAndView mav=new ModelAndView();
		List<Estudiante> estudiantes=null;
		try {
			estudiantes = estudianteService.filtrarPor(nombre);
			//estudiantes=estudianteService.startsWith(nombre);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/editarEstudiante")
	public ModelAndView editarEstudiante(@RequestParam(value="codigoEditar")int codigo) {
		ModelAndView mav= new ModelAndView();
		Estudiante estudiante=null;
		try {
			estudiante=estudianteService.findOne(codigo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		mav.addObject("estudiante",estudiante);
		mav.setViewName("editarEstudiante");
		return mav;
	}
	
	
	/*@RequestMapping("/agregarEstudiante")
	public ModelAndView agregar (Estudiante estudiante) {
		ModelAndView model=new ModelAndView();
		estudianteDAO.insertarEstudiante(estudiante);
		model.addObject("estudiante",new Estudiante());
		model.setViewName("index");
		return model;
	}
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView model=new ModelAndView();
		List<Estudiante> estudiantes=null;
		try {
			estudiantes=estudianteDAO.findAll();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		model.addObject("estudiantes",estudiantes);
		model.addObject("estudiante",new Estudiante());
		model.setViewName("listado");
		return model;
	}
	@RequestMapping("/eliminarEstudiante")
	public ModelAndView eliminar(Estudiante estudiante) {
		ModelAndView model=new ModelAndView();
		estudianteDAO.eliminarEstudiante(estudiante);
		List<Estudiante> estudiantes=null;
		try {
			estudiantes=estudianteDAO.findAll();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		model.addObject("estudiantes",estudiantes);
		model.addObject("estudiante",new Estudiante());
		model.setViewName("listado");
		return model;
	}*/
}
