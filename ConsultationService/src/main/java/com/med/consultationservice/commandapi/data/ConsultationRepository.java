package com.med.consultationservice.commandapi.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,String> {
}
