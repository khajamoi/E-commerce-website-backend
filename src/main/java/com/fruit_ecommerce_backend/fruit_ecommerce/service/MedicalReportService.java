package com.fruit_ecommerce_backend.fruit_ecommerce.service;

import com.fruit_ecommerce_backend.fruit_ecommerce.entity.MedicalReport;
import com.fruit_ecommerce_backend.fruit_ecommerce.repository.MedicalReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalReportService {

    private final MedicalReportRepository repository;

    public List<MedicalReport> listAll() {
        return repository.findAll();
    }

    public Optional<MedicalReport> findById(Long id) {
        return repository.findById(id);
    }

    public MedicalReport save(MedicalReport report) {
        return repository.save(report);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<MedicalReport> saveAll(List<MedicalReport> reports) {
        return repository.saveAll(reports);
    }
}
