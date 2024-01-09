package lv.rtustudents.projektesanasprojekts.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "perPage")
    private Float perPage;

    @Column(name = "hardCover")
    private Float hardCover;

    @Column(name = "softCover")
    private Float softCover;

    @Column(name = "paperCover")
    private Float paperCover;

    @Column(name = "bindingStitch")
    private Float bindingStitch;

    @Column(name = "bindingPerfect")
    private Float bindingPerfect;

    @Column(name = "bindingSpiral")
    private Float bindingSpiral;

    @Column(name = "formatAlbum")
    private Float formatAlbum;

    @Column(name = "formatPortrait")
    private Float formatPortrait;
}
