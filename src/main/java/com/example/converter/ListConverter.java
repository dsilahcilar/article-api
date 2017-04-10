package com.example.converter;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public abstract class ListConverter<Entity, Model> {
    abstract Model toModel(Entity entity);

    abstract Entity toEntity(Model model);

    public List<Model> toModelList(Collection<Entity> entities) {
        List<Model> models = new ArrayList<>();
        for (Entity entity : entities) {
            Model model = toModel(entity);
            models.add(model);
        }
        return models;
    }

    public List<Entity> toEntityList(Collection<Model> models) {
        List<Entity> entities = new ArrayList<>();
        for (Model model : models) {
            Entity entity = toEntity(model);
            entities.add(entity);
        }
        return entities;
    }
}
