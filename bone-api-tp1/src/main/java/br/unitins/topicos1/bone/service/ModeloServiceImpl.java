package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.ModeloDTO;
import br.unitins.topicos1.bone.dto.ModeloDTOResponse;
import br.unitins.topicos1.bone.model.Modelo;
import br.unitins.topicos1.bone.repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService {

    private static final Logger LOG = Logger.getLogger(ModeloServiceImpl.class);

    @Inject
    ModeloRepository repository;

    @Override
    public List<ModeloDTOResponse> findAll() {
        LOG.info("Requisição para buscar todos os modelos");
        List<ModeloDTOResponse> response = repository.listAll()
                .stream()
                .map(ModeloDTOResponse::valueOf)
                .toList();
        LOG.info("Resposta enviada para buscar todos os modelos");
        return response;
    }

    @Override
    public List<ModeloDTOResponse> findByNome(String nome) {
        LOG.infof("Requisição para buscar modelos pelo nome: %s", nome);
        List<ModeloDTOResponse> response = repository.findByNome(nome)
                .stream()
                .map(ModeloDTOResponse::valueOf)
                .toList();
        LOG.infof("Resposta enviada para modelos com nome: %s", nome);
        return response;
    }

    @Override
    public ModeloDTOResponse findById(Long id) {
        LOG.infof("Requisição para buscar modelo pelo ID: %d", id);
        ModeloDTOResponse response = ModeloDTOResponse.valueOf(repository.findById(id));
        LOG.infof("Resposta enviada para modelo ID: %d", id);
        return response;
    }

    @Override
    @Transactional
    public ModeloDTOResponse create(ModeloDTO dto) {
        LOG.infof("Requisição para criar modelo: %s", dto.nome());

        Modelo modelo = new Modelo();
        modelo.setNome(dto.nome());
        modelo.setCategoria(dto.categoria());
        modelo.setEstilo(dto.estilo());

        repository.persist(modelo);

        LOG.infof("Modelo '%s' criado com sucesso", dto.nome());
        return ModeloDTOResponse.valueOf(modelo);
    }

    @Override
    @Transactional
    public void update(Long id, ModeloDTO dto) {
        LOG.infof("Requisição para atualizar modelo ID: %d", id);
        Modelo modelo = repository.findById(id);

        modelo.setNome(dto.nome());
        modelo.setCategoria(dto.categoria());
        modelo.setEstilo(dto.estilo());

        LOG.infof("Modelo ID %d atualizado com sucesso", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.infof("Requisição para deletar modelo ID: %d", id);
        repository.deleteById(id);
        LOG.infof("Modelo ID %d deletado com sucesso", id);
    }
}
