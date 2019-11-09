package com.example.celi_labexer5;

public class CompanyList {
    private int logo;
    private String name, country, industry, ceo;

    public CompanyList(int logo, String name, String country, String industry, String ceo) {
        this.logo = logo;
        this.name = name;
        this.country = country;
        this.industry = industry;
        this.ceo = ceo;
    }

    public int getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getIndustry() {
        return industry;
    }

    public String getCeo() {
        return ceo;
    }
}
