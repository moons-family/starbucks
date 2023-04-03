package com.mooninho.starbucks.v2.entity;

import com.mooninho.starbucks.v2.domain.Email;
import com.mooninho.starbucks.v2.domain.Name;
import com.mooninho.starbucks.v2.domain.Password;
import com.mooninho.starbucks.v2.domain.Phone;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberId;

    @Embedded
    private Email email;
    @Embedded
    private Password password;

    @Embedded
    private Name name;
    @Embedded
    private Phone phone;

    private Member(Email email, Password password, Name name, Phone phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public static Member createMember(String email, String password, String name, String phone) {
        return new Member(
                Email.of(email),
                Password.of(password),
                optionalName(name),
                optionalPhone(phone)
        );
    }

    private static Phone optionalPhone(String phone) {
        return isPhoneNullOrBlank(phone) ? null : Phone.of(phone);
    }

    private static Name optionalName(String name) {
        return isNameNullOrBlank(name) ? null : Name.of(name);
    }

    private static boolean isPhoneNullOrBlank(String phone) {
        return phone == null || phone.isBlank();
    }

    private static boolean isNameNullOrBlank(String name) {
        return name == null || name.isBlank();
    }
}
