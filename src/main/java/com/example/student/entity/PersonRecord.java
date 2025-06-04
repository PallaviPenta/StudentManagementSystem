package com.example.student.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PersonRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @Min(5) @Max(99) private int age;
    @NotBlank private String groupName;
    @Email private String email;
    @NotBlank private String address;
}
