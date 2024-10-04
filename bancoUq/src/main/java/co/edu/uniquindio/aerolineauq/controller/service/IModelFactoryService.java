package co.edu.uniquindio.aerolineauq.controller.service;

import co.edu.uniquindio.aerolineauq.mapping.dto.EmpleadoDto;

import java.util.List;


public interface IModelFactoryService {

	List<EmpleadoDto> obtenerEmpleados();
	boolean agregarEmpleado(EmpleadoDto empleadoDto);

	boolean eliminarEmpleado(String cedula);

	boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto);


}
