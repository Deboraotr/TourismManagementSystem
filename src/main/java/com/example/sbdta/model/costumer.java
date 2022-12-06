package com.example.sbdta.model;

public class costumer {
        private Integer id_costumer;
        private  String costumer_name;
        private String contact_costumer;
        private String age;
        private String address;



        public Integer getId_costumer() {return id_costumer;}
        public void setId_costumer(Integer id_costumer) { this.id_costumer = id_costumer;}

        public String getCostumer_name() {return costumer_name;}
        public void setCostumer_name(String costumer_name) {
            this.costumer_name = costumer_name;
        }


        public String getContact_costumer() {
            return contact_costumer;
        }
        public void setContact_costumer(String contact_costumer) {
            this.contact_costumer = contact_costumer;
        }

        public String getAge() {
            return age;
        }
        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
        return address;
    }
        public void setAddress(String address) {
        this.address = address;
    }
    }


