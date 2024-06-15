package com.example.baikiemtra.model;

import jakarta.persistence.*;
import lombok.*;
@Data
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity

public class NhanVien {

    @Id

    @Column(name = "MA_NV")
    private String maNv;

    @Column(name = "TEN_NV", nullable = false, length = 100)
    private String tenNv;

    @Column(name = "PHAI", length = 3)
    private String phai;

    @Column(name = "NOI_SINH", length = 200)
    private String noiSinh;

    @ManyToOne
    @JoinColumn(name = "MA_PHONG")
    private PhongBan phongBan;

    @Column(name = "LUONG")
    private int luong;


}

