package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "z_organization")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Organization implements Serializable {
    /**
     * z_organization.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_organization.organization_desc
     */
    private String organizationDesc;

    /**
     * z_organization.create_time
     */
    private Timestamp createTime;

    /**
     * z_organization.update_time
     */
    private Timestamp updateTime;

    /**
     * z_organization.state
     */
    private Integer state;

    /**
     * z_organization.organization_name
     */
    private String organizationName;
//
//    @OneToMany(mappedBy = "organizationId")
//    private List<Department> department;

    //bi-directional many-to-one association to ZDepartment
//    @OneToMany(mappedBy="zOrganization")
//    private List<Department> ZDepartments;

}