package ru.evsmanko.mankoff.service;

import ru.evsmanko.mankoff.entity.User;

public interface VeronikaService {
    Double calculateCreditByUser(long id);
    User getUserInformationById(long id);
}
