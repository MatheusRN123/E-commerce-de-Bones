package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.BoneDTO;
import br.unitins.topicos1.bone.dto.BoneDTOResponse;
import br.unitins.topicos1.bone.model.Bone;
import br.unitins.topicos1.bone.model.Material;
import br.unitins.topicos1.bone.repository.BoneRepository;
import br.unitins.topicos1.bone.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BoneServiceImpl implements BoneService {

    @Inject
    BoneRepository repository;

    @Inject
    MaterialRepository repositoryMaterial;

    @Override
    public List<BoneDTOResponse> findAll() {
        return repository
                    .listAll()
                    .stream()
                    .map(m -> BoneDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<BoneDTOResponse> findByNome(String nome) {
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(m -> BoneDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public BoneDTOResponse findById(Long id) {
        return BoneDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public BoneDTOResponse create(BoneDTO dto) {

        Bone bone = new Bone();
        bone.setNome(dto.nome());
        bone.setCor(dto.cor());
        bone.setCategoriaAba(dto.categoriaAba());
        bone.setTamanhoAba(dto.tamanhoAba());
        bone.setProfundidade(dto.profundidade());
        bone.setCircunferência(dto.circunferência());
        bone.setBordado(dto.bordado());

        Material material = repositoryMaterial.findById(dto.idMaterial());
        bone.setMaterial(material);

        repository.persist(bone);

        return BoneDTOResponse.valueOf(bone);
    }

    @Override
    @Transactional
    public void update(Long id, BoneDTO dto) {

        Bone bone = repository.findById(id);

        bone.setNome(dto.nome());
        bone.setCor(dto.cor());
        bone.setCategoriaAba(dto.categoriaAba());
        bone.setTamanhoAba(dto.tamanhoAba());
        bone.setProfundidade(dto.profundidade());
        bone.setCircunferência(dto.circunferência());
        bone.setBordado(dto.bordado());

        Material material = repositoryMaterial.findById(dto.idMaterial());
        bone.setMaterial(material);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
}