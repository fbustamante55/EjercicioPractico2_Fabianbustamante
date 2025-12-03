package com.fidelitas.plataforma.service;

import com.fidelitas.plataforma.domain.Rol;
import com.fidelitas.plataforma.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }
    
    public Optional<Rol> buscarPorId(Long id) {
        return rolRepository.findById(id);
    }
    
    public Optional<Rol> buscarPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
    
    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }
    
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
    
    public boolean existePorNombre(String nombre) {
        return rolRepository.existsByNombre(nombre);
    }
}

