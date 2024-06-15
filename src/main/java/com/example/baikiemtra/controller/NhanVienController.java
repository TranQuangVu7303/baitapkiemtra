package com.example.baikiemtra.controller;

import com.example.baikiemtra.model.NhanVien;
import com.example.baikiemtra.service.NhanVienService;
import com.example.baikiemtra.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/nhanviens")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private PhongBanService phongBanService;

    @GetMapping("")
    public String showAllNhanVien(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<NhanVien> nhanViens = nhanVienService.getNhanVien(page, 5);
        model.addAttribute("nhanviens", nhanViens.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", nhanViens.getTotalPages());
        return "nhanvien/list"; // Ensure this template exists
    }

    @GetMapping("/add")
    public String addNhanVienForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongbans", phongBanService.getAllphongban());
        return "nhanvien/add"; // Ensure this template exists
    }

    @PostMapping("/add")
    public String addNhanVien(@Valid @ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phongbans", phongBanService.getAllphongban());
            return "nhanvien/add"; // Ensure this template exists
        }
        nhanVienService.addNhanVien(nhanvien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/edit/{id}")
    public String editNhanVienForm(Model model, @PathVariable String id) {
        Optional<NhanVien> nhanvienOpt = nhanVienService.getNhanVienById(id);
        if (nhanvienOpt.isPresent()) {
            model.addAttribute("nhanvien", nhanvienOpt.get());
            model.addAttribute("phongbans", phongBanService.getAllphongban());
            return "nhanvien/edit"; // Ensure this template exists
        }
        model.addAttribute("message", "NhanVien not found");
        return "redirect:/nhanviens";
    }

    @PostMapping("/edit")
    public String editNhanVien(@Valid @ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phongbans", phongBanService.getAllphongban());
            return "nhanvien/edit"; // Ensure this template exists
        }
        nhanVienService.updateNhanVien(nhanvien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable String id) {
        nhanVienService.deleteNhanVienById(id);
        return "redirect:/nhanviens";
    }
}
