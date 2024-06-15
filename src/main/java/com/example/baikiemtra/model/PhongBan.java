package com.example.baikiemtra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Data
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity

public class PhongBan {
    @Id

    @Column(name = "MA_PHONG")
    private String maPhong;

    @NotBlank(message = "Tên là bắt buộc")
    @Column(name = "TEN_PHONG", nullable = false, length = 30)
    private String tenPhong;

}
