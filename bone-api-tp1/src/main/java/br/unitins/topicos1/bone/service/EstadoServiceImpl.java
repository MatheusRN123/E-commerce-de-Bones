package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstadoDTO;
import br.unitins.topicos1.bone.dto.EstadoDTOResponse;
import br.unitins.topicos1.bone.model.Cidade;
import br.unitins.topicos1.bone.model.Estado;
import br.unitins.topicos1.bone.repository.CidadeRepository;
import br.unitins.topicos1.bone.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
<<<<<<< HEAD
import org.jboss.logging.Logger;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

<<<<<<< HEAD
    private static final Logger LOG = Logger.getLogger(EstadoServiceImpl.class);

=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    @Inject
    EstadoRepository estadoRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Override
    public List<EstadoDTOResponse> findAll() {
<<<<<<< HEAD
        LOG.info("Buscando todos os estados");
        try {
            var list = estadoRepository.listAll()
                    .stream()
                    .map(EstadoDTOResponse::valueOf)
                    .toList();
            LOG.infof("%d estados encontrados", list.size());
            return list;
        } catch (Exception e) {
            LOG.error("Erro ao buscar todos os estados", e);
            throw e;
        }
=======
        return estadoRepository
                .listAll()
                .stream()
                .map(EstadoDTOResponse::valueOf)
                .toList();
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

    @Override
    public EstadoDTOResponse findById(Long id) {
<<<<<<< HEAD
        LOG.infof("Buscando estado pelo ID: %d", id);
        try {
            Estado estado = estadoRepository.findById(id);
            if (estado == null) {
                LOG.warnf("Estado com ID %d não encontrado", id);
                return null;
            }
            LOG.infof("Estado com ID %d encontrado: %s", id, estado.getNome());
            return EstadoDTOResponse.valueOf(estado);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estado pelo ID: %d", id);
            throw e;
        }
=======
        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Estado não encontrado");

        return EstadoDTOResponse.valueOf(estado);
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

    @Override
    public List<EstadoDTOResponse> findByNome(String nome) {
<<<<<<< HEAD
        LOG.infof("Buscando estados pelo nome: %s", nome);
        try {
            var list = estadoRepository.findByNome(nome)
                    .stream()
                    .map(EstadoDTOResponse::valueOf)
                    .toList();
            LOG.infof("%d estados encontrados com o nome '%s'", list.size(), nome);
            return list;
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estados pelo nome: %s", nome);
            throw e;
        }
    }

    @Override
    public List<EstadoDTOResponse> findBySigla(String sigla) {
        LOG.infof("Buscando estados pela sigla: %s", sigla);
        try {
            var list = estadoRepository.findBySigla(sigla)
                    .stream()
                    .map(EstadoDTOResponse::valueOf)
                    .toList();
            LOG.infof("%d estados encontrados com a sigla '%s'", list.size(), sigla);
            return list;
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estados pela sigla: %s", sigla);
            throw e;
        }
=======
        return estadoRepository
                .find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%")
                .list()
                .stream()
                .map(EstadoDTOResponse::valueOf)
                .toList();
    }

    @Override
    public List<EstadoDTOResponse> findBySigla(String sigla){
        return estadoRepository.findBySigla(sigla)
        .stream()
        .map(EstadoDTOResponse::valueOf).
        toList();
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

    @Override
    @Transactional
    public EstadoDTOResponse create(EstadoDTO dto) {
<<<<<<< HEAD
        LOG.infof("Criando estado: %s (%s)", dto.nome(), dto.sigla());
        try {
            Estado estado = new Estado();
            estado.setNome(dto.nome());
            estado.setSigla(dto.sigla());

            if (dto.idsCidades() != null && !dto.idsCidades().isEmpty()) {
                List<Cidade> cidades = cidadeRepository.findByIds(dto.idsCidades());
                if (cidades.size() != dto.idsCidades().size()) {
                    LOG.error("Uma ou mais cidades informadas não existem.");
                    throw new IllegalArgumentException("Uma ou mais cidades informadas não existem.");
                }
                estado.setCidades(cidades);
            }

            estadoRepository.persist(estado);
            LOG.infof("Estado criado com sucesso: %s (%s), ID: %d", dto.nome(), dto.sigla(), estado.getId());
            return EstadoDTOResponse.valueOf(estado);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao criar estado: %s (%s)", dto.nome(), dto.sigla());
            throw e;
        }
=======

        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        // associa cidades
        if (dto.idsCidades() != null && !dto.idsCidades().isEmpty()) {

            List<Cidade> cidades = cidadeRepository.findByIds(dto.idsCidades());

            if (cidades.size() != dto.idsCidades().size()) {
                throw new IllegalArgumentException("Uma ou mais cidades informadas não existem.");
            }

            estado.setCidades(cidades);
        }

        estadoRepository.persist(estado);

        return EstadoDTOResponse.valueOf(estado);
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

    @Override
    @Transactional
    public void update(Long id, EstadoDTO dto) {
<<<<<<< HEAD
        LOG.infof("Atualizando estado ID: %d", id);
        try {
            Estado estado = estadoRepository.findById(id);
            if (estado == null) {
                LOG.warnf("Estado ID %d não encontrado para atualização", id);
                throw new NotFoundException("Estado não encontrado");
            }

            estado.setNome(dto.nome());
            estado.setSigla(dto.sigla());

            if (dto.idsCidades() == null || dto.idsCidades().isEmpty()) {
                estado.getCidades().clear();
                LOG.infof("Estado ID %d atualizado (sem cidades)", id);
                return;
            }

            List<Cidade> cidades = cidadeRepository.findByIds(dto.idsCidades());
            if (cidades.size() != dto.idsCidades().size()) {
                LOG.error("Uma ou mais cidades informadas não existem.");
                throw new IllegalArgumentException("Uma ou mais cidades informadas não existem.");
            }

            estado.setCidades(cidades);
            LOG.infof("Estado ID %d atualizado com sucesso", id);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar estado ID: %d", id);
            throw e;
        }
=======

        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Estado não encontrado");

        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        if (dto.idsCidades() == null || dto.idsCidades().isEmpty()) {
            estado.getCidades().clear();
            return;
        }

        List<Cidade> cidades = cidadeRepository.findByIds(dto.idsCidades());

        if (cidades.size() != dto.idsCidades().size()) {
            throw new IllegalArgumentException("Uma ou mais cidades informadas não existem.");
        }

        estado.setCidades(cidades);
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

    @Override
    @Transactional
    public void delete(Long id) {
<<<<<<< HEAD
        LOG.infof("Deletando estado ID: %d", id);
        try {
            estadoRepository.deleteById(id);
            LOG.infof("Estado ID %d deletado com sucesso", id);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao deletar estado ID: %d", id);
            throw e;
        }
=======

        boolean removed = estadoRepository.deleteById(id);

        if (!removed)
            throw new NotFoundException("Estado não encontrado");
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }
}
