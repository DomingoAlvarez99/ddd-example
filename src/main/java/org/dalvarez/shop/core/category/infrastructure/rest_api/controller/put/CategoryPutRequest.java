package org.dalvarez.shop.core.category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.core.category.domain.Category;
import org.dalvarez.shop.core.category.infrastructure.rest_api.shared.CategoryBasicRequest;
import org.dalvarez.shop.core.shared.domain.validation.Field;
import org.dalvarez.shop.core.shared.domain.validation.FieldValidator;
import org.dalvarez.shop.core.shared.domain.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.core.shared.domain.validation.IdValidator;
import org.dalvarez.shop.core.shared.domain.validation.UuidValidator;

import java.util.List;
import java.util.Map;

public class CategoryPutRequest extends CategoryBasicRequest<CategoryPutRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.ID, IdValidator.getInstance(),
            CategoryBasicRequest.FieldNames.NAME, GenericNotEmptyValidator.getInstance(),
            CategoryBasicRequest.FieldNames.PARENT_UUID, UuidValidator.getInstance()
    );

    private Long id;

    public CategoryPutRequest(
            final String name,
            final String parentUuid) {
        super(
                fieldsValidators,
                CategoryPutRequest.class,
                name,
                parentUuid
        );
    }

    @Override
    public Category toCategory(final Long id) {
        this.id = id;

        return super.toCategory(id);
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(FieldNames.ID, id),
                new Field<>(CategoryBasicRequest.FieldNames.NAME, name),
                new Field<>(CategoryBasicRequest.FieldNames.PARENT_UUID, parentUuid)
        );
    }

    @Override
    public String toString() {
        return "CategoryPutRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentUuid='" + parentUuid + '\'' +
                '}';
    }

    public static class FieldNames {

        public static final String ID = "id";

    }

}
