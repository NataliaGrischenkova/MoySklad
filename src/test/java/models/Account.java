package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    String name;
    String secondname;
    String lastname;
    String phone;
    String position;
    String INN;
}
