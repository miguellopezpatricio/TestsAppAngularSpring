package com.sistema.examenes.servicios;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.repositorios.RolRepository;
import com.sistema.examenes.repositorios.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private RolRepository rolRepo;
	
	

	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		
		Usuario usuarioLocal = usuarioRepo.findByUsername(usuario.getUsername());
		
		if(usuarioLocal != null) {
			System.out.println("El usuario ya existe");
			throw new Exception ("El usuario ya existe");
		}else {
			for(UsuarioRol usuarioRol:usuarioRoles) {
				rolRepo.save(usuarioRol.getRol());
			}
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			usuarioLocal = usuarioRepo.save(usuario);
		}
		return usuarioLocal;
	}



	@Override
	public Usuario obtenerUsuario(String username) {
		
		return usuarioRepo.findByUsername(username);
	}



	@Override
	public void eliminarUsuario(Long usuarioId) {
		usuarioRepo.deleteById(usuarioId);
	}

}
