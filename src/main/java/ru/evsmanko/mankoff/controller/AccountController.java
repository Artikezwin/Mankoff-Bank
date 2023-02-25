package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.evsmanko.mankoff.dto.TransferDTO;
import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.PaymentEntity;
import ru.evsmanko.mankoff.entity.Transfer;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.TransferRepository;
import ru.evsmanko.mankoff.service.GregoryService;
import ru.evsmanko.mankoff.service.VeronikaService;
import ru.evsmanko.mankoff.service.mapper.MappingUtils;
import ru.evsmanko.mankoff.service.mapper.TransferMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final VeronikaService veronikaService;
    private final GregoryService gregoryService;
    private final MappingUtils mappingUtils;
    private final TransferRepository transferRepository;
    @Autowired
    private TransferMapper transferMapper;

    @GetMapping("/user/{id}")
    public String userInformation(Model model, @PathVariable("id") long id) {
        UserDTO userDTO = mappingUtils.mapToUserDto(veronikaService.getUserInformationById(id));
        model.addAttribute("userInformation", userDTO);
        return "user-information";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDTO userDTO) {
        User user = mappingUtils.mapToUserEntity(userDTO);
        gregoryService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/balance")
    public String balanceInformation() {
        return "balance";
    }

    @GetMapping("/get-transfer/{id}")
    public String getTransfer(@PathVariable("id") long userId, Model model) {
        List<Transfer> transfers = transferRepository.findTransfersBySenderId(userId);
        List<TransferDTO> transfersDTO = new ArrayList<>();

        for (int i = 0; i < transfers.size(); i++) {
            transfersDTO.add(transferMapper.transferToTransferDTO(transfers.get(i)));
        }

        if (!transfersDTO.isEmpty()) {
            model.addAttribute("transfers", transfersDTO);
        }
        return "transfers";
    }

    @GetMapping("/get-transfers")
    public String listOfTransfers(Model model) {
        List<Transfer> transfers = transferRepository.findAll();
        List<TransferDTO> transfersDTO = new ArrayList<>();

        for (int i = 0; i < transfers.size(); i++) {
            transfersDTO.add(transferMapper.transferToTransferDTO(transfers.get(i)));
        }

        if (!transfersDTO.isEmpty()) {
            model.addAttribute("transfers", transfersDTO);
        }
        return "transfers";
    }

    @GetMapping("/transfer/save")
    public String newTransfer(Model model) {
        model.addAttribute("newTransfer", new TransferDTO());
        return "successPage";
    }

    @PostMapping("/transfer/save")
    public String saveTransfer(@ModelAttribute("newTransfer") TransferDTO transferDTO) {
        transferDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transferRepository.save(transferMapper.transferDTOtoTransfer(transferDTO));
        return "redirect:/contacts";
    }

    @GetMapping("/payments/{id}")
    public String paymentByShopperId(Model model, @PathVariable("id") long id) {
        model.addAttribute("payments", veronikaService.getAllByShopperId(id));
        return "user-payments";
    }

    @PostMapping("/payment/add")
    public String savePayment(@ModelAttribute PaymentEntity paymentEntity) {
        veronikaService.savePayment(paymentEntity);
        return "redirect:/";
    }


}
