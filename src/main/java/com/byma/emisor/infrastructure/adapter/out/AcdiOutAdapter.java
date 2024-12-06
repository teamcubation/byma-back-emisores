package com.byma.emisor.infrastructure.adapter.out;


import com.byma.emisor.application.exception.acdi.AcdiNoEncontradoException;
import com.byma.emisor.application.port.out.AcdiOutPort;
import com.byma.emisor.domain.model.Acdi;
import com.byma.emisor.exception_handler.ErrorMessages;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.AcdiEntity;
import com.byma.emisor.infrastructure.adapter.out.persistance.mapper.AcdiPersistanceMapper;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.AcdiRepository;
import com.byma.emisor.util.validation.Validador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AcdiOutAdapter implements AcdiOutPort {

    private final AcdiRepository acdiRepository;

    @Override
    public Acdi guardarAcdi(Acdi acdi) {
        log.info("Iniciando guardado de ACDI: {}", acdi);
        Validador.validarNoNulo(acdi);
        AcdiEntity acdiEntityGuardado = acdiRepository.save(AcdiPersistanceMapper.acdiModelAAcdiEntity(acdi));
        Acdi acdiModelGuardado = AcdiPersistanceMapper.acdiEntityAAcdiModel(acdiEntityGuardado);
        log.info("ACDI guardado exitosamente: {}", acdiModelGuardado);
        return acdiModelGuardado;
    }

    @Override
    public Optional<Acdi> obtenerAcdiPorId(Long idAcdi) {
        log.info("Iniciando obtención de ACDI con ID: {}", idAcdi);
        Validador.validarNoNulo(idAcdi);
        Optional<AcdiEntity> acdiEntityOpt = acdiRepository.findById(idAcdi);
        if (acdiEntityOpt.isPresent()) {
            Acdi acdi = AcdiPersistanceMapper.acdiEntityAAcdiModel(acdiEntityOpt.get());
            log.info("ACDI encontrado: {}", acdi);
            return Optional.of(acdi);
        }
        log.error("No se encontro ACDI con ID: {}", idAcdi);
        throw new AcdiNoEncontradoException(ErrorMessages.ACDI_NO_ENCOTRADO);
    }

    @Override
    public List<Acdi> obtenerTodosAcdis() {
        log.info("Iniciando obtencion de todos los ACDIs");
        List<Acdi> acdis = AcdiPersistanceMapper.acdiEntitiesAAcdiModels(acdiRepository.findAll());
        log.info("Se obtuvieron {} ACDIs", acdis.size());
        return acdis;
    }

    @Override
    public void eliminarAcdi(Long idAcdi) {
        log.info("Iniciando eliminacion de ACDI con ID: {}", idAcdi);
        Validador.validarNoNulo(idAcdi);
        if (!acdiRepository.existsById(idAcdi)) {
            log.error("No se encontro ACDI con ID: {}", idAcdi);
            throw new AcdiNoEncontradoException(ErrorMessages.ACDI_NO_ENCOTRADO);
        }
        acdiRepository.deleteById(idAcdi);
        log.info("ACDI con ID: {} eliminado exitosamente", idAcdi);
    }
}
