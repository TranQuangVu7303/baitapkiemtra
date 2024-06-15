package com.example.baikiemtra.service;

import com.example.baikiemtra.model.PhongBan;
import com.example.baikiemtra.repository.PhongBanRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
        @Autowired
        private PhongBanRepository phongBanRepository;

        public List<PhongBan> getAllphongban() {
            return phongBanRepository.findAll();
        }

        public Optional<PhongBan> getPhongBanById(String id) {
            return phongBanRepository.findById(id);
        }

        public void addPhongBan(PhongBan phongBan) {
            phongBanRepository.save(phongBan);
        }

        public void updatePhongBan(@NotNull PhongBan phongBan) {
            PhongBan existingPhongBan = phongBanRepository.findById(phongBan.getMaPhong())
                    .orElseThrow(() -> new IllegalStateException("PhongBan with ID " +
                            phongBan.getMaPhong() + " does not exist."));
            existingPhongBan.setTenPhong(phongBan.getTenPhong());
            phongBanRepository.save(existingPhongBan);
        }

        public void deletePhongBanById(String id) {
            if (!phongBanRepository.existsById(id)) {
                throw new IllegalStateException("PhongBan with ID " + id + " does not exist.");
            }
            phongBanRepository.deleteById(id);
        }
    }

