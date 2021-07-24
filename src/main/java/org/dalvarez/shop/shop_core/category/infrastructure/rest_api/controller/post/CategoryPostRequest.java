package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.shared.CategoryBasicRequest;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.shop_shared.shared.infrastructure.validation.UuidValidator;

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
