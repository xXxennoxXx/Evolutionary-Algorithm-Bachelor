package models;

import models.boucwen.BoucWenModel;

public enum ModelType {
    BOUC_WEN("Bouc-Wen") {
        @Override
        public Model newModel() {
            return new BoucWenModel();
        }
    };

    private String name;

    ModelType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ModelType: " + name;
    }

    public abstract Model newModel();
}
