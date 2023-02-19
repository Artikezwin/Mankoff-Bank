package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.Transfer;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.TransferRepository;
import ru.evsmanko.mankoff.service.GregoryService;
import ru.evsmanko.mankoff.service.VeronikaService;
import ru.evsmanko.mankoff.service.mapper.MappingUtils;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final VeronikaService veronikaService;
    private final GregoryService gregoryService;
    private final MappingUtils mappingUtils;
    private final TransferRepository transferRepository;

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
    public String getTransfer(@PathVariable("id") long userId, Model model){
        List<Transfer> lst = transferRepository.findTransfersBySenderId(userId);
        if(!lst.isEmpty()){
            model.addAttribute("transfers", lst);
        }
        return "transfers";
    }

    @GetMapping("/get-transfers")
    public String listOfTransfers(Model model){
        List<Transfer> lst = transferRepository.findAll();
        if(!lst.isEmpty()){
            model.addAttribute("transfers", lst);
        }
        return "transfers";
    }

    @GetMapping("/transfer/save")
    public String newTransfer(Model model){
        model.addAttribute("newTransfer", new Transfer());
        return "successPage";
    }

    @PostMapping("/transfer/save")
    public String saveTransfer(@ModelAttribute("newTransfer") Transfer transfer){
        transferRepository.save(transfer);
        return "redirect:/contacts";
    }

}
