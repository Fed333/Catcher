package com.example.catcher.dto;

import com.example.catcher.domain.Level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelsRequest {
    private List<String> levels;

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    public Set<Level> getLevelsEnum(){
        if (levels == null){
            return null;
        }
        Set<Level> levelSet = new HashSet<>();
        try {
            for (String level : levels) {
                levelSet.add(Level.valueOf(level.toUpperCase()));
            }
        }
        catch (IllegalArgumentException ie){
            System.out.println("Parsing List<String> levels to Set<Level> levels failed!");
            return null;
        }
        return levelSet;

    }

    @Override
    public String toString(){
        return "LevelsRequest [levels=" + levels + "]";
    }

}
