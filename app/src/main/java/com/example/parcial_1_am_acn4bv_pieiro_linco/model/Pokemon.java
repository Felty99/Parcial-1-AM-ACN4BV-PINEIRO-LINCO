package com.example.parcial_1_am_acn4bv_pieiro_linco.model;

import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    private int height;

    private int weight;

    public int getWeight() {
        return weight;
    }

    private List<Ability> abilities;
    private List<TypeFull> types;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<TypeFull> getTypes() {
        return types;
    }

    public void setTypes(List<TypeFull> types) {
        this.types = types;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }
    public String getAbiltiesToString() {
        String result = "";
        for (Ability ability : abilities) {
            result += ability.getName() + " - ";
        }
        return result;
    }
    public void setAbilties(List<Ability> abilties) {
        this.abilities = abilities;
    }
}
