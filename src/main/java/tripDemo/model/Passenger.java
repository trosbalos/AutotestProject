package tripDemo.model;

import lombok.Data;

@Data
public class Passenger {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
}