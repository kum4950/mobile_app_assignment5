package com.bjtu2018kumseungwon.gymclub;


public class item {
    String trainer_name;
    String email_address;
    String phone_number;


    public item() {
    }

    public item( String trainer_name, String email_address, String phone_number) {
        this.trainer_name = trainer_name;
        this.email_address = email_address;
        this.phone_number = phone_number;

    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
