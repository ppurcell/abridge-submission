package com.abridge.base.repository;

import com.abridge.base.entity.BridgeLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BridgeLinkRepository extends CrudRepository<BridgeLink, Long> {

    BridgeLink findByTerm(String slug);
}
