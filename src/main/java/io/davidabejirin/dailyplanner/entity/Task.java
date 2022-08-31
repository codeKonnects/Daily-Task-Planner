package io.davidabejirin.dailyplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@AllArgsConstructor
@Data

@Setter
@Table(name = "tasks",schema = "", catalog = "")
public class Task {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "message")
    private String message;
    @Column(name = "date")
    private Date date;

    private Date updatedDate;

    @Column(name = "done")
    private Boolean done;


    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    public Task() {}
    public Task(int id, String message, boolean done) {
        this.id = id;
        this.message = message;
        this.updatedDate = new Date();
        this.done = done;
    }
    public Task( String message, String description, boolean done) {
        this.message = message;
        this.description = description;
        this.date = new Date();
        this.updatedDate = new Date();
        this.done = done;
    }


}
