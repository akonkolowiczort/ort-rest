package uy.edu.ort.controller;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uy.edu.ort.dto.*;
import uy.edu.ort.exceptions.PersonaNotFoundException;
import uy.edu.ort.service.PersonaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("personas")
class PersonaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private PersonaService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<PersonaDTO> obtenerTodas(@RequestHeader("X-Auth-User") @NotNull String user) {
        LOGGER.info("User: " + user);
        return service.obtenerPersonas();
    }

    @RequestMapping(value = "/formats", method = RequestMethod.GET)
    public PersonasXMLDTO obtenerTodasMultipleFormat() {
        return new PersonasXMLDTO(service.obtenerPersonas()
                .stream().map(p -> new PersonaXMLDTO(p.getId(), p.getNombre(), p.getApellido()))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonaDTO obtenerPorId(@PathVariable("id") Long id) {
        return service.obtenerPersona(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Long guardar(@RequestBody GuardarPersonaDTO persona) {
        return service.guardarPersona(persona);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void actualizar(@PathVariable( "id" ) Long id, @RequestBody GuardarPersonaDTO persona) {
        service.actualizarPersona(id, persona);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void borrar(@PathVariable("id") Long id) {
        service.borrarPersona(id);
    }


    @ExceptionHandler(PersonaNotFoundException.class)
    public ErrorDTO handlePersonaNotFoundException(PersonaNotFoundException ex){
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorDTO handleException(Exception ex){
        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

}
