//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class IdEntity {
    private String id;

    public IdEntity() {
    }

    @Id
    @GeneratedValue(
            generator = "paymentableGenerator"
    )
    @GenericGenerator(
            name = "paymentableGenerator",
            strategy = "uuid"
    )
    @Column(
            name = "ID",
            nullable = false,
            length = 32
    )
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
