package com.gmail.merikbest2015.ecommerce.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "clothes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Cloth {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cloth_id_seq")
    @SequenceGenerator(name = "cloth_id_seq", sequenceName = "cloth_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "cloth_title", nullable = false)
    private String clothTitle;

    @Column(name = "perfumer", nullable = false)
    private String perfumer;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "cloth_gender", nullable = false)
    private String clothGender;

    @Column(name = "cloth_top_notes", nullable = false)
    private String clothTopNotes;

    @Column(name = "cloth_middle_notes", nullable = false)
    private String clothMiddleNotes;

    @Column(name = "cloth_base_notes", nullable = false)
    private String clothBaseNotes;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "volume", nullable = false)
    private String volume;

    @Column(name = "type", nullable = false)
    private String type;
}
