package ib.parking.model;

import static org.springframework.util.StringUtils.isEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "charges")
public class Charges {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "c_scheme", nullable = false)
    private String chargingScheme;

    @Enumerated(EnumType.STRING)
    @Column(name = "v_type", nullable = false)
    private VehicleType vType;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    public List<ChargingType> getChargingScheme() {

        if (isEmpty(chargingScheme)) {
            return new ArrayList<>(1);
        }

        List<Map<String, Object>> taxes = null;
        try {
            taxes = MAPPER.readValue(chargingScheme, List.class);
        } catch (IOException e) {
            throw new RuntimeException("Error in parsing tax json from database", e);
        }

        List<ChargingType> taxKeys = new ArrayList<>(1);
        for (Map<String, Object> key : taxes) {

            if (((String) key.get("cType")).equals("fType")) {
                FixedType ft = new FixedType();
                ft.setHours(key.get("hours") == null ? null : Long.valueOf((Integer) key.get("hours")));
                ft.setPrice((Double) key.get("price"));
                taxKeys.add(ft);
            } else {
                VariableType vt = new VariableType();
                vt.setHours(key.get("hours") == null ? null : Long.valueOf((Integer) key.get("hours")));
                vt.setPrice((Double) key.get("price"));
                taxKeys.add(vt);
            }
        }
        return taxKeys;
    }

    public void setChargingScheme(List<ChargingType> cs) {
        try {
            this.chargingScheme = MAPPER.writeValueAsString(cs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting map to json", e);
        }
    }

    public VehicleType getvType() {
        return vType;
    }

    public void setvType(VehicleType vType) {
        this.vType = vType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
