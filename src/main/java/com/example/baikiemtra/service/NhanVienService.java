package com.example.baikiemtra.service;

import com.example.baikiemtra.model.NhanVien;
import com.example.baikiemtra.repository.NhanVienRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {

    private final NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    public Optional<NhanVien> getNhanVienById(String id) {
        return nhanVienRepository.findById(id);
    }

    public NhanVien addNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public NhanVien updateNhanVien(NhanVien nhanVien) {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVien.getMaNv())
                .orElseThrow(() -> new IllegalStateException("NhanVien with ID " +
                        nhanVien.getMaNv() + " does not exist."));
        existingNhanVien.setTenNv(nhanVien.getTenNv());
        existingNhanVien.setLuong(nhanVien.getLuong());
        existingNhanVien.setPhai(nhanVien.getPhai());
        existingNhanVien.setNoiSinh(nhanVien.getNoiSinh());
        existingNhanVien.setPhongBan(nhanVien.getPhongBan());
        return nhanVienRepository.save(existingNhanVien);
    }

    public void deleteNhanVienById(String id) {
        if (!nhanVienRepository.existsById(id)) {
            throw new IllegalStateException("NhanVien with ID " + id + " does not exist.");
        }
        nhanVienRepository.deleteById(id);
    }

    public Page<NhanVien> getNhanVien(int page, int size) {
        return nhanVienRepository.findAll(PageRequest.of(page, size));
    }
}
