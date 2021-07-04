package org.dalvarez.shop.core.category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.core.category.infrastructure.rest_api.shared.CategoryBasicRequest;
import org.dalvarez.shop.core.shared.domain.validation.Field;
import org.dalvarez.shop.core.shared.domain.validation.FieldValidator;
import org.dalvarez.shop.core.shared.domain.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.core.shared.domain.validation.UuidValidator;

import java.util.List;
import java.util.Map;

public class CategoryPostRequest extends CategoryBasicRequest<CategoryPostRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.NAME, GenericNotEmptyValidator.getInstance(),
            FieldNames.PARENT_UUID, UuidValidator.getInstance()
    );

    public CategoryPostRequest(final String name,
                               final String parentUuid) {
        super(
                fieldsValidators,
                CategoryPostRequest.class,
                name,
                parentUuid
        );
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(FieldNames.NAME, name),
                new Field<>(FieldNames.PARENT_UUID, parentUuid)
        );
    }

}
