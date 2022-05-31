package com.med.illnessservice.commandapi.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IllnessRepository extends JpaRepository<Illness,String> {
}
