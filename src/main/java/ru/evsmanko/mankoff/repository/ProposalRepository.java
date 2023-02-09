package ru.evsmanko.mankoff.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.evsmanko.mankoff.entity.Proposal;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    Proposal save(Proposal proposal);
}
