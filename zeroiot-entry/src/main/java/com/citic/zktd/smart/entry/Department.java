package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "z_department")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_department.dept_name (部门名称)
     */
    private String deptName;

    /**
     * z_department.dept_code (部门编号)
     */
    private String deptCode;

    /**
     * z_department.parent_id (上级部门(一级部门填0))
     */
//    private Long parentId;
//            @ManyToOne()
//            @JoinColumn(name="parent_id")
//        private Department department;
//    @OneToMany(mappedBy = "department")
    @OneToMany
    @JoinColumn(name = "parent_id")
//    @JsonBackReference
//    @JsonManagedReference
    private List<Department> children;
    /**
     * z_department.organization_id
     */
//    private Long organizationId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    /**
     * create_time (注册时间)
     */
    private Timestamp createTime;

    /**
     * update_time
     */
    private Timestamp updateTime;

    /**
     * z_department.state
     */
    private Integer state;

}