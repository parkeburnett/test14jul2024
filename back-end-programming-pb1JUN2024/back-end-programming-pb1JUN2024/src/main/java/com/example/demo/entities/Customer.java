package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

/*
this is the customer table
 */

@Getter
@Setter
@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    /*
    id for the table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer")
    private Long id;

    /*
    First Name
    @String
     */
    @NotBlank(message = "First name cannot be blank")
    @Column(name="customer_first_name",nullable = false)
    private String firstName;

    /*
    Last Name
    @String
     */
    @NotBlank(message = "Last name cannot be blank")
    @Column(name="customer_last_name",nullable = false)
    private String lastName;

    /*
    Address
    @String
     */
    @NotBlank(message = "The address line cannot be blank")
    @Column(name="address",nullable = false)
    private String address;

    /*
    Postal code
    @String
    This has to be a string to handle international postal codes
    it wil have a min length of 3  the shortest zipcode in the world is 3 characters
     */
    @NotBlank(message = "The postal code a required field and cannot be blank")
    @Size(min = 3, message = "The postal code must be at least 3 characters")
    @Column(name="postal_code",nullable = false)
    private String postal_code;

    /*
    Createion date time for the table
    @Datetime
     */
    @CreationTimestamp
    @Column(name="create_date")
    private Date create_date;

    /*
    update datetime stamp
    @Datetime
     */
    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    /*
    mapping to other tables
     */
    /*
    mapping to the division table
     */
    @ManyToOne
    @JoinColumn(name="division_id")
    private Division division;

    /*
    mapping to cart table
     */
    @OneToMany(mappedBy = "cart_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cart> carts;
}
