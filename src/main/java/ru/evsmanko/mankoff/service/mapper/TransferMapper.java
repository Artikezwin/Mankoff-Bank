package ru.evsmanko.mankoff.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.evsmanko.mankoff.dto.TransferDTO;
import ru.evsmanko.mankoff.entity.Transfer;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransferMapper {
    TransferDTO transferToTransferDTO(Transfer transfer);
    Transfer transferDTOtoTransfer(TransferDTO transferDTO);
}
