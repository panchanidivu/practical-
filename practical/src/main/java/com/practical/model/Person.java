// package com.practical.model;

// import java.util.Date;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Inheritance;
// import jakarta.persistence.InheritanceType;
// import lombok.Data;

// @Entity
// @Inheritance(strategy = InheritanceType.JOINED)  // Inheritance strategy using JOINED
// @Data
// public class Person {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String address;
//     private Date dateOfBirth;
//     private Long phoneNumber;

// }
