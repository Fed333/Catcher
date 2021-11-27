package com.example.catcher.dto;

import java.util.List;

public class LevelsRequest {
    private List<String> levels;

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    @Override
    public String toString(){
        return "LevelsRequest [levels=" + levels + "]";
    }

}
