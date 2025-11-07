package br.unitins.topicos1.bone.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.bone.dto.BoneDTO;
import br.unitins.topicos1.bone.dto.BoneDTOResponse;
import br.unitins.topicos1.bone.model.Bone;
import br.unitins.topicos1.bone.model.Estampa;
import br.unitins.topicos1.bone.model.Estoque;
import br.unitins.topicos1.bone.model.Marca;
import br.unitins.topicos1.bone.model.Material;
import br.unitins.topicos1.bone.model.Modelo;
import br.unitins.topicos1.bone.repository.BoneRepository;
import br.unitins.topicos1.bone.repository.EstampaRepository;
import br.unitins.topicos1.bone.repository.EstoqueRepository;
import br.unitins.topicos1.bone.repository.MarcaRepository;
import br.unitins.topicos1.bone.repository.MaterialRepository;
import br.unitins.topicos1.bone.repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BoneServiceImpl implements BoneService {

    @Inject
    BoneRepository repository;

    @Inject
    MaterialRepository repositoryMaterial;

    @Inject
    MarcaRepository repositoryMarca;

    @Inject
    ModeloRepository repositoryModelo;

    @Inject
    EstoqueRepository repositoryEstoque;

    @Inject
    EstampaRepository repositoryEstampa;

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
        Bone bone = repository.findById(id);
        if(bone == null){
            return null;
        }
        return BoneDTOResponse.valueOf(bone);
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
        bone.setCircunferencia(dto.circunferencia());
        bone.setBordado(dto.bordado());

        Material material = repositoryMaterial.findById(dto.idMaterial());
        bone.setMaterial(material);

        Marca marca = repositoryMarca.findById(dto.idMarca());
        bone.setMarca(marca);

        Modelo modelo = repositoryModelo.findById(dto.idModelo());
        bone.setModelo(modelo);

        Estoque estoque = new Estoque();
        int quantidade = (dto.quantidadeEstoque() != null) ? dto.quantidadeEstoque() : 0;
        estoque.setQuantidade(quantidade);
        estoque.setDataAtualizacao(LocalDate.now());
        repositoryEstoque.persist(estoque);
        
        bone.setEstoque(estoque);

        repository.persist(bone);

        if (bone.getEstampas() == null) {
            bone.setEstampas(new ArrayList<>());
        }

        if (dto.idsEstampas() == null || dto.idsEstampas().isEmpty()) {
            bone.getEstampas().clear();
            return BoneDTOResponse.valueOf(bone);
        }

        List<Estampa> estampas = repositoryEstampa.findByIds(dto.idsEstampas());

        if (estampas.size() != dto.idsEstampas().size()) {

            throw new IllegalArgumentException("Uma ou mais estampas informadas não existem.");
        }

        bone.setEstampas(estampas);

        return BoneDTOResponse.valueOf(bone);
    }

    @Override
    @Transactional
    public void update(Long id, BoneDTO dto) {

        Bone bone = repository.findById(id);

        if(bone == null){
            throw new NotFoundException("Boné não encontrado");
        }

        bone.setNome(dto.nome());
        bone.setCor(dto.cor());
        bone.setCategoriaAba(dto.categoriaAba());
        bone.setTamanhoAba(dto.tamanhoAba());
        bone.setProfundidade(dto.profundidade());
        bone.setCircunferencia(dto.circunferencia());
        bone.setBordado(dto.bordado());

        Material material = repositoryMaterial.findById(dto.idMaterial());
        bone.setMaterial(material);

        Marca marca = repositoryMarca.findById(dto.idMarca());
        bone.setMarca(marca);

        Modelo modelo = repositoryModelo.findById(dto.idModelo());
        bone.setModelo(modelo);


        if (bone.getEstampas() == null) {
            bone.setEstampas(new ArrayList<>());
        }

        if (dto.idsEstampas() == null || dto.idsEstampas().isEmpty()) {

            bone.getEstampas().clear();
            return;
        }

        List<Estampa> estampas = repositoryEstampa.findByIds(dto.idsEstampas());

        if (estampas.size() != dto.idsEstampas().size()) {

            throw new IllegalArgumentException("Uma ou mais estampas informadas não existem.");
        }

        bone.setEstampas(estampas);
        
        Estoque estoque = bone.getEstoque();
    
        if (estoque == null) {
            estoque = new Estoque();
            int quantidade = (dto.quantidadeEstoque() != null) ? dto.quantidadeEstoque() : 0;
            estoque.setQuantidade(quantidade);
            estoque.setDataAtualizacao(LocalDate.now());
            repositoryEstoque.persist(estoque);
            bone.setEstoque(estoque);
        } else {
            if(dto.quantidadeEstoque() != null){
                estoque.setQuantidade(dto.quantidadeEstoque());
            }
            estoque.setDataAtualizacao(LocalDate.now());
        }
    
    }

    @Override
    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);

    }
    
}