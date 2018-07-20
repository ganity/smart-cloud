package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_function")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Function implements Serializable {
    /**
     * z_function.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_function.protocol_id (控制协议id(对应一个控制地址))
     */
    private Long protocolId;

    /**
     * z_function.function_name (控制功能(如开,关,暂停,调温))
     */
    private String functionName;

    /**
     * z_function.span (控制功能对应的名称(on,off,stop,temp...))
     */
    private String span;

    /**
     * z_function.span_value (控制功能的值(1,0,2,[18-32]),即on发1,off发0, [18-32]为范围值)
     */
    private String spanValue;

    /**
     * z_function.create_time
     */
    private Timestamp createTime;

    /**
     * z_function.update_time
     */
    private Timestamp updateTime;

    /**
     * z_function.state
     */
    private Integer state;

}