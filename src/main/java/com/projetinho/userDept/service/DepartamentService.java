package com.projetinho.userDept.service;

import com.projetinho.userDept.exception.BadRequestException;
import com.projetinho.userDept.mapper.DepartamentMapper;
import com.projetinho.userDept.model.Departament;
import com.projetinho.userDept.repository.DepartamentRepository;
import com.projetinho.userDept.requests.DepartamentPostRequestBody;
import com.projetinho.userDept.requests.DepartamentPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentService {
    private final DepartamentRepository departamentRepository;

    public List<Departament> findAll(){
        return departamentRepository.findAll();
    }

    public Departament findByIdOrThrowsBadRequestException(Long id){
        return departamentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Departament Id not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    public Departament save(DepartamentPostRequestBody departament){
        Departament toDepartament = DepartamentMapper.INSTANCE.toDepartament(departament);
        return departamentRepository.save(toDepartament);
    }

    @Transactional(rollbackFor = Exception.class)
    public void replace(DepartamentPutRequestBody departament){
        System.out.println(departament.getIdDepartament());
        Departament departamentFind = findByIdOrThrowsBadRequestException(departament.getIdDepartament());
        Departament toDepartament = DepartamentMapper.INSTANCE.toDepartament(departament);

        toDepartament.setIdDepartament(departamentFind.getIdDepartament());
        departamentRepository.save(toDepartament);
    }

    public void delete(Long id){
        Departament departament = findByIdOrThrowsBadRequestException(id);
        departamentRepository.delete(departament);
    }
}
