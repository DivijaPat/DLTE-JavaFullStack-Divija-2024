package com.accountsjpa.demo.remotes;

import com.accountsjpa.demo.model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Accounts,Long> {
    Accounts save(Accounts accounts);
}