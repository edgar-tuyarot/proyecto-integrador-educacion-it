package com.limpiezaIt.service.impl;

import com.limpiezaIt.entity.Cliente;
import com.limpiezaIt.repository.ClienteRepository;
import com.limpiezaIt.service.interfaces.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    //Guardar o actualizar categoría
    public Cliente guardarCliente(Cliente ccliente) {

        


        Cliente nuevoCliente = Cliente.builder()
                .nombre("Cliente1")
                .apellido("Cliente Ap")
                .build();


        return nuevoCliente;
    }


    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(optionalCliente.isPresent()) return optionalCliente;
        return Optional.empty();
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        //verificamos que el producto este en DB
        Optional<Cliente> opt = buscarPorId(id);

        //Si esta, se pasa a actualizar
        if(opt.isPresent()){
            Cliente clienteDB = opt.get();
            clienteDB.setNombre(cliente.getNombre());
            clienteDB.setApellido(cliente.getApellido());
            clienteDB.setEmail(cliente.getEmail());
            clienteDB.setTelefono(cliente.getTelefono());
            clienteDB.setCelular(cliente.getCelular());
            //Se guarda
            return clienteRepository.save(clienteDB);

        }else {
            //Se retorna null si no esta.
            return null;
        }

    }

    @Override
    public boolean desactivarCliente(Long id) {
        Optional<Cliente> opt = buscarPorId(id);
        if (opt.isPresent()){
            clienteRepository.desactivarCliente(id);
            return true;
        }
        return false;
    }
}
