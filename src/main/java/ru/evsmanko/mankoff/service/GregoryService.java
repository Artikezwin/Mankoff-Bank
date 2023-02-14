package ru.evsmanko.mankoff.service;

import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.Proposal;
import ru.evsmanko.mankoff.entity.User;

import java.util.List;

public interface GregoryService {
    User exportUserInJson(long id);
    List<User> getFirstTwelveUsers();
    Proposal saveProposal(Proposal proposal);
    User saveUser(UserDTO userDTO);
}
