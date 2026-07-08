package com.code.productmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 50, message = "Tên sản phẩm phải từ 5 đến 50 ký tự")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Giá bắt đầu không được để trống")
    @Min(value = 100000, message = "Giá bắt đầu thấp nhất phải là 100.000 VND")
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String status = "chờ duyệt"; // default status

    @NotNull(message = "Vui lòng chọn loại sản phẩm")
    @ManyToOne
    @JoinColumn(name = "id_loai_sp", nullable = false)
    private Category category;
}
